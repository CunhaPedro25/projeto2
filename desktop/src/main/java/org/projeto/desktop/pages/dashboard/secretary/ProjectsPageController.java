package org.projeto.desktop.pages.dashboard.secretary;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.projeto.data.services.ProjectService;

import java.time.LocalDate;
import java.util.List;

public class ProjectsPageController {
    @FXML
    public Button newProject;
    @FXML
    public TableColumn<Project, String> clientColumn;
    @FXML
    public TableColumn<Project, String> engineerColumn;
    @FXML
    public TableColumn<Project, LocalDate> create_dateColumn;
    @FXML
    public TableColumn<Project, String> acceptedColumn;

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
        create_dateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));

        acceptedColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            if(project.getAccepted() == null){
                return new SimpleStringProperty("Pending");
            }
            return new SimpleStringProperty(project.getAccepted().toString());
        });

        // Populate the TableView
        table.setItems(projectObservableList);
    }
}
