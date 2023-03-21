package me.niallmurray.spring_template.service;


import me.niallmurray.spring_template.domain.User;
import me.niallmurray.spring_template.repositories.UserRepository;
import me.niallmurray.spring_template.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username);

    if (user == null)
      throw new UsernameNotFoundException("User with username [" + username + "] not found.");

    return new UserDetailsImpl(user);
  }

}
