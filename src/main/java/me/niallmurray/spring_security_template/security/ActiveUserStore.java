package me.niallmurray.spring_security_template.security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActiveUserStore {
  public List<String> users;

  public ActiveUserStore() {
    users = new ArrayList<String>();
  }
}
