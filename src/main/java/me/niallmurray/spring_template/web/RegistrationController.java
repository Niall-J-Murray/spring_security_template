package me.niallmurray.spring_template.web;

import me.niallmurray.spring_template.domain.User;
import me.niallmurray.spring_template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {


  @Autowired
  private UserService userService;

  @GetMapping("/register")
  public String getRegister(ModelMap model) {
    model.addAttribute("user", new User());
    return "register";
  }


  @PostMapping("/register")
  public String postCreateUser(ModelMap model, User user) {

    boolean usernameExists = userService.usernameExists(user.getUsername());

    if (usernameExists) {
      model.addAttribute("userExists", "Username already exists");
      return "register";
    }

    if (user.getPassword().length() < 8) {
      model.addAttribute("badPassword", "Password cannot be less than 8 characters");
      return "register";
    }
//    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);

    return "redirect:/login";
  }
}
