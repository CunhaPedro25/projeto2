package org.projeto.desktop.pages.modals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.ConstructionTeamService;
import org.projeto.data.services.TeamService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.ConstructionTeamFormController;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddConstructionTeamModalController {
    private ConstructionTeamFormController constructionTeamFormController;
    private boolean edit = false;
    @FXML
    public Button save;
    @FXML
    public void cancel(ActionEvent actionEvent) {
        SceneManager.closeWindow(save);
    }
    @FXML
    public void save(ActionEvent actionEvent) {
        try {
            if (edit){
                if (!constructionTeamFormController.isFormCorrect()){
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                } else {
                    ConstructionTeamService.addNew(ConstructionTeam.builder()
                            .construction(ConstructionService.findById(constructionTeamFormController.constructionsComboBox.getValue()))
                            .team(TeamService.getTeamById(Long.valueOf(constructionTeamFormController.teamComboBox.getValue())))
                            .dailyValue(BigDecimal.valueOf(Double.parseDouble(constructionTeamFormController.daily_value.getText())))
                            .startDate(LocalDate.parse(constructionTeamFormController.start_date.getText()))
                            .endDate(LocalDate.parse(constructionTeamFormController.end_date.getText()))
                            .build());
                }

            }else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableEdit(Integer teamID,Integer constructionID) {
        edit = true;
        constructionTeamFormController.teamComboBox.setValue(teamID);
        constructionTeamFormController.constructionsComboBox.setValue(constructionID);
        constructionTeamFormController.teamComboBox.setDisable(true);
        constructionTeamFormController.constructionsComboBox.setDisable(true);
    }
}
