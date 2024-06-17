package org.projeto.data.repositories;

import org.projeto.data.entities.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Long> {
  Construction findById (Integer id);

  List<Construction> findByProject_Id(Integer id);

  List<Construction> findConstructionsByProject_IdAndState_Id(Integer project_id, Integer state_id);

    Construction findByName(String value);
}
