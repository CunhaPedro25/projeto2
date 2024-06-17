package org.projeto.desktop.pages.dashboard.secretary;

import javafx.animation.PauseTransition;
import javafx.beans.Observable;
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
import org.projeto.data.entities.Team;
import org.projeto.data.services.TeamService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.modals.AddProjectModalController;
import org.projeto.desktop.pages.modals.AddTeamModalController;

import java.math.BigDecimal;
import java.util.List;

public class TeamsPageController {
    @FXML
    public TextField searchField;
    @FXML
    public TableView<Team> table;
    @FXML
    public TableColumn<Team,Integer> teamColumn;
    @FXML
    public TableColumn<Team,String> busyColumn;
    @FXML
    public TableColumn<Team, BigDecimal> dailyValueColumn;
    @FXML
    public TableColumn<Team,String> leaderColumn;
    @FXML
    public Button addJob;
    @FXML
    public Button newTeam;
    @FXML
    public Button delete;
    Team selectedTeam;
    @FXML
    public void initialize() {
        populateTableView();
    }

    private void populateTableView() {
        List<Team> teams = TeamService.getTeams();
        ObservableList<Team> teamObservableList = FXCollections.observableArrayList(teams);

        teamColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        busyColumn.setCellValueFactory(cellData -> {
            Team team = cellData.getValue();
            return new SimpleStringProperty(
                    team.getBusy() == null ? "Unknown" :
                            team.getBusy() ? "Yes" : "No"
            );
        });
        dailyValueColumn.setCellValueFactory(new PropertyValueFactory<>("dailyValue"));
        leaderColumn.setCellValueFactory(cellData -> {
            Team team = cellData.getValue();
            return new SimpleStringProperty(
                    team.getLeader() == null ? "Unknown" :
                            team.getLeader().getName()
            );
        });
        delete.setDisable(true);
        addJob.setDisable(true);

        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            Team selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedTeam = selected;

                // Create a PauseTransition for single click
                PauseTransition singleClickPause = new PauseTransition(Duration.millis(300));
                singleClickPause.setOnFinished(e -> {
                    if (event.getClickCount() == 1) {
                        System.out.println("Single click detected");
                        delete.setDisable(false);
                        addJob.setDisable(false);
                    }
                });


                // Handle double click immediately
                if (event.getClickCount() == 2) {
                    singleClickPause.stop();
                    System.out.println("Double click detected");
                    editTeam(selectedTeam);
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });


        // Populate the TableView
        table.setItems(teamObservableList);
    }
    @FXML
    private void editTeam(Team selectedProject) {
        try{
            SceneManager.openNewModal(
                    "pages/modals/add-team.fxml",
                    "Edit Team",
                    true,
                    controller -> {
                        AddTeamModalController editTeam = (AddTeamModalController) controller;
                        editTeam.enableEdit(selectedTeam);
                    });

        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to edit the project. Please try again.");
        }
        populateTableView();
    }
    @FXML
    public void openNewTeamModal(ActionEvent actionEvent) {
        SceneManager.openNewModal(
                "pages/modals/add-team.fxml",
                "Add Team",
                true,
                controller -> {
                    AddTeamModalController addTeam = (AddTeamModalController) controller;
                    addTeam.enableAddTeam();
                });
        populateTableView();
    }
    @FXML
    public void openNewTeamJobModal(ActionEvent actionEvent) {
        SceneManager.openNewModal(
                "pages/modals/add-team.fxml",
                "Add New Job",
                true,
                controller -> {
                    AddTeamModalController addJob = (AddTeamModalController) controller;
                    addJob.enableAddToConstruction(selectedTeam);
                });
        populateTableView();
    }
    @FXML
    public void delete(ActionEvent actionEvent) {
        if (SceneManager.openConfirmationAlert("Delete Team", "Are you sure you want to delete this team?")) {
            TeamService.delete(Long.valueOf(selectedTeam.getId()));
            populateTableView();
        }

    }
}
