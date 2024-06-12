package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
  @JoinColumn(name = "team")
  private Team team;

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

}