package org.projeto.data.repositories.documents;

import org.projeto.data.entities.Budget;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends DocumentRepository<Project> {

  List<Project> findProjectByClient_id(Integer id);

  Optional<Project> findProjectByClient_IdAndCreateDate(Integer client_id, LocalDate createDate);
}
