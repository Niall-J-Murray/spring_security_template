package me.niallmurray.spring_security_template.web;

import me.niallmurray.spring_security_template.domain.User;
import me.niallmurray.spring_security_template.security.ActiveUserStore;
import me.niallmurray.spring_security_template.service.AdminService;
import me.niallmurray.spring_security_template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

  @Autowired
  ActiveUserStore activeUserStore;
  @Autowired
  private AdminService adminService;
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String rootRedirect() {
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
    model.addAttribute("activeUsers", activeUserStore.getUsers());
    model.addAttribute("users", adminService.getAllUserAccounts());
    model.addAttribute("user", user);

    if (userService.isLoggedIn(user)) {
      model.addAttribute("isLoggedIn", true);
    }

    if (userService.isAdmin(user)) {
      model.addAttribute("isAdmin", true);
    }
    return "home";
  }

  @GetMapping("/login")
  public String getLogin(@AuthenticationPrincipal User user, ModelMap model) {
    List<String> activeUsers = activeUserStore.getUsers();
    if (user != null && activeUsers.contains(user.getUsername())) {
      return "redirect:/home";
    }
    model.addAttribute("activeUsers", activeUsers);
    return "login";
  }

  @GetMapping("/confirm_logout")
  public String getConfirmLogout(@AuthenticationPrincipal User user, ModelMap model) {
    model.addAttribute("user", user);
    return "confirm_logout";
  }

  @GetMapping("/logout")
  public String getLogout() {
    return "login";
  }
}
