package proj2.projeto.entities.Obra.Reclamacao;

import jakarta.persistence.*;
import proj2.projeto.entities.Cliente.Cliente;

@Entity
@Table(name = "reclamacao")
public class Reclamacao {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

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

}