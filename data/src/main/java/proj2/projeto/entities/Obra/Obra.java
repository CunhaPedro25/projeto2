package proj2.projeto.entities.Obra;

import jakarta.persistence.*;
import proj2.projeto.entities.Equipa.Equipa;

@Entity
@Table(name = "obra")
public class    Obra {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipa", nullable = false)
    private Equipa equipa;

    @Column(name = "etapa")
    private Integer etapa;

    @Column(name = "concluida")
    private Boolean concluida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

}