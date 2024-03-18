package proj2.projeto.entities.Cliente;

import jakarta.persistence.*;
import proj2.projeto.entities.CodPostal.CodPostal;
import proj2.projeto.entities.Obra.Orcamento.Fatura;
import proj2.projeto.entities.Obra.Orcamento.Orcamento;
import proj2.projeto.entities.Obra.Reclamacao.Reclamacao;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumeroPorta() {
        return numeroPorta;
    }

    public void setNumeroPorta(Integer numeroPorta) {
        this.numeroPorta = numeroPorta;
    }

    public CodPostal getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(CodPostal codPostal) {
        this.codPostal = codPostal;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Set<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(Set<Fatura> faturas) {
        this.faturas = faturas;
    }

    public Set<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(Set<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public Set<Reclamacao> getReclamacaos() {
        return reclamacaos;
    }

    public void setReclamacaos(Set<Reclamacao> reclamacaos) {
        this.reclamacaos = reclamacaos;
    }

}