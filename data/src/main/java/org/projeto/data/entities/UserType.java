package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_type")
public class UserType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "type", nullable = false, length = Integer.MAX_VALUE)
  private String type;

  @OneToMany(mappedBy = "userType")
  private Set<User> users = new LinkedHashSet<>();

}