package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Worker;

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
  private Boolean busy =false;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "leader", nullable = false)
  private Worker leader;

  @OneToMany(mappedBy = "team")
  private Set<Construction> constructions = new LinkedHashSet<>();

  @OneToMany(mappedBy = "team")
  private Set<Worker> workers = new LinkedHashSet<>();

  public Team() {}
  public Team(Worker leader) {
    this.leader = leader;
  }
}