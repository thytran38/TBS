package tbs.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages={"tbs.webclient.controller"})
@EnableAutoConfiguration
@EnableCaching
public class WebClientApplication {

	public static void main(String[] args) {


		SpringApplication.run(WebClientApplication.class, args);
	}

}
