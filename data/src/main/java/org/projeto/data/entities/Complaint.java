package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "complaint")
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private User client;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "construction", nullable = false)
  private Construction construction;

  @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
  private String description;


}
