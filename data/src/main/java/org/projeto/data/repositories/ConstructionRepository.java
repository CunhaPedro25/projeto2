package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.Construction;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionRepository  extends JpaRepository<Construction, Long> {
  Construction findById (Integer id);

  List<Construction> findConstructionsByTeam_Id(Integer id);

  Optional<Construction> findByBudget_Id (Integer id);

  List<Construction> findConstructionsByBudget_Id(Integer id);
}