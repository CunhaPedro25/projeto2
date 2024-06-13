package org.projeto.desktop.pages.dashboard.client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.ProjectService;
import org.projeto.desktop.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ConstructionsController {
  private  ProjectService projectService;
  public Button newConstruction;

  @FXML
  private TableView<Construction> table;

  @FXML
  private TableColumn<Construction, Integer> teamColumn;

  @FXML
  private TableColumn<Construction, Boolean> finishedColumn;

  @FXML
  private TableColumn<Construction, Integer> stageColumn;

  @FXML
  private TableColumn<Construction, Date> lastUpdateColumn;
    @Autowired
    public ConstructionsController(ProjectService projectService) {
        this.projectService = projectService;
    }
    public ConstructionsController() {

    }

    public void initialize() {
    newConstruction.setVisible(false);
    populateTableView();

  }

  private void populateTableView() {
// Get constructions for the current user
    List<Construction> clientConstructions = new ArrayList<>();
    List<Project> clientProjects = ProjectService.getProjectsByClientID(CurrentUser.id);

    if (clientProjects != null) {
      for (Project project : clientProjects) {
        Set<Construction> constructions = project.getConstructions();
        if (constructions != null) {
          clientConstructions.addAll(constructions);
        } else {
          System.err.println("Warning: Project ID " + project.getId() + " has no constructions.");
        }
      }
    } else {
      System.err.println("Error: No projects found for client ID " + CurrentUser.id);
    }

    // Convert List to ObservableList
    ObservableList<Construction> constructionObservableList = FXCollections.observableArrayList(clientConstructions);
    teamColumn.setCellValueFactory(cellData -> {
      Construction construction = cellData.getValue();
      int teamId = construction.getTeam().getId(); // Assuming getId() returns the team's ID
      return new SimpleIntegerProperty(teamId).asObject();
    });

    finishedColumn.setCellValueFactory(new PropertyValueFactory<>("finished"));

    stageColumn.setCellValueFactory(cellData -> {
      Construction construction = cellData.getValue();
      int stageId = construction.getTeam().getId(); // Assuming getId() returns the team's ID
      return new SimpleIntegerProperty(stageId).asObject();
    });

    lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));


    // Populate the TableView
    table.setItems(constructionObservableList);
  }
}