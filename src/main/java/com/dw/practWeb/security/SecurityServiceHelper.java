package com.dw.practWeb.security;

import com.dw.practWeb.model.Registration.Role;

public interface SecurityServiceHelper {

  /**
   * This method is used to logged in using system user
   */
  void loginAsSystem();

  void loginAsUser(String username);

  void loginAsRole(String role);

  void loginAsRole(Role role);

  void logout();
}
