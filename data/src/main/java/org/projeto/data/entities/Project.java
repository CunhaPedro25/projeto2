package org.projeto.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private User client;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "engineer", nullable = false)
  private User engineer;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "construction_type", nullable = false)
  private ConstructionType constructionType;

  @Column(name = "requirements_create_date")
  private LocalDate requirementsCreateDate;

  @Column(name = "budget_create_date")
  private LocalDate budgetCreateDate;

  @Column(name = "requirements_document", length = Integer.MAX_VALUE)
  private String requirementsDocument;

  @Column(name = "budget_document", length = Integer.MAX_VALUE)
  private String budgetDocument;

  @Column(name = "budget")
  private BigDecimal budget;

  @Column(name = "requirements_state")
  private Boolean requirementsState;

  @Column(name = "budget_state")
  private Boolean budgetState;

  @JsonIgnore
  @OneToMany(mappedBy = "project")
  private Set<Construction> constructions = new LinkedHashSet<>();

}