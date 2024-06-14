package org.projeto.desktop.pages.dashboard.secretary;

import javafx.beans.property.SimpleObjectProperty;
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
import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.projeto.desktop.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


        // Populate the TableView
        table.setItems(projectObservableList);
    }
    @FXML
    public void openNewProjectModal() {
        SceneManager.openNewModal("pages/modals/add-project.fxml", "Add Project", true);
    }

    public void delete(ActionEvent actionEvent) {
        Project project = table.getSelectionModel().getSelectedItem();
        ProjectService.delete(Long.valueOf(project.getId()));
        populateTableView();
    }
}
