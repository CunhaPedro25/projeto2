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
@Table(name = "construction_type")
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "type", length = Integer.MAX_VALUE)
  private String type;

  @JsonIgnore
  @OneToMany(mappedBy = "constructionType")
  private Set<Project> projects = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "constructionType")
  private Set<Stage> stages = new LinkedHashSet<>();

}