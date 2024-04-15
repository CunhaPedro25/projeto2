package org.projeto.data.entities;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.users.Client;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "zipcode")
public class Zipcode {
  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @Column(name = "district", nullable = false, length = Integer.MAX_VALUE)
  private String district;

  @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
  private String city;

  @Column(name = "locale", nullable = false, length = Integer.MAX_VALUE)
  private String locale;

  @OneToMany(mappedBy = "zipcode")
  private Set<Client> clients = new LinkedHashSet<>();

  public Zipcode() {}
  public Zipcode(String id, String district, String city, String locale) {
    this.id = id;
    this.district = district;
    this.city = city;
    this.locale = locale;
  }
}