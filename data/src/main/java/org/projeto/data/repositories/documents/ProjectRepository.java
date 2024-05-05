package org.projeto.data.repositories.documents;

import org.projeto.data.entities.Budget;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends DocumentRepository<Project> {

  List<Project> findProjectByClient_id(Integer id);
}
