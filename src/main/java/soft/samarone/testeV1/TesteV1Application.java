package soft.samarone.testeV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableSpringDataWebSupport
@EnableWebMvc
@SpringBootApplication
@ComponentScan("soft.samarone.testeV1")
public class TesteV1Application {

	public static void main(String[] args) {
		SpringApplication.run(TesteV1Application.class, args);
	}
}
