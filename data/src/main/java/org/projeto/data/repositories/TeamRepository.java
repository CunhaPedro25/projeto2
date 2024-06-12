package org.projeto.data.repositories;

import org.projeto.data.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
  Team findById(Integer id);

  Optional<Team> findByLeader_Id(Integer leaderId);

}
