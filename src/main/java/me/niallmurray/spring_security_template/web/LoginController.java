package me.niallmurray.spring_security_template.web;

import me.niallmurray.spring_security_template.domain.Authority;
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
import java.util.Set;

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


  //* FIX HOME TO BE UNSECURED AND SHOWN TO ALL WHO ACCESS IT
  // CHANGE WHAT IS SHOWN ON HOME DEPENDING ON IF LOGGED IN OR NOT
  // FINISH ADJUSTING NAV BAR TO SHOW APPROPRIATE LINKS ON EACH PAGE
  // E.G DASH ONLY WHEN LOGGED IN, CONSIDER REMOVING ADMIN LINKS FOR SIMPLICITY
  @GetMapping("/home")
  public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
    model.addAttribute("activeUsers", activeUserStore.getUsers());
    model.addAttribute("users", adminService.getAllUserAccounts());
    model.addAttribute("user", user);
//    if (activeUserStore.getUsers().contains(user.getUsername())) {
//      model.addAttribute("isLoggedIn", true);
//    }
    Set<Authority> authorities = user.getAuthorities();
    System.out.println(authorities);

//*Check if user is admin (has "ROLE_ADMIN") then set isAdmin==true below.

//    System.out.println(user);

    if (userService.isLoggedIn(user)) {
      model.addAttribute("isLoggedIn", true);
    }
//    if (user != null && user.getAuthorities().contains() {
//      model.addAttribute("isAdmin", true);
//    }

    return "home";
  }

  @GetMapping("/login")
  public String getLogin(@AuthenticationPrincipal User user, ModelMap model) {
    List activeUsers = activeUserStore.getUsers();
    if (activeUsers.contains(user)) {
      return "redirect:/home";
    }

    model.addAttribute("activeUsers", activeUsers);
    return "login";
  }

//  @GetMapping("/perform_login")
//  public String redirectLoginSuccess() {
//    return "redirect:/home";
//  }
//  @GetMapping("/loggedUsers")
//  public String getLoggedUsers(ModelMap model) {
//    model.addAttribute("activeUsers", activeUserStore.getUsers());
//    return "admin";
//  }

  @GetMapping("/confirm_logout")
  public String getConfirmLogout() {
    return "confirm_logout";
  }

  @GetMapping("/logout")
  public String getLogout() {
    return "login";
  }
}
