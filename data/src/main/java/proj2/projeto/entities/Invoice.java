package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "stage", nullable = false)
  private Stage stage;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private Client client;

  @Column(name = "issue_date")
  private LocalDate issueDate;

  @Column(name = "paid")
  private Boolean paid;

}