package org.projeto.data.repositories;

import org.projeto.data.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findProjectByClient_id(Integer id);

//  Optional<Project> findProjectByClient_IdAndCreateDate(Integer client_id, LocalDate createDate);
}
