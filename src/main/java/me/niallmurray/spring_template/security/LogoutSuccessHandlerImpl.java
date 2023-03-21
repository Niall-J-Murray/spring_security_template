package me.niallmurray.spring_template.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("logoutSuccessHandlerImpl")
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(HttpServletRequest request,
                              HttpServletResponse response,
                              Authentication authentication)
          throws IOException, ServletException {
    HttpSession session = request.getSession();
    if (session != null) {
      session.removeAttribute("user");
    }
    response.sendRedirect("/home");
  }
}

