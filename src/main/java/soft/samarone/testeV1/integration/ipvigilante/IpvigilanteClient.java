package soft.samarone.testeV1.integration.ipvigilante;

import org.springframework.web.client.RestTemplate;

import soft.samarone.testeV1.integration.ipvigilante.dto.IpvigilanteResponse;

public class IpvigilanteClient {
	
	private static final String URL_SERVICE = "https://ipvigilante.com/";
	
	public IpvigilanteResponse search(final String ip) {
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL_SERVICE + ip, IpvigilanteResponse.class);
	}

}
