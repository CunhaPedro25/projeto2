package proj2.projeto.entities.Obra.Orcamento;
import jakarta.persistence.*;
import proj2.projeto.entities.Obra.Estado.Estado;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "etapa")
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orcamento", nullable = false)
    private Orcamento orcamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estado", nullable = false)
    private Estado estado;

    @Column(name = "descricao", nullable = false, length = Integer.MAX_VALUE)
    private String descricao;

    @OneToMany(mappedBy = "etapa")
    private Set<Fatura> faturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "etapa")
    private Set<MaterialEtapa> materialEtapas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(Set<Fatura> faturas) {
        this.faturas = faturas;
    }

    public Set<MaterialEtapa> getMaterialEtapas() {
        return materialEtapas;
    }

    public void setMaterialEtapas(Set<MaterialEtapa> materialEtapas) {
        this.materialEtapas = materialEtapas;
    }

}