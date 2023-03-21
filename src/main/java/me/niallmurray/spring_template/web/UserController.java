package me.niallmurray.spring_template.web;

import me.niallmurray.spring_template.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class UserController {

  @Autowired
  ActiveUserStore activeUserStore;

  @GetMapping("/loggedUsers")
  public String getLoggedUsers(Locale locale, ModelMap model) {
    model.addAttribute("users", activeUserStore.getUsers());
    return "users";
  }
}
