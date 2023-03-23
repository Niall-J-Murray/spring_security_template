package me.niallmurray.spring_security_template.service;

import me.niallmurray.spring_security_template.domain.Authority;
import me.niallmurray.spring_security_template.domain.User;
import me.niallmurray.spring_security_template.repositories.UserRepository;
import me.niallmurray.spring_security_template.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

  @Autowired
  ActiveUserStore activeUserStore;
  @Autowired
  private UserRepository userRepository;

  public User createUser(User user) {
    Authority authority = new Authority();
    authority.setUser(user);

    authority.setAuthority("ROLE_USER");
    user.getAuthorities().add(authority);
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    // To check for unsuccessful logouts.
    user.setLastLogout(LocalDateTime.of(66, 6, 6, 6, 6));

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

  public boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }

  public boolean isLoggedIn(User user) {
    if (user != null) {
      return activeUserStore.getUsers().contains(user.getUsername());
    }
    return false;
  }

  public boolean isAdmin(User user) {
    if (user != null) {
      Set<Authority> authorities = user.getAuthorities();
      for (Authority authority : authorities) {
        if (authority.getAuthority().equals("ROLE_ADMIN")) {
          return true;
        }
      }
    }
    return false;
  }

  public void updateLastLogout(Long userId) {
    User user = findById(userId);
    user.setLastLogout(LocalDateTime.now());
    userRepository.save(user);
  }

  public void delete(Long userId) {
    userRepository.deleteById(userId);
  }
}