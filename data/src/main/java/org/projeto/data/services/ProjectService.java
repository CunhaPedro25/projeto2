package org.projeto.data.services;

import org.projeto.data.entities.Project;
import org.projeto.data.repositories.documents.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public static List<Project> getAllProjects(){
    return projectRepository.findAll();
  }

  public static void addNew(Project newProject){
    Optional<Project> existingProject = projectRepository.findProjectByClient_IdAndCreateDate(newProject.getClient().getId(), newProject.getCreateDate());
    Optional<Project> exisingFile = projectRepository.findByFilePath(newProject.getFilePath());
    if(existingProject.isPresent() || exisingFile.isPresent()){
      throw new IllegalStateException("Project already exists");
    }else {
      projectRepository.save(newProject);
    }
  }
}
