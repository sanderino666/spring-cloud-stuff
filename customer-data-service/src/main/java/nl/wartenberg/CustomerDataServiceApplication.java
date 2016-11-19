package nl.wartenberg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableEurekaClient
@EnableResourceServer
@EnableOAuth2Client
public class CustomerDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataServiceApplication.class, args);
	}

	@Component
	public static class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

		@Override
		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
			config.setBasePath("/api");
		}
	}
}
