package me.niallmurray.spring_template.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authority ")
public class Authority implements GrantedAuthority {
  @Serial
  private static final long serialVersionUID = -8123526131047887755L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private User user;
  private String authority;

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Authority other)) {
      return false;
    }
    return Objects.equals(id, other.id);
  }

//  @Override
//  public String toString() {
//    return "Authority{" +
//            "id=" + id +
//            ", user=" + user +
//            ", authority='" + authority + '\'' +
//            '}';
//  }
}
