package no.bekk.fagdag20210303.unleashdemo;

import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@EnableJwtTokenValidation(ignore = { "org.springframework",
		"springfox.documentation.swagger.web.ApiResourceController" })
public class UnleashDemoApplication {

	public static String APP_NAME = "unleash-demo";

	public static void main(String[] args) {
		SpringApplication.run(UnleashDemoApplication.class, args);
	}

}
