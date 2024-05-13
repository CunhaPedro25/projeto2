package org.projeto.data.services;

import org.projeto.data.entities.Project;
import org.projeto.data.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository){
    this.projectRepository = projectRepository;
  }

  public List<Project> getProjectsByClientID(Integer clientID){
    return this.projectRepository.findProjectByClient_id(clientID);
  }

  public List<Project> getAllProjects(){
    return this.projectRepository.findAll();
  }

  public void addNew(Project newProject){
//    Optional<Project> existingProject = this.projectRepository.findProjectByClient_IdAndCreateDate(newProject.getClient().getId(), newProject.getCreateDate());
//    Optional<Project> exisingFile = this.projectRepository.findByFilePath(newProject.getFilePath());
//    if(existingProject.isPresent() || exisingFile.isPresent()){
//      throw new IllegalStateException("Project already exists");
//    }else {
//      this.projectRepository.save(newProject);
//    }
  }
}
