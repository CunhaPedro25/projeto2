package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class MaterialFormController {
    @FXML
    public TextField materialNameInput;
    @FXML
    public TextField quantityInput;
    @FXML
    public TextField valueUnitInput;

    public boolean isFormCorrect() {
        return
                !materialNameInput.getText().trim().isEmpty()
                && !quantityInput.getText().trim().isEmpty()
                && !valueUnitInput.getText().trim().isEmpty();
    }
    public void setValues(String materialName, String quantity, String valueUnit){
        this.materialNameInput.setText(materialName);
        this.quantityInput.setText(quantity);
        this.valueUnitInput.setText(valueUnit);
    }
    public void clearValues(){
        this.materialNameInput.setText("");
        this.quantityInput.setText("");
        this.valueUnitInput.setText("");
    }
}
