package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.entities.Team;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.ConstructionTeamService;
import org.projeto.data.services.TeamService;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.TeamFormController;

import java.math.BigDecimal;
import java.util.List;

public class AddTeamModalController {
    @FXML
    private TeamFormController teamFormController;
    @FXML
    public Button save;
    private boolean edit = false;
    private boolean addConstruction = false;
    Team selectedTeam = null;


    public void initialize() {
        List<String> names = TeamService.getLeaderNames();
        ObservableList<String> leaderNames = FXCollections.observableArrayList(names);
        teamFormController.leaderComboBox.setItems(leaderNames);
        List<String> constructionNames = ConstructionService.getAllConstructionNames();
        ObservableList<String> constructionNamesList = FXCollections.observableArrayList(constructionNames);
        teamFormController.constructionComboBox.setItems(constructionNamesList);

    }
    @FXML
    public void cancel(ActionEvent actionEvent) {
        SceneManager.closeWindow(save);
    }
    @FXML
    public void save(ActionEvent actionEvent) {
        try {
            if (edit){
                if (!teamFormController.isFormCorrect()){
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                } else {
                    selectedTeam.setDailyValue(new BigDecimal(teamFormController.daily_value.getText()));
                    selectedTeam.setLeader(UserService.findUserByName(teamFormController.leaderComboBox.getValue()));
                    TeamService.updateTeam(selectedTeam);
                    SceneManager.closeWindow(save);
                }
            }else if (addConstruction){
                if (!teamFormController.isFormCorrectAddingToConstruction()){
                    SceneManager.openErrorAlert("Error add", "Please fill all the required fields correctly");
                    return;
                } else {
                    ConstructionTeamService.addNew(ConstructionTeam.builder()
                            .construction(ConstructionService.findByName(teamFormController.constructionComboBox.getValue()))
                            .team(TeamService.getTeamById(Long.valueOf(selectedTeam.getId())))
                            .startDate(teamFormController.start_date.getValue())
                            .endDate(teamFormController.end_date.getValue())
                            .dailyValue(new BigDecimal(teamFormController.daily_value.getText()))
                            .build());
                    SceneManager.closeWindow(save);

                }
            }else {
                if (!teamFormController.isFormCorrect()){
                    SceneManager.openErrorAlert("Error add", "Please fill all the required fields correctly");
                    return;
                } else {
                    TeamService.addNew(Team.builder()
                            .dailyValue(new BigDecimal(teamFormController.daily_value.getText()))
                            .leader(UserService.findUserByName(teamFormController.leaderComboBox.getValue()))
                            .build());
                    SceneManager.closeWindow(save);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void enableAddTeam(){
        addConstruction = false;
        edit = false;
        System.out.println("add team is true CARALHO");
        teamFormController.leaderComboBox.setDisable(false);
        teamFormController.start_date.setDisable(true);
        teamFormController.end_date.setDisable(true);
        teamFormController.constructionComboBox.setVisible(false);
        teamFormController.constructionLabel.setVisible(false);
    }
    public void enableAddToConstruction(Team Team) {
        addConstruction = true;
        edit = false;
        selectedTeam = Team;
        System.out.println("add construction is true CARALHO");

        teamFormController.leaderComboBox.setDisable(true);
        teamFormController.leaderComboBox.setValue(selectedTeam.getLeader().getName());

        teamFormController.start_date.setDisable(false);
        teamFormController.start_date.setVisible(true);

        teamFormController.end_date.setDisable(false);
        teamFormController.end_date.setVisible(true);

        teamFormController.constructionComboBox.setVisible(true);
        teamFormController.constructionLabel.setVisible(true);
    }

    public void enableEdit(Team Team) {
        edit = true;
        addConstruction = false;

        System.out.println("edit team is true CARALHO");

        selectedTeam = Team;
        teamFormController.start_date.setDisable(true);
        teamFormController.end_date.setDisable(true);
        teamFormController.constructionComboBox.setVisible(false);
        teamFormController.constructionLabel.setVisible(false);


    }
}
