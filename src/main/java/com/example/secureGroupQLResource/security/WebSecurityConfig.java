package com.example.secureGroupQLResource.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Bean
//	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////	    // @formatter:off
////	    http
////	        .authorizeHttpRequests((authz) -> authz
////	            .antMatchers("/hello").hasAuthority("SCOPE_access-hello")
////	            .anyRequest().authenticated()
////	        )
////	        .oauth2ResourceServer().jwt();
////	    // @formatter:on
//		
//		JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
//    	jwtAuthConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
//	    
//	    http
//      .csrf().disable()
//      .authorizeRequests()
//          .antMatchers("/graphql").permitAll()
////          .antMatchers("/vendor/**").permitAll()
////          .antMatchers("/graphiql").permitAll()
//          .anyRequest().authenticated()
//      .and()
//      .oauth2ResourceServer()
//      .jwt()
//      .jwtAuthenticationConverter(jwtAuthConverter);
//	 
//	    return http.build();
//	  }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
    	jwtAuthConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
    	
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/graphql").permitAll()
//                .antMatchers("/vendor/**").permitAll()
//                .antMatchers("/graphiql").permitAll()
                .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("mi3o").password("{noop}nbusr123").roles("USER").and()
//                .withUser("admin").password("{noop}nbusr123").roles("USER", "ADMIN");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    
//    @Value("${spring.security.oauht2.resourceserver.jwt.jwk-set-uri}")
//    private String jwkSetUri;
//    
//    @Bean
//	public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
//		return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//	}
}