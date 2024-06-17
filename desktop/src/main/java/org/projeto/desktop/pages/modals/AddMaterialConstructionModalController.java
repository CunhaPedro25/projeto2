package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionMaterial;
import org.projeto.data.entities.ConstructionMaterialId;
import org.projeto.data.entities.Material;
import org.projeto.data.services.ConstructionMaterialService;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.MaterialService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.MaterialConstructionFormController;

import java.util.List;

public class AddMaterialConstructionModalController {
    @FXML
    public Button save;
    @FXML
    MaterialConstructionFormController materialConstructionFormController;

    Integer constructionID;
    @FXML
    public void initialize() {
        getReady();
    }

    @FXML
    public void cancel() {
        SceneManager.closeWindow(save);
    }

    @FXML
    public void save() {
        System.out.println("save been clicked");
        try {
            if (!materialConstructionFormController.isFormCorrect()){
                System.out.println("form is not correct");
                SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
            } else {
                System.out.println("form is correct");
                if (Integer.parseInt(materialConstructionFormController.quantity.getText()) > MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue()).getQuantity()){
                    SceneManager.openErrorAlert("Error", "Not enough material in stock");
                    return;
                }
                Construction test = ConstructionService.findById(constructionID);
                if (test == null){
                    System.out.println("Construction is null");
                    throw new IllegalArgumentException("OLHA O ERRO AQUI!!!!!!!!!!!!!!!!!!!");
                }

                ConstructionMaterialService.addNew(ConstructionMaterial.builder()
                        .id(ConstructionMaterialId.builder()
                                .construction(constructionID)
                                .material(MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue()).getId())
                                .build())
                        .construction(test)
                        .material(MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue()))
                        .quantity(Integer.valueOf(materialConstructionFormController.quantity.getText()))
                        .build());

                SceneManager.openConfirmationAlert("Success", "Material added successfully");
            Integer oldQuantity = MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue()).getQuantity();
            Integer newQuantity = oldQuantity - Integer.parseInt(materialConstructionFormController.quantity.getText());
            Material material =  MaterialService.getMaterialByName(materialConstructionFormController.materialComboBox.getValue());
            material.setQuantity(newQuantity);
            MaterialService.update(material);
                SceneManager.closeWindow(save);
            }
        } catch (Exception e){
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "An error occurred while adding the material");
        }
    }

    public void getReady(){
        System.out.println("get ready");
        List<String> names = MaterialService.getAllMaterialsNames();
        materialConstructionFormController.materialComboBox.setItems(FXCollections.observableArrayList(names));
        materialConstructionFormController.materialComboBox.setValue(names.get(0));
    }

    public void setConstruction(Construction construction) {
        constructionID = construction.getId();
        System.out.printf("Construction ID: %d\n", constructionID);
        materialConstructionFormController.constructionName.setText(String.valueOf(construction.getName()));
        materialConstructionFormController.constructionName.setDisable(true);
        constructionID = construction.getId();

    }
}
