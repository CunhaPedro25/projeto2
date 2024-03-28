package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Worker;

@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "busy")
  private Boolean busy;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "leader", nullable = false)
  private Worker leader;

}