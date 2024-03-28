package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Client;

@Getter
@Setter
@Entity
@Table(name = "complaint")
public class Complaint {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "construction", nullable = false)
  private Construction construction;

  @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
  private String description;

}