package proj2.projeto.entities.Obra.Orcamento;

import jakarta.persistence.*;
import proj2.projeto.entities.Cliente.Cliente;
import proj2.projeto.entities.Engenheiro.Engenheiro;

@Entity
@Table(name = "orcamento")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "engenheiro", nullable = false)
    private Engenheiro engenheiro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Engenheiro getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(Engenheiro engenheiro) {
        this.engenheiro = engenheiro;
    }

}