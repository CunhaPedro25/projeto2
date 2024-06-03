package org.projeto.data.repositories;

import org.projeto.data.entities.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository  extends JpaRepository<Construction, Long> {
  Construction findById (Integer id);

  List<Construction> findbyProjectID(Integer id);

  List<Construction> findConstructionsByProjectAndAndState(Integer projectID, Integer stateID);

  List<Construction> findConstructionsByTeam_Id(Integer id);
}
