package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import proj2.projeto.entities.users.Client;
import proj2.projeto.entities.users.Engineer;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client", nullable = false)
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "engineer", nullable = false)
  private Engineer engineer;

  @Column(name = "file_path", length = Integer.MAX_VALUE)
  private String filePath;

  @Column(name = "create_date")
  private LocalDate createDate = LocalDate.now();

  @Column(name = "accepted")
  private Boolean accepted;

  @OneToMany(mappedBy = "project")
  private Set<Budget> budgets = new LinkedHashSet<>();

  public Project() {}

  public Project(Client client, Engineer engineer) {
    this.client = client;
    this.engineer = engineer;
  }

  public Project(Client client, Engineer engineer, String filePath) {
    this.client = client;
    this.engineer = engineer;
    this.filePath = filePath;
  }

  public Project(Client client, Engineer engineer, LocalDate createDate) {
    this.client = client;
    this.engineer = engineer;
    this.createDate = createDate;
  }

  public Project(Client client, Engineer engineer, String filePath, LocalDate createDate) {
    this.client = client;
    this.engineer = engineer;
    this.filePath = filePath;
    this.createDate = createDate;
  }
}