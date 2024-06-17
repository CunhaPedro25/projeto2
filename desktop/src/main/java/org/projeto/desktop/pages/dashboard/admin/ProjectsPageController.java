package org.projeto.desktop.pages.dashboard.admin;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.modals.AddProjectModalController;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class ProjectsPageController {
    @FXML
    public Button newProject;
    @FXML
    public TableColumn<Project, String> clientColumn;
    @FXML
    public TableColumn<Project, String> engineerColumn;
    @FXML
    public TableColumn<Project,String> requirements_create_dateColumn;
    @FXML
    public TableColumn<Project, LocalDate> budget_create_dateColumn;
    @FXML
    public TableColumn<Project, String> acceptedColumn;
    public TextField searchField;
    public Button delete;

    @FXML
    private TableView<Project> table;

    Project selectedProject;

    public void initialize() {
        populateTableView();
    }

    private void populateTableView() {
        // Get projects for the current user
        List<Project> projects = ProjectService.getAllProjects();

        // Convert List to ObservableList
        ObservableList<Project> projectObservableList = FXCollections.observableArrayList(projects);

        clientColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            String client = project.getClient().getName();
            return new SimpleStringProperty(client);
        });
        engineerColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            if(project.getEngineer() == null) return new SimpleStringProperty("Pending");
            String engineer = project.getEngineer().getName();
            return new SimpleStringProperty(engineer);
        });
        requirements_create_dateColumn.setCellValueFactory(new PropertyValueFactory<>("requirementsCreateDate"));
        budget_create_dateColumn.setCellValueFactory(new PropertyValueFactory<>("requirementsCreateDate"));


        acceptedColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            return new SimpleStringProperty(
                    project.getBudgetState() == null ? "Pending" :
                            project.getBudgetState() ? "Accepted" : "Rejected"
            );
        });

        delete.setDisable(true);

        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            Project selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedProject = selected;

                // Create a PauseTransition for single click
                PauseTransition singleClickPause = new PauseTransition(Duration.millis(300));
                singleClickPause.setOnFinished(e -> {
                    if (event.getClickCount() == 1) {
                        System.out.println("Single click detected");
                        delete.setDisable(false);
                    }
                });


                // Handle double click immediately
                if (event.getClickCount() == 2) {
                    singleClickPause.stop();
                    System.out.println("Double click detected");
                    editProject(selectedProject);
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });


        // Populate the TableView
        table.setItems(projectObservableList);
    }
    @FXML
    private void editProject(Project selectedProject) {
        try{
            SceneManager.openNewModal(
                "pages/modals/add-project.fxml",
                "Edit Project",
                true,
                controller -> {
                    AddProjectModalController editProject = (AddProjectModalController) controller;
                    editProject.enableEdit(selectedProject);
            });

        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to edit the project. Please try again.");
        }
    }

    @FXML
    public void openNewProjectModal() {
        SceneManager.openNewModal("pages/modals/add-project.fxml", "Add Project", true);
    }

    public void delete(ActionEvent actionEvent) {
        if(SceneManager.openConfirmationAlert("Delete Project", "Are you sure you want to delete this project?")){
            Project project = table.getSelectionModel().getSelectedItem();
            ProjectService.delete(Long.valueOf(project.getId()));
            populateTableView();
        }

    }
}
