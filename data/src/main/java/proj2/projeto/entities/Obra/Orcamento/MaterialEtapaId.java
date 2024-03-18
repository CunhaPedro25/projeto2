package proj2.projeto.entities.Obra.Orcamento;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialEtapaId implements Serializable {
    private static final long serialVersionUID = -4685895887586078137L;
    @Column(name = "etapa", nullable = false)
    private Integer etapa;

    @Column(name = "material", nullable = false)
    private Integer material;

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaterialEtapaId entity = (MaterialEtapaId) o;
        return Objects.equals(this.material, entity.material) &&
                Objects.equals(this.etapa, entity.etapa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, etapa);
    }

}