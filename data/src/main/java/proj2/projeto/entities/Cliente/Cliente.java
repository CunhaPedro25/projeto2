package proj2.projeto.entities.Cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proj2.projeto.entities.CodPostal.CodPostal;
import proj2.projeto.entities.Obra.Orcamento.Fatura;
import proj2.projeto.entities.Obra.Orcamento.Orcamento;
import proj2.projeto.entities.Obra.Reclamacao.Reclamacao;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name = "cliente")
public class Cliente {
    @Getter
    @Id
    @SequenceGenerator(
            name = "cliente_sequence",
            sequenceName = "cliente_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cliente_sequence"
    )
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telemovel", nullable = false)
    private Integer telemovel;

    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @Column(name = "rua", nullable = false, length = Integer.MAX_VALUE)
    private String rua;

    @Column(name = "numero_porta", nullable = false)
    private Integer numeroPorta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_postal", nullable = false)
    private CodPostal codPostal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cliente")
    private TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private Set<Fatura> faturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Orcamento> orcamentos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Reclamacao> reclamacaos = new LinkedHashSet<>();

    public Cliente(String nome, String email, Integer telemovel, String password, String rua, Integer numeroPorta, CodPostal codPostal, TipoCliente tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.telemovel = telemovel;
        this.password = password;
        this.rua = rua;
        this.numeroPorta = numeroPorta;
        this.codPostal = codPostal;
        this.tipoCliente = tipoCliente;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}