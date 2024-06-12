package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "construction_material")
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionMaterial {
  @EmbeddedId
  private ConstructionMaterialId id;

  @MapsId("construction")
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "construction", nullable = false)
  private Construction construction;

  @MapsId("material")
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "material", nullable = false)
  private Material material;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}