package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    public ComboBox<String> constructionComboBox;
    @FXML
    public Label constructionLabel;

    public boolean isFormCorrect() {
        return
                !daily_value.getText().trim().isEmpty()
                    && !leaderComboBox.getSelectionModel().isEmpty();
    }
    public boolean isFormCorrectAddingToConstruction() {
        LocalDate startDate = start_date.getValue();
        LocalDate endDate = end_date.getValue();

        return !daily_value.getText().trim().isEmpty()
                && startDate != null
                && endDate != null
                && !startDate.toString().trim().isEmpty()
                && !endDate.toString().trim().isEmpty()
                && endDate.isAfter(startDate)
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
