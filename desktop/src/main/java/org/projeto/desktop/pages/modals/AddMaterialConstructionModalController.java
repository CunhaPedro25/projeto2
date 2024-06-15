package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionMaterial;
import org.projeto.data.services.ConstructionMaterialService;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.MaterialService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.MaterialConstructionFormController;
import org.projeto.desktop.components.MaterialFormController;

import java.util.List;

public class AddMaterialConstructionModalController {
    @FXML
    public Button save;
    @FXML
    private MaterialConstructionFormController materialConstructionFormController;
    Construction selectedConstruction;
    @FXML
    public void initialize() {
        getReady();
    }
    @FXML
    public void cancel(ActionEvent actionEvent) {
        SceneManager.closeWindow(save);
    }
    @FXML
    public void save(ActionEvent actionEvent) {
        System.out.println("save been clicked");
        try {
            if (!materialConstructionFormController.isFormCorrect()){
                System.out.println("form is not correct");
                SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                return;
            } else {
                System.out.println("form is correct");
                ConstructionMaterialService.addNew(ConstructionMaterial.builder()
                        .construction(ConstructionService.findById(Math.toIntExact(Long.parseLong(materialConstructionFormController.constructionID.getText()))))
                        .material(MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue()))
                        .quantity(Integer.valueOf(materialConstructionFormController.quantity.getText()))
                        .build());

                SceneManager.openConfirmationAlert("Success", "Material added successfully");
                SceneManager.closeWindow(save);
            }
        } catch (Exception e){
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "An error occurred while adding the material");
        }
    }
    public void getReady(){
        System.out.println("get ready");

    }
    public void setConstruction(Construction construction) {
        materialConstructionFormController.constructionID.setText(String.valueOf(construction.getId()));
        materialConstructionFormController.constructionID.setDisable(true);

    }
}
