package me.niallmurray.spring_security_template.service;

import me.niallmurray.spring_security_template.domain.Authority;
import me.niallmurray.spring_security_template.domain.User;
import me.niallmurray.spring_security_template.repositories.UserRepository;
import me.niallmurray.spring_security_template.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

  @Autowired
  ActiveUserStore activeUserStore;
  @Autowired
  private UserRepository userRepository;

  public User saveUser(User user) {
    Authority authority = new Authority();
    authority.setUser(user);

    authority.setAuthority("ROLE_USER");
    user.getAuthorities().add(authority);


    System.out.println(("pw:" + user.getPassword()));
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    System.out.println(("pwe:" + user.getPassword()));
    System.out.println(user);


    return userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long userId) {
    Optional<User> findById = userRepository.findById(userId);
    return findById.orElse(null);

  }


  public boolean usernameExists(String username) {

    return userRepository.findByUsername(username) != null;
  }

  public boolean isLoggedIn(User user) {
    return activeUserStore.getUsers().contains(user.getUsername());
  }
}
