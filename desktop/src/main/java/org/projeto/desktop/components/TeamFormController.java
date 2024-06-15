package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class TeamFormController {

    @FXML
    public TextField daily_value;
    @FXML
    public DatePicker start_date;
    @FXML
    public DatePicker end_date;
    @FXML
    public ComboBox<String> leaderComboBox;
    @FXML
    public ComboBox<Integer> constructionComboBox;

    public boolean isFormCorrect() {
        return
                !daily_value.getText().trim().isEmpty()
                    && !leaderComboBox.getSelectionModel().isEmpty();
    }
    public boolean isFormCorrectAddingToConstruction() {
        return
                !daily_value.getText().trim().isEmpty()
                        && !start_date.getValue().toString().trim().isEmpty()
                        && !end_date.getValue().toString().trim().isEmpty()
                        && !constructionComboBox.getSelectionModel().isEmpty();
    }
    public void setValues( String dailyValue, LocalDate startDate, LocalDate endDate, String leaderName){
        this.daily_value.setText(dailyValue);
        this.start_date.setValue(startDate);
        this.end_date.setValue(endDate);
        this.leaderComboBox.setValue(leaderName);
    }
    public void clearValues(){
        this.daily_value.setText("");
        this.start_date.setValue(null);
        this.end_date.setValue(null);
        this.leaderComboBox.setValue(null);
    }
}
