package com.dw.practWeb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    handle(request, response, authentication);
  }

  protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    // SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
    // String lastRequestURL = savedRequest != null ? savedRequest.getRedirectUrl() : null;

    String lastRequestURL = determineTargetUrl(authentication);

    if (lastRequestURL == null) {
      lastRequestURL = "/home";
    }

    if (response.isCommitted()) {
      return;
    }

    response.sendRedirect(lastRequestURL);
  }

  /** Builds the target URL according to the logic defined in the main class Javadoc. */
  protected String determineTargetUrl(Authentication authentication) {
    boolean isUser = false;
    boolean isAdmin = false;
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
      if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
        isUser = true;
        break;
      } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
        isAdmin = true;
        break;
      }
    }

    if (isUser) {
      return "/home";
    } else if (isAdmin) {
      return "/admin";
    } else {
      throw new IllegalStateException();
    }
  }

  /*
   * protected void clearAuthenticationAttributes(HttpServletRequest request) { HttpSession session
   * = request.getSession(false); if (session == null) { return; }
   * session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); }
   */
}
