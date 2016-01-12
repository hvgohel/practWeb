package com.dw.practWeb.security;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.model.Registration.Role;
import com.dw.practWeb.service.SecurityRegisteredUserManager;

/**
 *
 */
@Component(value = "securityServiceHelper")
public class SecurityServiceHelperImpl implements SecurityServiceHelper {
    
    private static Logger logger = LoggerFactory.getLogger(SecurityServiceHelperImpl.class);

    @Inject
    private UserDetailsService registeredUserDetailsService;

    @Inject
    private SecurityRegisteredUserManager securityRegisteredUserManager;

    private String systemUserEmail = "system";

    public void loginAsSystem() {

        if (systemUserEmail == null) {
            throw new IllegalArgumentException("System email not found in configuration.!");
        }
        logger.debug("Logging in as system user {}", systemUserEmail);
        try {
            SecurityUser securityUser = null;
            Registration registration = securityRegisteredUserManager.get(systemUserEmail);

            if (registration != null) {

                securityUser =
                        new SecurityUser(registration.getUserName(), registration.getPassword(),
                                RegisteredUserDetailsServiceImpl.getRoleBasedAuthorities(registration));
                securityUser.setId(registration.getId());

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), securityUser
                                .getAuthorities()));
                logger.debug("Logged in as system user {}", systemUserEmail);
                return;
            }
        } catch (Exception e) {
            logger.error("loadUserByUsername:: failed", e);
        }

        throw new UsernameNotFoundException("Failed to find username in database: " + systemUserEmail);
    }

    public void loginAsUser(String email) {
        logger.debug("loginAsUser:: email : {}", email);
        SecurityUser user = (SecurityUser) registeredUserDetailsService.loadUserByUsername(email);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    // Requires a user to be created
    public void loginAsRole(String role) {
        List<GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(role);
        SecurityUser user = new SecurityUser("testuser@test.com", "name", authorities);
        user.setId(1L);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities));
    }

    public void loginAsRole(Role role) {
        loginAsRole(role.toString());
    }

    public void logout() {
        logger.debug("logout() :: current user is logged out");
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
