package com.dw.practWeb.security;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.repository.RegistrationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Service
@Transactional
public class RegisteredUserDetailsServiceImpl implements UserDetailsService {
  private static Logger logger = LoggerFactory.getLogger(RegisteredUserDetailsServiceImpl.class);

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Inject
  private RegistrationRepository registrationRepository;

  public RegisteredUserDetailsServiceImpl() {
    logger.debug("initialized");
  }

  public static List<GrantedAuthority> getRoleBasedAuthorities(Registration registration) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(registration.getRole().getAuthority()));

    /*
     * for (Registration.Role role : registration.getRoles()) { authorities.add(new
     * SimpleGrantedAuthority(role.getAuthority())); }
     */
    return authorities;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Registration registration = registrationRepository.findByUserName(username);

      if (registration == null) {
        throw new RuntimeException("No registration found");
      }

      SecurityUser user = new SecurityUser(registration.getUserName(), registration.getPassword(),
          getRoleBasedAuthorities(registration));

      user.setId(registration.getId());

      return user;
    } catch (Exception e) {
      logger.error("loadUserByUsername:: failed", e);
    }

    throw new UsernameNotFoundException("Failed to find username in database: " + username);

  }

}
