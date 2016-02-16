package com.dw.practWeb.security;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dw.practWeb.model.Registration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private CustomAuthenticationSuccessHandler success;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/home").hasRole(Registration.Role.USER.name()).antMatchers("/index")
        .hasRole(Registration.Role.ADMIN.name())
        // .antMatchers("/signup").permitAll()
        // .antMatchers("/", "/welcome").permitAll()
        .anyRequest().permitAll().and().csrf().disable().formLogin().loginPage("/login").successHandler(success)
        .permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied");

  }

  @Inject
  UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

    auth.userDetailsService(userDetailsService);
  }
}
