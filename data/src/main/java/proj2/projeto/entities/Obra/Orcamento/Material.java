package proj2.projeto.entities.Obra.Orcamento;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "valor_unidade", nullable = false)
    private Double valorUnidade;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "material")
    private Set<MaterialEtapa> materialEtapas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(Double valorUnidade) {
        this.valorUnidade = valorUnidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Set<MaterialEtapa> getMaterialEtapas() {
        return materialEtapas;
    }

    public void setMaterialEtapas(Set<MaterialEtapa> materialEtapas) {
        this.materialEtapas = materialEtapas;
    }

}