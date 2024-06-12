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
@Table(name = "stage")
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", length = Integer.MAX_VALUE)
  private String name;

  @Column(name = "percentage")
  private Double percentage;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "construction_type", nullable = false)
  private ConstructionType constructionType;

  @JsonIgnore
  @OneToMany(mappedBy = "stage")
  private Set<Construction> constructions = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "stage")
  private Set<Invoice> invoices = new LinkedHashSet<>();

}