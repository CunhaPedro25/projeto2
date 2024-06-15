package org.projeto.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "construction")
@NoArgsConstructor
@AllArgsConstructor
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "project", nullable = false)
  private Project project;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "stage")
  private Stage stage;

  @Column(name = "stage_budget")
  private BigDecimal stageBudget;

  @Column(name = "total")
  private BigDecimal total;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  private State state;

  @Column(name = "last_update")
  private Instant lastUpdate;

  @JsonIgnore
  @OneToMany(mappedBy = "construction")
  private Set<Complaint> complaints = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "construction")
  private Set<ConstructionMaterial> constructionMaterials = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "construction")
  private Set<ConstructionTeam> constructionTeams = new LinkedHashSet<>();

}