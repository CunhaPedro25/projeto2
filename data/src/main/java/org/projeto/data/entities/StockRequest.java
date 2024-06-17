package org.projeto.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stock_request")
public class StockRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "construction_team", nullable = false)
    private ConstructionTeam constructionTeam;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material", nullable = false)
    private Material material;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "accepted")
    private Boolean accepted;

}