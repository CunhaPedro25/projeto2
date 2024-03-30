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
@Table(name = "budget")
public class Budget {
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

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "project", nullable = false)
  private Project project;

  @OneToMany(mappedBy = "budget")
  private Set<Construction> constructions = new LinkedHashSet<>();

  @OneToMany(mappedBy = "budget")
  private Set<Stage> stages = new LinkedHashSet<>();

  public Budget() {}

  public Budget(Client client, Engineer engineer, Project project) {
    this.client = client;
    this.engineer = engineer;
    this.project = project;
  }

  public Budget(Client client, Engineer engineer, Project project, String filePath) {
    this.client = client;
    this.engineer = engineer;
    this.project = project;
    this.filePath = filePath;
  }

  public Budget(Client client, Engineer engineer, Project project, LocalDate createDate) {
    this.client = client;
    this.engineer = engineer;
    this.project = project;
    this.createDate = createDate;
  }

  public Budget(Client client, Engineer engineer, Project project, String filePath, LocalDate createDate) {
    this.client = client;
    this.engineer = engineer;
    this.project = project;
    this.filePath = filePath;
    this.createDate = createDate;
  }
}