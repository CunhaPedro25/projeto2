package org.projeto.desktop.pages.dashboard.admin;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.projeto.data.entities.Material;
import org.projeto.data.entities.Project;
import org.projeto.data.services.MaterialService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.modals.AddMaterialModalController;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MaterialsPageController {
    @FXML
    private TableView<Material> table;
    @FXML
    public Button newProject;
    @FXML
    public Button delete;
    @FXML
    public TableColumn<Material, String> nameColumn;
    @FXML
    public TableColumn<Project, Integer> quantityColumn;
    @FXML
    public TableColumn<Project,Integer> valueUnitColumn;
    public TextField searchField;

    Material selectedMaterial;

    public void initialize() {
        populateTableView();
    }

    private void populateTableView() {
        List<Material> materials = MaterialService.getAllMaterials();
        ObservableList<Material> materialObservableList = FXCollections.observableArrayList(materials);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        valueUnitColumn.setCellValueFactory(new PropertyValueFactory<>("valueUnit"));
        delete.setDisable(true);

        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            Material selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedMaterial = selected;

                // Create a PauseTransition for single click
                PauseTransition singleClickPause = new PauseTransition(Duration.millis(300));
                singleClickPause.setOnFinished(e -> {
                    if (event.getClickCount() == 1) {
                        System.out.println("Single click detected");
                        delete.setDisable(false);
                    }
                });


                // Handle double click immediately
                if (event.getClickCount() == 2) {
                    singleClickPause.stop();
                    System.out.println("Double click detected");
                    editMaterial(selectedMaterial);
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });

        table.setItems(materialObservableList);
    }
    @FXML
    private void editMaterial(Material selectedMaterial) {
        try{
            SceneManager.openNewModal(
                    "pages/modals/add-material.fxml",
                    "Edit Material",
                    true,
                    controller -> {
                        AddMaterialModalController editMaterial = (AddMaterialModalController) controller;
                        editMaterial.enableEdit(selectedMaterial);
                    });

        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to edit the project. Please try again.");
        }
        populateTableView();
    }

    @FXML
    public void openNewMaterialModal() {
        SceneManager.openNewModal("pages/modals/add-material.fxml", "Add Material", true);
        populateTableView();
    }

    public void delete(ActionEvent actionEvent) {
        if (SceneManager.openConfirmationAlert("Delete Material", "Are you sure you want to delete this material?")){
            Material material = table.getSelectionModel().getSelectedItem();
            MaterialService.delete(Long.valueOf(material.getId()));
            populateTableView();
        }
    }
}
