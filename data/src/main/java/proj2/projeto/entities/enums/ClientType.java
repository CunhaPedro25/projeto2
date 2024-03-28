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
@Table(name = "client_type")
public class ClientType {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "type", nullable = false, length = Integer.MAX_VALUE)
  private String type;

}