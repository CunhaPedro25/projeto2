package proj2.projeto.entities.Obra.Estado;

import jakarta.persistence.*;
import proj2.projeto.entities.Obra.Orcamento.Etapa;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = Integer.MAX_VALUE)
    private String estado;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @OneToMany(mappedBy = "estado")
    private Set<Etapa> etapas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(Set<Etapa> etapas) {
        this.etapas = etapas;
    }

}