package com.springjwt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenStore tokenStore;

  @Value("${jwt.CLIEN_ID}")
  private String CLIEN_ID;

  @Value("${jwt.CLIENT_SECRET}")
  private String CLIENT_SECRET;

  @Value("${jwt.GRANT_TYPE_PASSWORD}")
  private String GRANT_TYPE_PASSWORD;

  @Value("${jwt.AUTHORIZATION_CODE}")
  private String AUTHORIZATION_CODE;

  @Value("${jwt.REFRESH_TOKEN}")
  private String REFRESH_TOKEN;

  @Value("${jwt.IMPLICIT}")
  private String IMPLICIT;

  @Value("${jwt.SCOPE_READ}")
  private String SCOPE_READ;

  @Value("${jwt.SCOPE_WRITE}")
  private String SCOPE_WRITE;

  @Value("${jwt.TRUST}")
  private String TRUST;

  @Value("${jwt.ACCESS_TOKEN_VALIDITY_SECONDS}")
  int ACCESS_TOKEN_VALIDITY_SECONDS;

  @Value("${jwt.FREFRESH_TOKEN_VALIDITY_SECONDS}")
  int REFRESH_TOKEN_VALIDITY_SECONDS;


  @Override
  public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

    configurer
        .inMemory()
        .withClient(CLIEN_ID)
        .secret(CLIENT_SECRET)
        .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
        .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
        .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
        refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
  }


  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore)
        .authenticationManager(authenticationManager);
  }


}
