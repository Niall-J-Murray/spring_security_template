package me.niallmurray.spring_template.service;

import me.niallmurray.spring_template.domain.User;
import me.niallmurray.spring_template.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

  @Autowired
  private UserRepository userRepository;

  @Secured({"ROLE_ADMIN"})
  public List<User> getAllUserAccounts() {
    return userRepository.findAll();
  }
}
