package proj2.projeto.entities.CodPostal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cod_postal")
public class CodPostal {
    @Id
    @Column(name = "cod_postal", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}