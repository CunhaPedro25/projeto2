package org.projeto.data.services;

import org.projeto.data.entities.Project;
import org.projeto.data.repositories.ConstructionRepository;
import org.projeto.data.repositories.documents.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
  private static ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository){
    ProjectService.projectRepository = projectRepository;
  }
  public static List<Project> getProjectsByClientID(Integer clientID){
    return projectRepository.findProjectByClient_id(clientID);
  }
}
