
package com.conatus.myapp.kakao_login;

// WebSecurityConfigurerAdapter, EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.*;
// HttpSecurity
import org.springframework.security.config.annotation.web.builders.*;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}