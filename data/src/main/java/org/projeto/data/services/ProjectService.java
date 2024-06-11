package org.projeto.data.services;

import org.projeto.data.entities.Project;
import org.projeto.data.repositories.ProjectRepository;
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

  public List<Project> getProjectsByClientID(Integer clientID){
    return ProjectService.projectRepository.findProjectByClient_id(clientID);
  }

  public List<Project> getProjectsByEngineerID(Integer engineerID){
    return ProjectService.projectRepository.findProjectsByEngineer_Id(engineerID);
  }
  public List<Project> getProjectsByConstructionType(Integer constructionTypeID){
    return ProjectService.projectRepository.findProjectsByConstructionType_Id(constructionTypeID);
  }
  public List<Project> getAllProjects(){
    return ProjectService.projectRepository.findAll();
  }

  public void addNew(Project newProject){
    Optional<Project> existingProject = ProjectService.projectRepository.findProjectByClient_IdAndRequirementsCreateDate(newProject.getClient().getId(), newProject.getRequirementsCreateDate());
    if(existingProject.isPresent() ){
      throw new IllegalStateException("Project already exists");
    }else {
      ProjectService.projectRepository.save(newProject);
    }
  }
  public void delete(Long projectID){
    Optional<Project> existingProject = ProjectService.projectRepository.findById(projectID);
    if(existingProject.isPresent()){
      ProjectService.projectRepository.deleteById(projectID);
    }else {
      throw new IllegalStateException("That Project does not exist");

    }
  }
}
