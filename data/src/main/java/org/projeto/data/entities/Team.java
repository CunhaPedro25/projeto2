package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "busy")
  private Boolean busy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leader")
  private User leader;

  @Column(name = "daily_value", nullable = false)
  private BigDecimal dailyValue;

  @OneToMany(mappedBy = "team")
  private Set<Construction> constructions = new LinkedHashSet<>();

  @OneToMany(mappedBy = "team")
  private Set<ConstructionTeam> constructionTeams = new LinkedHashSet<>();

  @OneToMany(mappedBy = "team")
  private Set<User> users = new LinkedHashSet<>();

}