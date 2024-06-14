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
    public ComboBox<String> clientComboBox;

    @FXML
    public ComboBox<String> engineerComboBox;

    @FXML
    public TextField requirements_document;


    public boolean isFormCorrect() {
        return
                !clientComboBox.getSelectionModel().isEmpty()
                    && !engineerComboBox.getSelectionModel().isEmpty()
                    && !requirements_document.getText().trim().isEmpty();
    }


    public void setValues(String clientName, String engineerName, String requirementsDocument){
        this.clientComboBox.setValue(clientName);
        this.engineerComboBox.setValue(engineerName);
        this.requirements_document.setText(requirementsDocument);
    }

    public void clearValues(){
        this.clientComboBox.setValue(null);
        this.engineerComboBox.setValue(null);
        this.requirements_document.setText("");
    }
}



