package org.projeto.desktop.pages.modals;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.User;
import org.projeto.data.services.TeamService;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.AssignTeamFormController;
import javafx.collections.FXCollections;


import java.util.List;

public class AssignTeamModalController {
    @FXML
    public Button save;
    @FXML
    private AssignTeamFormController assignTeamFormController;
    User selectedUser = null;
    public void initialize() {
        List<Integer> teamIDs = TeamService.getAllTeamsIds();
        ObservableList<Integer> teamIDsList = FXCollections.observableArrayList(teamIDs);
        assignTeamFormController.teamComboBox.setItems(teamIDsList);
    }
    @FXML
    public void cancel(ActionEvent actionEvent) {
        SceneManager.closeWindow(save);
    }
    @FXML
    public void save(ActionEvent actionEvent) throws Exception {
        if (!assignTeamFormController.isFormCorrect()){
            SceneManager.openErrorAlert("Error", "Please fill all the required fields correctly");
            return;
        } else {
            selectedUser.setTeam(TeamService.getTeamById(Long.valueOf(assignTeamFormController.teamComboBox.getValue())));
            UserService.update(selectedUser);
            SceneManager.closeWindow(save);
        }
    }
    public void setSelectedUser(User user) {
        this.selectedUser = user;
        assignTeamFormController.worker.setText(user.getName());
        assignTeamFormController.worker.setDisable(true);
    }
}
