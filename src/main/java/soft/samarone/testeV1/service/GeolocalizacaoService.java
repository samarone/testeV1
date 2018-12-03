package soft.samarone.testeV1.service;

import java.util.function.Function;

import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.integration.ipvigilante.IpvigilanteClient;
import soft.samarone.testeV1.integration.ipvigilante.dto.IpvigilanteResponse;

@Service
public class GeolocalizacaoService {

	private static final String COMMA = ",";

	@Async
	@Retryable(maxAttempts = 5)
	public void handleCliente(Cliente cliente, Function<Cliente,Cliente> function) {

		IpvigilanteClient client = new IpvigilanteClient();
		IpvigilanteResponse result = client.search(cliente.getIp());

		cliente.setGeoLocalizacao(result.getData().getLatitude() + COMMA + result.getData().getLongitude());
		function.apply(cliente);

	}

}
