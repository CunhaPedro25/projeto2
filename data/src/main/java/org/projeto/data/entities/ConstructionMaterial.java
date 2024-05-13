package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "construction_material")
public class ConstructionMaterial {
  @EmbeddedId
  private ConstructionMaterialId id;

  @MapsId("construction")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "construction", nullable = false)
  private Construction construction;

  @MapsId("material")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "material", nullable = false)
  private Material material;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}