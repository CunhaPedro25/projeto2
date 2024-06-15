package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.Material;
import org.projeto.data.services.*;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.ConstructionFormController;
import org.projeto.desktop.components.MaterialFormController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class AddMaterialModalController {
    @FXML
    public Button save;
    @FXML
    public Button cancel;
    @FXML
    private MaterialFormController materialFormController;
    boolean edit = false;
    Material editMaterial = null;
    public void initialize() {

    }
    @FXML
    public void save() {
        System.out.println("save been clicked");
        try {
            if (edit){
                System.out.println("editing is true");
                if (!materialFormController.isFormCorrect()){
                    System.out.println("form is not correct");
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                } else {
                    System.out.println("form is correct");
                    editMaterial.setName(materialFormController.materialNameInput.getText());
                    editMaterial.setQuantity(Integer.valueOf(materialFormController.quantityInput.getText()));
                    editMaterial.setValueUnit(new BigDecimal(materialFormController.valueUnitInput.getText()));
                    MaterialService.update(editMaterial);
                    SceneManager.openConfirmationAlert("Success", "Construction updated successfully");
                    edit = false;
                    SceneManager.closeWindow(save);
                }

            }else {
                System.out.println("editing is false");
                if (!materialFormController.isFormCorrect()){
                    System.out.println("form is not correct");
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                } else {
                    System.out.println("form is correct");
                        MaterialService.addNew(Material.builder()
                            .name(materialFormController.materialNameInput.getText())
                            .quantity(Integer.valueOf(materialFormController.quantityInput.getText()))
                            .valueUnit(new BigDecimal(materialFormController.valueUnitInput.getText()))
                            .build());
                SceneManager.openConfirmationAlert("Success", "Material added successfully");
                SceneManager.closeWindow(save);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void cancel() {
        SceneManager.closeWindow(save);
    }

    public void enableEdit(Material selectedMaterial) {
        edit = true;
        materialFormController.materialNameInput.setText(selectedMaterial.getName());
        materialFormController.quantityInput.setText(String.valueOf(selectedMaterial.getQuantity()));
        materialFormController.valueUnitInput.setText(String.valueOf(selectedMaterial.getValueUnit()));
        System.out.println("editing is true ");
        editMaterial = selectedMaterial;

    }

}
