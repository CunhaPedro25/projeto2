package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "construction")
public class Construction {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team")
  private Team team;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "budget")
  private Budget budget;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stage")
  private Stage stage;

  @Column(name = "finished")
  private Boolean finished;

  @Column(name = "last_update")
  private LocalDate lastUpdate;

  @Column(name = "start_date")
  private LocalDate startDate;

}