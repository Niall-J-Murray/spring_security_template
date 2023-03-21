package me.niallmurray.spring_security_template.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public ActiveUserStore activeUserStore() {
    return new ActiveUserStore();
  }

}

