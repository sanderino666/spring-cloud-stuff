package nl.wartenberg.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import nl.wartenberg.domain.Aggregate;

public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Aggregate.class);
		super.configureRepositoryRestConfiguration(config);
	}
}
