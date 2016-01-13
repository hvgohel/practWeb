package com.dw.practWeb.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    
    @Inject
    private CustomAuthenticationSuccessHandler success;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers("/home").authenticated()
                //.antMatchers("/signup").permitAll()
                //.antMatchers("/", "/welcome").permitAll()
                .anyRequest().permitAll()
                .and()
            .csrf()
                .disable()
            .formLogin()
                .loginPage("/login")
                .successHandler(success)
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

     @Inject
     UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

         auth.userDetailsService(userDetailsService);
    }
    
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws ServletException, IOException
            {
                super.onAuthenticationSuccess(request, response, authentication);

//                SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
//                String lastRequestURL = savedRequest != null ? savedRequest.getRedirectUrl() : null;
//
//                if (lastRequestURL == null)
//                {
//                    response.sendRedirect("home");
//                    return;
//                }
//                response.sendRedirect(lastRequestURL);
            }
        };
    }
}
