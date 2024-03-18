package proj2.projeto.entities.Engenheiro;

import jakarta.persistence.*;
import proj2.projeto.entities.Obra.Orcamento.Orcamento;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "engenheiro")
public class Engenheiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telemovel", nullable = false)
    private Integer telemovel;

    @OneToMany(mappedBy = "engenheiro")
    private Set<Orcamento> orcamentos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    public Set<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(Set<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

}