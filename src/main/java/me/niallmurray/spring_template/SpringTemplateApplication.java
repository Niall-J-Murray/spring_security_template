package me.niallmurray.spring_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTemplateApplication.class, args);
    List lait = new ArrayList();
  }

}
