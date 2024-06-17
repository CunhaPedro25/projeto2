package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class MaterialConstructionFormController {
    @FXML
    public TextField constructionName;
    @FXML
    public TextField quantity;
    @FXML
    public ComboBox<String> materialComboBox;

    public boolean isFormCorrect() {
        return
                !constructionName.getText().trim().isEmpty()
                && !quantity.getText().trim().isEmpty()
                && !materialComboBox.getValue().trim().isEmpty();
    }
    public void setValues(String constructionID, String quantity, String materialComboBox){
        this.constructionName.setText(constructionID);
        this.quantity.setText(quantity);
        this.materialComboBox.setValue(materialComboBox);
    }
    public void clearValues(){
        this.constructionName.setText("");
        this.quantity.setText("");
        this.materialComboBox.setValue("");
    }

}
