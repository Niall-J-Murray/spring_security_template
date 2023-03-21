package me.niallmurray.spring_template.web;


import me.niallmurray.spring_template.domain.User;
import me.niallmurray.spring_template.service.AdminService;
import me.niallmurray.spring_template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  @Autowired
  private AdminService adminService;

  @Autowired
  private UserService userService;

  @GetMapping("/dashboard")
  public String getDashboard(@AuthenticationPrincipal User user, ModelMap model) {
    model.addAttribute("user", user);
    if (userService.isLoggedIn(user)) {
      model.addAttribute("isLoggedIn", true);
    }
    //    System.out.println(user.getUsername()+" logged in.");
    return "dashboard";
  }

//  @GetMapping("/admin")
//  public String getAdmin(ModelMap modelMap){
//    List<User> allUserAccounts = adminService.getAllUserAccounts();
//    modelMap.addAttribute("users",allUserAccounts);
//    return "admin";
//  }

//  @GetMapping("/account")
//  public String getAccount(ModelMap model) {
//    User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User user = userService.findById(loggedInUser.getId());
//    model.addAttribute("user", user);
//    return "/account";
//  }

  @GetMapping("/test")
  public String getTest() {
    return "test";
  }

}
