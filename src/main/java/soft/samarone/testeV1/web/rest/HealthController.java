package soft.samarone.testeV1.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

	private final Logger LOG = LoggerFactory.getLogger(HealthController.class);

	@GetMapping("/")
	public ResponseEntity<String> healthCheck() {
		LOG.info("REST request health check");
		return new ResponseEntity<>("{\"status\" : \"UP\"}", HttpStatus.OK);
	}

}
