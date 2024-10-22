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
@Table(name = "zipcode")
@NoArgsConstructor
@AllArgsConstructor
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

  @JsonIgnore
  @OneToMany(mappedBy = "zipcode")
  private Set<User> users = new LinkedHashSet<>();

}