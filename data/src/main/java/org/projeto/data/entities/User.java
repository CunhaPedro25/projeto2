package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
  @Id
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

  @Column(name = "address", length = Integer.MAX_VALUE)
  private String address;

  @Column(name = "door")
  private Integer door;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "zipcode")
  private Zipcode zipcode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_type", nullable = false)
  private UserType userType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team")
  private Team team;

  @Column(name = "active")
  private Boolean active;

}