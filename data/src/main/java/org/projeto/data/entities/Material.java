package org.projeto.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "material")
@NoArgsConstructor
@AllArgsConstructor
public class Material {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
  private String name;

  @Column(name = "value_unit", nullable = false)
  private BigDecimal valueUnit;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @JsonIgnore
  @OneToMany(mappedBy = "material")
  private Set<ConstructionMaterial> constructionMaterials = new LinkedHashSet<>();

}