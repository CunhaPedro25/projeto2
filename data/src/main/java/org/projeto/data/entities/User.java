package org.projeto.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(name = "address", length = Integer.MAX_VALUE)
  private String address;

  @Column(name = "door")
  private Integer door;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "zipcode")
  private Zipcode zipcode;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_type", nullable = false)
  private UserType userType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team")
  private Team team;

  @Column(name = "active")
  private Boolean active;

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private Set<Complaint> complaints = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private Set<Invoice> invoices = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private Set<Project> projects_client = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private Set<Project> projects_engineer = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "leader")
  private Set<Team> teams = new LinkedHashSet<>();
}