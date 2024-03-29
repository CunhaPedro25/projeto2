package proj2.projeto.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.Team;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "worker")
public class Worker extends User{
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team")
  private Team team;

  @OneToMany(mappedBy = "leader")
  private Set<Team> teams = new LinkedHashSet<>();

  public Worker() {}

  public Worker(String name, String email, String password, String phone) {
    super(name, email, password, phone);
  }

  public Worker(String name, String email, String password, String phone, Team team) {
    super(name, email, password, phone);
    this.team = team;
  }
}