package org.projeto.desktop.pages.dashboard.secretary;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.projeto.data.entities.Team;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.UserRepository;
import org.projeto.data.services.TeamService;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.RegisterFormController;
import org.projeto.desktop.pages.modals.AddUserModalController;
import org.projeto.desktop.pages.modals.AssignTeamModalController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;


@Component
public class WorkersPageController {
    @FXML
    TableView<User> table;
    @FXML
    TableColumn<User, String> name;
    @FXML
    TableColumn<User, String> currentTeam;
    @FXML
    TableColumn<User, String> phone;
    @FXML
    public Button assignTeam;
    User selectedUser = null;

    public void initialize(){
        populateTableView();
        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            User selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedUser = selected;

                // Create a PauseTransition for single click
                PauseTransition singleClickPause = new PauseTransition(Duration.millis(300));
                singleClickPause.setOnFinished(e -> {
                    if (event.getClickCount() == 1) {
                        System.out.println("Single click detected");
                        assignTeam.setDisable(false);
                    }
                });


                // Handle double click immediately
                if (event.getClickCount() == 2) {
                    singleClickPause.stop();
                    System.out.println("Double click detected");
                    openAssignTeamModal();
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });
    }

    private void populateTableView() {
        ObservableList<User> entities = FXCollections.observableArrayList(UserService.getAllUsers());
        FilteredList<User> filteredData = new FilteredList<>(entities, user -> user.getUserType().getType().equals("Worker"));
        table.setItems(filteredData);


        assignTeam.setDisable(true);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        currentTeam.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            if (user.getTeam() == null) {
                return new SimpleStringProperty("No team assigned");
            } else {
                Team team = TeamService.getTeamById(Long.valueOf(user.getTeam().getId()));
                return new SimpleStringProperty(team.getId().toString());
            }
        });
        table.setItems(entities);
    }

    @FXML
    public void openAssignTeamModal() {
        SceneManager.openNewModal("pages/modals/assignTeam-user.fxml",
                "Assign Team",
                true,
                controller -> {
                    AssignTeamModalController assignTeamModalController = (AssignTeamModalController) controller;
                    assignTeamModalController.setSelectedUser(selectedUser);
                });
    }
}