package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.entities.Team;
import org.projeto.data.services.*;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.ConstructionFormController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class AddConstructionModalController {
    @FXML
    public Button save;
    @FXML
    public Button cancel;
    @FXML
    private ConstructionFormController constructionFormController;
    boolean edit = false;
    Construction editConstruction = null;
    public void initialize() {
        List<String> states = StateService.getAllStatesDescription();
        List<String> stages = StageService.getAllStageNames();
        List<Integer> projects = ProjectService.getAllProjectsIds();
        constructionFormController.stateComboBox.setItems(FXCollections.observableArrayList(states));
        constructionFormController.stageComboBox.setItems(FXCollections.observableArrayList(stages));
        constructionFormController.projectComboBox.setItems(FXCollections.observableArrayList(projects));
    }
    @FXML
    public void save() {
        System.out.println("save been clicked");
        try {
         if (edit){
             System.out.println("editing is true");
             if (!constructionFormController.isFormCorrect()){
                 System.out.println("form is not correct");
                 SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                 return;
             } else {
                    System.out.println("form is correct");
                    editConstruction.setState(StateService.getStateByDescription(constructionFormController.stateComboBox.getValue()));
                    editConstruction.setStage(StageService.getStageByName(constructionFormController.stageComboBox.getValue()));
                    ConstructionService.update(editConstruction);
                    SceneManager.openConfirmationAlert("Success", "Construction updated successfully");
                    edit = false;
                    SceneManager.closeWindow(save);
             }

         }else {
             System.out.println("editing is false");
             if (!constructionFormController.isFormCorrect()){
                 System.out.println("form is not correct");
                 SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                 return;
             } else {
                 System.out.println("form is correct");
                 LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
                 ZoneId zoneId = ZoneId.systemDefault();
                 Instant instant = localDateTime.atZone(zoneId).toInstant();
                 ConstructionService.addNew(Construction.builder()
                         .project(ProjectService.getProjectById(constructionFormController.projectComboBox.getValue()))
                         .state(StateService.getStateByDescription(constructionFormController.stateComboBox.getValue()))
                         .stage(StageService.getStageByName(constructionFormController.stageComboBox.getValue()))
                         .stageBudget(BigDecimal.valueOf(Double.parseDouble(constructionFormController.stageBudget.getText())))
                         .lastUpdate(instant)
                         .build());
             }
         }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void cancel() {
        SceneManager.closeWindow(save);
    }

    public void enableEdit(Construction selectedConstruction) {
        edit = true;
        constructionFormController.stateComboBox.setValue(selectedConstruction.getState().getDescription());
        constructionFormController.stageComboBox.setValue(selectedConstruction.getStage().getName());
        constructionFormController.projectComboBox.setValue(selectedConstruction.getProject().getId());
        constructionFormController.projectComboBox.setDisable(true);
        System.out.println("editing is true ");
        editConstruction = selectedConstruction;

    }
}