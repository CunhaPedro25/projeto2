package proj2.projeto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "zipcode")
public class Zipcode {
  @Id
  @Column(name = "id", nullable = false, length = Integer.MAX_VALUE)
  private String id;

  @Column(name = "district", nullable = false, length = Integer.MAX_VALUE)
  private String district;

  @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
  private String city;

  @Column(name = "locale", nullable = false, length = Integer.MAX_VALUE)
  private String locale;

}