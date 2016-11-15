package nl.wartenberg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

// curl -u acme:acmesecret "http://localhost:8002/uaa/oauth/token?grant_type=password&username=user&password=password" -X POST -H "Content-Type:application/x-www-form-urlencoded" -v
// curl "acme:acmesecret@localhost:8002/uaa/oauth/token?grant_type=password&username=user&password=password" -X POST -H "Content-Type:application/x-www-form-urlencoded" -v
// curl "http://localhost:8002/uaa/v1/me" -H "Authorization: Bearer 123d298b-a6eb-4cd7-839c-f425bfa62e65"
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints //
				.tokenStore(tokenStore()) //
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer //
				.tokenKeyAccess("permitAll()") //
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() //
				.withClient("acme") //
				.secret("acmesecret") //
				.authorizedGrantTypes("authorization_code", "refresh_token", "password") //
				.scopes("openid");
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
}
