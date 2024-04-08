package proj2.projeto.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StageMaterialId implements Serializable {
  @Serial
  private static final long serialVersionUID = 2906110217833017574L;
  @Column(name = "stage", nullable = false)
  private Integer stage;

  @Column(name = "material", nullable = false)
  private Integer material;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    StageMaterialId entity = (StageMaterialId) o;
    return Objects.equals(this.stage, entity.stage) &&
            Objects.equals(this.material, entity.material);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stage, material);
  }

  public StageMaterialId() {}

  public StageMaterialId(Integer stage, Integer material) {
    this.stage = stage;
    this.material = material;
  }
}