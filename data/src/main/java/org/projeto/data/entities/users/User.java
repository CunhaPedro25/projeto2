package org.projeto.data.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", length = 70)
  private String name;

  @Column(name = "email", nullable = false, length = 254)
  private String email;

  @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
  private String password;

  @Column(name = "phone", nullable = false, length = Integer.MAX_VALUE)
  private String phone;

  @Column(name = "active")
  private Boolean active = true;

  public User() {}

  public User(String name, String email, String password, String phone) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }
}
