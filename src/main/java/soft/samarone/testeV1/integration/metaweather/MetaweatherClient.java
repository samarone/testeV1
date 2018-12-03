package soft.samarone.testeV1.integration.metaweather;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import soft.samarone.testeV1.integration.metaweather.dto.MetaWeatherLocationResponse;
import soft.samarone.testeV1.integration.metaweather.dto.MetaWeatherResponse;
import soft.samarone.testeV1.integration.metaweather.dto.MetaWeatherResponseList;

public class MetaweatherClient {

	private static final String URL_SERVICE_SEARCH = "https://www.metaweather.com/api/location/search/?lattlong=";
	private static final String URL_SERVICE_LOCATION = "https://www.metaweather.com/api/location/";

	public MetaWeatherResponseList search(final String geolocalizacao) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<MetaWeatherResponse>> responseEntity = restTemplate.exchange(
				URL_SERVICE_SEARCH + geolocalizacao, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MetaWeatherResponse>>() {
				});

		MetaWeatherResponseList list = new MetaWeatherResponseList();
		list.setResults(responseEntity.getBody());
		return list;

	}

	public MetaWeatherLocationResponse location(final Integer woeid) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(URL_SERVICE_LOCATION + woeid, MetaWeatherLocationResponse.class);
	}

}
