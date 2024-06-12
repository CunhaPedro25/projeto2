package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

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