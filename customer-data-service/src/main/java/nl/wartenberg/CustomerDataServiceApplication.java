package nl.wartenberg;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import nl.wartenberg.config.DatabaseInitializer;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
// @EnableEurekaClient
// @EnableResourceServer
// @EnableOAuth2Client
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
