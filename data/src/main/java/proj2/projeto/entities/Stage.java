package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.enums.State;

@Getter
@Setter
@Entity
@Table(name = "stage")
public class Stage {
  @Id
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

}