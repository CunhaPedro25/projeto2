package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.projeto.data.entities.Construction;

public class ConstructionTeamFormController {
    @FXML
    public ComboBox<Integer> constructionsComboBox;
    @FXML
    public ComboBox<Integer> teamComboBox;
    @FXML
    public TextField daily_value;
    @FXML
    public TextField start_date;
    @FXML
    public TextField end_date;

    public boolean isFormCorrect() {
        return
                !constructionsComboBox.getSelectionModel().isEmpty()
                    && !teamComboBox.getSelectionModel().isEmpty()
                    && !daily_value.getText().trim().isEmpty()
                    && !start_date.getText().trim().isEmpty()
                    && !end_date.getText().trim().isEmpty();
    }
    public void setValues(Integer constructionID, Integer teamID, String dailyValue, String startDate, String endDate){
        this.constructionsComboBox.setValue(constructionID);
        this.teamComboBox.setValue(teamID);
        this.daily_value.setText(dailyValue);
        this.start_date.setText(startDate);
        this.end_date.setText(endDate);
    }
    public void clearValues(){
        this.constructionsComboBox.setValue(null);
        this.teamComboBox.setValue(null);
        this.daily_value.setText("");
        this.start_date.setText("");
        this.end_date.setText("");
    }
}
