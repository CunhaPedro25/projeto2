package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "construction")
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team")
  private Team team;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "budget")
  private Budget budget;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "stage")
  private Stage stage;

  @Column(name = "finished")
  private Boolean finished = false;

  @Column(name = "last_update")
  private LocalDate lastUpdate;

  @Column(name = "start_date")
  private LocalDate startDate;

  @OneToMany(mappedBy = "construction")
  private Set<Complaint> complaints = new LinkedHashSet<>();

  public Construction() {}

  public Construction(Team team, Budget budget, Stage stage, LocalDate startDate) {
    this.team = team;
    this.budget = budget;
    this.stage = stage;
    this.startDate = startDate;
    this.lastUpdate = LocalDate.now();
  }
}