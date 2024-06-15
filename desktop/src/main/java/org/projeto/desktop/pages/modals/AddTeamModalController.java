package org.projeto.desktop.pages.modals;

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
import java.time.LocalDate;
import java.util.List;

public class AddTeamModalController {
    @FXML
    private TeamFormController teamFormController;
    @FXML
    public Button save;
    private boolean edit = false;
    private boolean addConstruction = false;
    Team editTeam = null;


    public void initialize() {
        List<String> leaderNames = TeamService.getLeaderNames();
        teamFormController.leaderComboBox.setItems((ObservableList<String>) leaderNames);
        List<Integer> constructionIds = ConstructionService.getAllConstructionIds();
        teamFormController.constructionComboBox.setItems((ObservableList<Integer>) constructionIds);

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
                    editTeam.setDailyValue(new BigDecimal(teamFormController.daily_value.getText()));
                    editTeam.setLeader(UserService.findUserByName(teamFormController.leaderComboBox.getValue()));
                    TeamService.updateTeam(editTeam);
                    SceneManager.closeWindow(save);
                }
            }else {
                if (!teamFormController.isFormCorrect()){
                    SceneManager.openErrorAlert("Error add", "Please fill all the required fields correctly");
                    return;
                } else {
                    Team team = new Team();
                    team.setDailyValue(new BigDecimal(teamFormController.daily_value.getText()));
                    team.setLeader(UserService.findUserByName(teamFormController.leaderComboBox.getValue()));
                    TeamService.addNew(team);
                    SceneManager.closeWindow(save);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableEdit(Team selectedTeam) {
        edit = true;
        editTeam = selectedTeam;

    }
}
