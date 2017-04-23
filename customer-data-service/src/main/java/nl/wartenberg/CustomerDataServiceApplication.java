package nl.wartenberg;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import nl.wartenberg.config.DatabaseInitializer;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableEurekaClient
@EnableOAuth2Client
@EnableHystrix
@ComponentScan("nl.wartenberg")
@EnableBinding(Source.class)
public class CustomerDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataServiceApplication.class, args);
	}

	@Bean
	public IMongodConfig mongodConfig() throws IOException {
		return new MongodConfigBuilder() //
				.version(Version.Main.PRODUCTION) //
				.cmdOptions(new MongoCmdOptionsBuilder() //
						.useNoJournal(true) //
						.useStorageEngine("mmapv1") //
						.build()) //
				.build();
	}

	@Bean
	public CommandLineRunner commandLineRunner(DatabaseInitializer databaseInitializer) {
		return args -> {
			// Initialize the database for end to end integration testing
			databaseInitializer.populate();
		};
	}

}
