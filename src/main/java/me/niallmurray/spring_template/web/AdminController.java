package me.niallmurray.spring_template.web;

import me.niallmurray.spring_template.domain.User;
import me.niallmurray.spring_template.security.ActiveUserStore;
import me.niallmurray.spring_template.service.AdminService;
import me.niallmurray.spring_template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

  @Autowired
  ActiveUserStore activeUserStore;
  @Autowired
  private AdminService adminService;
  @Autowired
  private UserService userService;

  @GetMapping("/admin")
  public String getAdmin(ModelMap model) {
    List<User> allUserAccounts = adminService.getAllUserAccounts();
    model.addAttribute("users", allUserAccounts);
    model.addAttribute("activeUsers", activeUserStore.getUsers());
    model.addAttribute("isLoggedIn", true);

    return "admin";
  }

}
