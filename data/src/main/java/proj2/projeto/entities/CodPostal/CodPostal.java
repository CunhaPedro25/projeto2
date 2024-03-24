package proj2.projeto.entities.CodPostal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import proj2.projeto.entities.Cliente.Cliente;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cod_postal")
public class CodPostal {
    @Id
    @Column(name = "cod_postal", nullable = false)
    private Integer id;

    @Column(name = "distrito", nullable = false, length = Integer.MAX_VALUE)
    private String distrito;

    @OneToMany(mappedBy = "codPostal")
    private Set<Cliente> clientes = new LinkedHashSet<>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public CodPostal(Integer id, String distrito) {
        this.id = id;
        this.distrito = distrito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}