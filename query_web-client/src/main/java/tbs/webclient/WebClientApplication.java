package tbs.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"tbs.webclient.controller"})
@EnableAutoConfiguration
public class WebClientApplication {

	public static void main(String[] args) {


		SpringApplication.run(WebClientApplication.class, args);
	}

}
