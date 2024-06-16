package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.StockRequest;

import java.util.Optional;

@Repository
public interface StockRequestRepository extends JpaRepository<StockRequest, Long> {
    StockRequest findById (Integer id);
    Optional<StockRequest> findByMaterialId(Integer material_id);
    Optional<StockRequest> findByConstructionTeamId(Integer constructionTeam_id);
}
