package proj2.projeto.entities.enums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Client;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client_type")
public class ClientType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "type", nullable = false, length = Integer.MAX_VALUE)
  private String type;

  @OneToMany(mappedBy = "clientType")
  private Set<Client> clients = new LinkedHashSet<>();

  public ClientType() {}

  public ClientType(String type) {
    this.type = type;
  }
}