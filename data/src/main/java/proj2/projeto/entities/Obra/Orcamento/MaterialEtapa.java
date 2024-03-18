package proj2.projeto.entities.Obra.Orcamento;

import jakarta.persistence.*;

@Entity
@Table(name = "material_etapa")
public class MaterialEtapa {
    @EmbeddedId
    private MaterialEtapaId id;

    @MapsId("etapa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "etapa", nullable = false)
    private Etapa etapa;

    @MapsId("material")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material", nullable = false)
    private Material material;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public MaterialEtapaId getId() {
        return id;
    }

    public void setId(MaterialEtapaId id) {
        this.id = id;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}