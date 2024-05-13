package org.projeto.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ConstructionMaterialId implements Serializable {
  private static final long serialVersionUID = -4846752530165043571L;
  @Column(name = "construction", nullable = false)
  private Integer construction;

  @Column(name = "material", nullable = false)
  private Integer material;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ConstructionMaterialId entity = (ConstructionMaterialId) o;
    return Objects.equals(this.material, entity.material) &&
            Objects.equals(this.construction, entity.construction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(material, construction);
  }

}