package proj2.projeto.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.Team;

@Getter
@Setter
@Entity
@Table(name = "worker")
public class Worker {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", length = 70)
  private String name;

  @Column(name = "email", nullable = false, length = 254)
  private String email;

  @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
  private String password;

  @Column(name = "phone", nullable = false)
  private Integer phone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team")
  private Team team;

  @Column(name = "active")
  private Boolean active;

}