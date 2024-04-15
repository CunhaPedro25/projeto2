package org.projeto.data.entities;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.users.Client;

@Getter
@Setter
@Entity
@Table(name = "complaint")
public class Complaint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public Complaint() {}

  public Complaint(Client client, Construction construction, String description) {
    this.client = client;
    this.construction = construction;
    this.description = description;
  }
}