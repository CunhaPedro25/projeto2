package org.projeto.data.entities;

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

  public StageMaterial() {}

  public StageMaterial(Stage stage, Material material, Integer quantity) {
    this.id = new StageMaterialId(stage.getId(), material.getId());
    this.stage = stage;
    this.material = material;
    this.quantity = quantity;
  }
}