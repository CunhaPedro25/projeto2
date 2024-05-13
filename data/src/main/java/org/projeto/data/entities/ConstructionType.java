package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "construction_type")
public class ConstructionType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "type", length = Integer.MAX_VALUE)
  private String type;

  @OneToMany(mappedBy = "constructionType")
  private Set<Project> projects = new LinkedHashSet<>();

  @OneToMany(mappedBy = "constructionType")
  private Set<Stage> stages = new LinkedHashSet<>();

}