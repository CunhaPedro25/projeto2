package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "invoice")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "project", nullable = false)
  private Project project;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "stage", nullable = false)
  private Stage stage;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private User client;

  @Column(name = "stage_total")
  private BigDecimal stageTotal;

  @Column(name = "budget_total")
  private BigDecimal budgetTotal;

  @Column(name = "percentage")
  private Double percentage;

  @Column(name = "issue_date")
  private LocalDate issueDate;

  @Column(name = "paid")
  private Boolean paid;

}