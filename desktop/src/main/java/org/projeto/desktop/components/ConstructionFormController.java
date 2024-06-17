package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConstructionFormController {
    @FXML
    public ComboBox<Integer> projectComboBox;
    @FXML
    public ComboBox<String> stageComboBox;
    @FXML
    public ComboBox<String> stateComboBox;
    @FXML
    public TextField name;

    public  boolean isFormCorrect() {
        return !projectComboBox.getSelectionModel().isEmpty();
    }
    public void setValues(Integer team, String stage, String state){
        this.projectComboBox.setValue(team);
        this.stageComboBox.setValue(stage);
        this.stateComboBox.setValue(state);
    }
    public void clearValues(){
        this.projectComboBox.setValue(null);
        this.stageComboBox.setValue(null);
        this.stateComboBox.setValue(null);
    }
}
