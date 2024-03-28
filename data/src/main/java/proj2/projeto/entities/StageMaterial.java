package proj2.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stage_material")
public class StageMaterial {
  @EmbeddedId
  private StageMaterialId id;

  @MapsId("stage")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "stage", nullable = false)
  private Stage stage;

  @MapsId("material")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "material", nullable = false)
  private Material material;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}