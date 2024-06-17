package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AssignTeamFormController {
    @FXML
    public TextField worker;
    @FXML
    public ComboBox<Integer> teamComboBox;
    public boolean isFormCorrect() {
        return !teamComboBox.getSelectionModel().isEmpty();
    }

    public void setValues(String workerName){
        this.worker.setText(workerName);
    }
    public void clearValues(){
        this.worker.setText("");
        this.teamComboBox.setValue(null);
    }
}
