package soft.samarone.testeV1.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.integration.ipvigilante.IpvigilanteClient;
import soft.samarone.testeV1.integration.ipvigilante.dto.IpvigilanteResponse;

@Service
public class GeolocalizacaoService {

	private static final String LOOPBACK_IP = "127.0.0.1";

	private static final String COMMA = ",";
	
    @Value("${ip.client.fixed:8.8.8.8}")
    private String ipClientFixed;

	public Cliente handleCliente(Cliente cliente) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String ip = request.getRemoteAddr();
		
		if (LOOPBACK_IP.equals(ip)) {
			ip = ipClientFixed;
		}
		
		cliente.setIp(ip);

		IpvigilanteClient client = new IpvigilanteClient();
		IpvigilanteResponse result = client.search(ip);

		cliente.setGeoLocalizacao(result.getData().getLatitude() + COMMA + result.getData().getLongitude());

		return cliente;
	}

}
