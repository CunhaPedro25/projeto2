package proj2.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.data.entities.users.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "stage", nullable = false)
  private Stage stage;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private Client client;

  @Column(name = "total")
  private BigDecimal total;

  @Column(name = "issue_date")
  private LocalDate issueDate = LocalDate.now();

  @Column(name = "paid")
  private Boolean paid = false;

  public Invoice() {}

  public Invoice(Stage stage, Client client) {
    this.stage = stage;
    this.client = client;
  }

  public Invoice(Stage stage, Client client, LocalDate issueDate) {
    this.stage = stage;
    this.client = client;
    this.issueDate = issueDate;
  }
}