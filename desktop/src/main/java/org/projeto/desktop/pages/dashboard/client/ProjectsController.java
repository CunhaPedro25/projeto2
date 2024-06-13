package org.projeto.desktop.pages.dashboard.client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.projeto.desktop.CurrentUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class ProjectsController {
  @FXML
  public Button newProject;
  @FXML
  public TableColumn<Project, Integer> engineerColumn;
  @FXML
  public TableColumn<Project, LocalDate> create_dateColumn;
  @FXML
  public TableColumn<Project, Boolean> accpetedColumn;

  @FXML
  private TableView<Project> table;

  public void initialize() {
    newProject.setVisible(false);
    populateTableView();
  }

  private void populateTableView() {
    // Get projects for the current user
    List<Project> clientProjects = ProjectService.getProjectsByClientID(CurrentUser.id);

    // Convert List to ObservableList
    ObservableList<Project> projectObservableList = FXCollections.observableArrayList(clientProjects);

    engineerColumn.setCellValueFactory(cellData -> {
      Project project = cellData.getValue();
      int engineerid = project.getEngineer().getId(); // Assuming getId() returns the team's ID
      return new SimpleIntegerProperty(engineerid).asObject();
    });

    create_dateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));

    accpetedColumn.setCellValueFactory(new PropertyValueFactory<>("accepted"));

    // Populate the TableView
    table.setItems(projectObservableList);
  }
}
