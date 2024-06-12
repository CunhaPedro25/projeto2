package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "construction_team")
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionTeam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team")
  private Team team;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "construction")
  private Construction construction;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "days")
  private Integer days;

  @Column(name = "daily_value")
  private BigDecimal dailyValue;

}