package proj2.projeto.entities.Equipa;

import jakarta.persistence.*;
import proj2.projeto.entities.Equipa.Obreiro.Obreiro;
import proj2.projeto.entities.Obra.Obra;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "equipa")
public class Equipa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chefe", nullable = false)
    private Obreiro chefe;

    @OneToMany(mappedBy = "equipa")
    private Set<Obra> obras = new LinkedHashSet<>();

    @OneToMany(mappedBy = "equipa")
    private Set<Obreiro> obreiros = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Obreiro getChefe() {
        return chefe;
    }

    public void setChefe(Obreiro chefe) {
        this.chefe = chefe;
    }

    public Set<Obra> getObras() {
        return obras;
    }

    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }

    public Set<Obreiro> getObreiros() {
        return obreiros;
    }

    public void setObreiros(Set<Obreiro> obreiros) {
        this.obreiros = obreiros;
    }

}