package proj2.projeto.entities.enums;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

}