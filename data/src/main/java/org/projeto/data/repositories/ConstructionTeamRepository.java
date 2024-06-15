package org.projeto.data.repositories;

import org.projeto.data.entities.ConstructionTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionTeamRepository extends JpaRepository<ConstructionTeam, Long> {
    ConstructionTeam findById (Integer id);
    List<ConstructionTeam> findByConstruction_Id(Integer id);
    List<ConstructionTeam> findByTeam_Id(Integer id);
    List<ConstructionTeam> findByConstruction_IdAndTeam_Id(Integer construction_id, Integer team_id);
}
