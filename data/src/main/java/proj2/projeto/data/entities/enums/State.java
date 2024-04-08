package proj2.projeto.data.entities.enums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.data.entities.Stage;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @OneToMany(mappedBy = "state")
  private Set<Stage> stages = new LinkedHashSet<>();

  public State() {}

  public State(String description) {
    this.description = description;
  }
}