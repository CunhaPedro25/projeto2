package org.projeto.data.services;

import org.projeto.data.entities.Project;
import org.projeto.data.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    return ProjectService.projectRepository.findProjectByClient_id(clientID);
  }

  public static Project getProjectById(Integer projectID){
    return ProjectService.projectRepository.findById(projectID);
  }

  public static List<Project> getProjectsByEngineerID(Integer engineerID){
    return ProjectService.projectRepository.findProjectsByEngineer_Id(engineerID);
  }
  public static List<Project> getProjectsByConstructionType(Integer constructionTypeID){
    return ProjectService.projectRepository.findProjectsByConstructionType_Id(constructionTypeID);
  }
  public static List<Project> getAllProjects(){
    return ProjectService.projectRepository.findAll();
  }

  public static void addNew(Project newProject){
    Optional<Project> existingProject = ProjectService.projectRepository.findProjectByClient_IdAndRequirementsCreateDate(newProject.getClient().getId(), newProject.getRequirementsCreateDate());
    if(existingProject.isPresent() ){
      throw new IllegalStateException("Project already exists");
    }else {
      ProjectService.projectRepository.save(newProject);
    }
  }
  public static void delete(Long projectID){
    Optional<Project> existingProject = ProjectService.projectRepository.findById(projectID);
    if(existingProject.isPresent()){
      ProjectService.projectRepository.deleteById(projectID);
    }else {
      throw new IllegalStateException("That Project does not exist");

    }
  }

  public static void update(Project editProject) {
    ProjectService.projectRepository.save(editProject);
  }

  public static void updateProjectBudgetState(Integer projectID, Boolean status) {
    Project project = ProjectService.projectRepository.findById(projectID);
    project.setBudgetState(status);
    ProjectService.projectRepository.save(project);
  }

  public static List<Integer> getAllProjectsIds() {
      List<Project> projects = ProjectService.projectRepository.findAll();
      List<Integer> projectIds = new ArrayList<>();
      for (Project project : projects){
          projectIds.add(project.getId());
      }
      return projectIds;
  }
}
