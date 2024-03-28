package proj2.projeto.entities;

import jakarta.persistence.*;
import proj2.projeto.entities.Obra.Orcamento.MaterialEtapa;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

//TODO
@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "valor_unidade", nullable = false)
    private Double valorUnidade;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "material")
    private Set<MaterialEtapa> materialEtapas = new LinkedHashSet<>();

  @Column(name = "value_unit", nullable = false)
  private BigDecimal valueUnit;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getValueUnit() {
    return valueUnit;
  }

  public void setValueUnit(BigDecimal valueUnit) {
    this.valueUnit = valueUnit;
  }

  public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(Double valorUnidade) {
        this.valorUnidade = valorUnidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Set<MaterialEtapa> getMaterialEtapas() {
        return materialEtapas;
    }

    public void setMaterialEtapas(Set<MaterialEtapa> materialEtapas) {
        this.materialEtapas = materialEtapas;
    }

}