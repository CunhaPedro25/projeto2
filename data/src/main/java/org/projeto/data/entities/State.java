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
@Table(name = "state")
@NoArgsConstructor
@AllArgsConstructor
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "state")
  private Set<Construction> constructions = new LinkedHashSet<>();

}