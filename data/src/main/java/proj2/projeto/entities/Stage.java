package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.enums.State;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stage")
public class Stage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "budget", nullable = false)
  private Budget budget;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "state", nullable = false)
  private State state;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @OneToMany(mappedBy = "stage")
  private Set<Construction> constructions = new LinkedHashSet<>();

  @OneToMany(mappedBy = "stage")
  private Set<Invoice> invoices = new LinkedHashSet<>();

  @OneToMany(mappedBy = "stage")
  private Set<StageMaterial> stageMaterials = new LinkedHashSet<>();

  public Stage() {}

  public Stage(Budget budget, State state, String description) {
    this.budget = budget;
    this.state = state;
    this.description = description;
  }
}