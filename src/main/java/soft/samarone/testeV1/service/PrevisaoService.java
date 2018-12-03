package soft.samarone.testeV1.service;

import static soft.samarone.testeV1.Constants.GEO_DEFAULT;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.integration.metaweather.MetaweatherClient;
import soft.samarone.testeV1.integration.metaweather.dto.ConsolidatedWeather;
import soft.samarone.testeV1.integration.metaweather.dto.MetaWeatherLocationResponse;
import soft.samarone.testeV1.integration.metaweather.dto.MetaWeatherResponseList;

@Service
public class PrevisaoService {

	private final Logger LOG = LoggerFactory.getLogger(PrevisaoService.class);

	@Async
	@Retryable(backoff = @Backoff(delay = 3000, multiplier = 2, maxDelay = 9000), maxAttempts = 10)
	public void handleCliente(Cliente cliente, Function<Cliente, Cliente> function) {

		LOG.info("O serviço de previsão de tempo será consultado para o Ip: {}", cliente.getIp());

		MetaweatherClient client = new MetaweatherClient();

		String geoLocalizacao = cliente.getGeoLocalizacao();

		if (GEO_DEFAULT.equals(geoLocalizacao)) {
			LOG.warn("Geolocalização ainda não foi preenchida, abortando...");
			throw new RuntimeException();
		} else {

			MetaWeatherResponseList result = client.search(geoLocalizacao);

			// Where On Earth ID
			Integer woeid = new Integer(0);

			if (!result.getResults().isEmpty()) {
				woeid = result.getResults().get(0).getWoeid();
				LOG.info("Pesquisa realizada com retorno de WOEID aproximado com valor de: {}", woeid);
			}

			MetaWeatherLocationResponse resultLocation = client.location(woeid);

			final int size = resultLocation.getConsolidatedWeather().size();

			ConsolidatedWeather consolidatedWeather = resultLocation.getConsolidatedWeather().get(size - 1);

			if (consolidatedWeather != null) {
				LOG.info("Encontrada previsão de para o WOIED com valores: {}", consolidatedWeather);
				cliente.setTempMin(consolidatedWeather.getMinTemp());
				cliente.setTempMax(consolidatedWeather.getMaxTemp());
				LOG.info("Cliente atualziado com valores de TempMin: {} e TempMax: {}", cliente.getTempMin(),
						cliente.getTempMax());
			}

			function.apply(cliente);
		}
	}

}
