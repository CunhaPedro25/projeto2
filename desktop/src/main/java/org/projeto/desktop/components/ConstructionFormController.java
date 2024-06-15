package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.projeto.data.entities.Stage;
import org.projeto.data.entities.State;
import org.projeto.data.entities.Team;

public class ConstructionFormController {
    @FXML
    public ComboBox<Integer> projectComboBox;
    @FXML
    public ComboBox<String> stageComboBox;
    @FXML
    public ComboBox<String> stateComboBox;
    @FXML
    public TextField stageBudget;
    public  boolean isFormCorrect() {
        return
                    !stageComboBox.getSelectionModel().isEmpty()
                    && !stateComboBox.getSelectionModel().isEmpty()
                    && !stageBudget.getText().trim().isEmpty();
    }
    public void setValues(Integer team, String stage, String state, String budget){
        this.projectComboBox.setValue(team);
        this.stageComboBox.setValue(stage);
        this.stateComboBox.setValue(state);
        this.stageBudget.setText(budget);
    }
    public void clearValues(){
        this.projectComboBox.setValue(null);
        this.stageComboBox.setValue(null);
        this.stateComboBox.setValue(null);
        this.stageBudget.setText("");
    }
}
