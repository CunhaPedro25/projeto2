package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @OneToMany(mappedBy = "state")
  private Set<Construction> constructions = new LinkedHashSet<>();

}