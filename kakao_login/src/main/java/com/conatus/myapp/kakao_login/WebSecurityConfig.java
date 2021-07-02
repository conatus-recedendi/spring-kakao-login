
package com.conatus.myapp.kakao_login;

import java.util.*;
import java.lang.System.*;

// WebSecurityConfigurerAdapter, EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.*;

import org.springframework.context.annotation.Bean;
// HttpSecurity
import org.springframework.security.config.annotation.web.builders.*;

import org.springframework.security.oauth2.client.registration.*;
import java.util.*;

import lombok.RequiredArgsConstructor;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login/**").permitAll().anyRequest().authenticated().and().oauth2Login();

    }

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(
		@Value ("${spring.security.oauth2.client.registration.kakao.client-id}")
		String kakaoClientId, 
		@Value("${spring.security.oauth2.client.registration.kakao.client-secret}") 
		String kakaoClientSecret
		)
	{
		List<ClientRegistration> registrations = new ArrayList<>();
		
		registrations.add(KakaoOAuthProvider.KAKAO.getBuilder("kakao").clientId(kakaoClientId).clientSecret(kakaoClientSecret).jwkSetUri("temp").build());
		//System.out.println(registrations.get(0).toString());
		return new InMemoryClientRegistrationRepository(registrations);
	}

}