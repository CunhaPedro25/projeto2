package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.projeto.desktop.SceneManager;
import org.springframework.stereotype.Component;

@Component
public class ProjectFormController {
    @FXML
    public ComboBox<String> clientComboBox;

    @FXML
    public ComboBox<String> engineerComboBox;

    @FXML
    public Button requirements_document;

    @FXML
    public TextField budget;


    public boolean isFormCorrect() {
        return
                !clientComboBox.getSelectionModel().isEmpty()
                    && !engineerComboBox.getSelectionModel().isEmpty();
    }


    public void setValues(String clientName, String engineerName){
        this.clientComboBox.setValue(clientName);
        this.engineerComboBox.setValue(engineerName);
    }

    public void clearValues(){
        this.clientComboBox.setValue(null);
        this.engineerComboBox.setValue(null);
    }

    @FXML
    public void openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(SceneManager.getStage(engineerComboBox));
    }
}



