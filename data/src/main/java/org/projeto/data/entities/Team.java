package org.projeto.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "busy")
  private Boolean busy;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "leader")
  @JsonBackReference
  private User leader;

  @Column(name = "daily_value", nullable = false)
  private BigDecimal dailyValue;

  @JsonIgnore
  @OneToMany(mappedBy = "team")
  private Set<ConstructionTeam> constructionTeams = new LinkedHashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "team")
  private Set<User> users = new LinkedHashSet<>();

}