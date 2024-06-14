package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.projeto.data.entities.ConstructionType;
import org.projeto.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectFormController {
    @FXML
    private ComboBox<String> clientComboBox;

    @FXML
    private ComboBox<String> engineerComboBox;

    @FXML
    public ComboBox<String> budgetState;
    @FXML
    public TextField budgetDocument;
    @FXML
    public TextField requirements_document;

    @FXML
    public TextField budget;

    public boolean isFormCorrect() {
        return
                !clientComboBox.getSelectionModel().isEmpty()
                    && !engineerComboBox.getSelectionModel().isEmpty()
                    && !budgetState.getSelectionModel().isEmpty();

    }
/*    public boolean isFormCorrectEdit() {
        return
                email.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                        && phone.getText().matches("\\d{9}")
                        && !firstName.getText().trim().isEmpty()
                        && !lastName.getText().trim().isEmpty();
    }*/

    public void setValues(String clientName, String engineerName, String constructionTypeName, String budgetState){
        this.clientComboBox.setValue(clientName);
        this.engineerComboBox.setValue(engineerName);
        this.budgetState.setValue(budgetState);
    }

    public void clearValues(){
        this.clientComboBox.setValue(null);
        this.engineerComboBox.setValue(null);
        this.budgetState.setValue(null);
    }
}



