package org.projeto.desktop.pages.dashboard.admin;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionType;
import org.projeto.data.entities.Project;
import org.projeto.data.entities.Stage;
import org.projeto.data.services.ConstructionService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.modals.AddConstructionModalController;
import org.projeto.desktop.pages.modals.AddMaterialConstructionModalController;

import java.time.LocalDate;

public class ConstructionsPageController {
    @FXML
    public Button delete;
    @FXML
    public Button newConstruction;
    @FXML
    public TableView<Construction> table;
    @FXML
    public TableColumn<Construction, String> projectColumn;
    @FXML
    public TableColumn<Construction,String> stageColumn;
    @FXML
    public TableColumn<Construction,String> stateColumn;
    @FXML
    public TableColumn<Construction, LocalDate> lastUpdateColumn;
    @FXML
    public TableColumn<Construction,String> constructionTypeColumn;

    @FXML
    public Button addMaterial;
    @FXML
    public TableColumn<Construction,String> nameColumn;
    Construction selectedConstruction;
    public void initialize() {
        populateTableView();
    }
    public void populateTableView() {
        ObservableList<Construction> entities = FXCollections.observableArrayList(ConstructionService.getAllConstructions());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        projectColumn.setCellValueFactory(cellData ->{
            Construction construction = cellData.getValue();
            Project project = construction.getProject();
            return new SimpleObjectProperty<>(project.getId().toString());
        });
        stageColumn.setCellValueFactory(cellData ->{
            Construction construction = cellData.getValue();
            Stage stage = construction.getStage();
            return new SimpleObjectProperty<>(stage.getName());
        });
        stateColumn.setCellValueFactory(cellData ->{
            Construction construction = cellData.getValue();
            return new SimpleObjectProperty<>(construction.getState().getDescription());
        });
        lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        constructionTypeColumn.setCellValueFactory(cellData -> {
            Construction construction = cellData.getValue();
            Project project = construction.getProject();
            ConstructionType constructionType = project.getConstructionType();
            return new SimpleStringProperty(constructionType.getType());
        });
        delete.setDisable(true);
        addMaterial.setDisable(true);

        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            Construction selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedConstruction = selected;

                // Create a PauseTransition for single click
                PauseTransition singleClickPause = new PauseTransition(Duration.millis(300));
                singleClickPause.setOnFinished(e -> {
                    if (event.getClickCount() == 1) {
                        System.out.println("Single click detected");
                        delete.setDisable(false);
                        addMaterial.setDisable(false);
                    }
                });


                // Handle double click immediately
                if (event.getClickCount() == 2) {
                    singleClickPause.stop();
                    System.out.println("Double click detected");
                    editConstruction(selectedConstruction);
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });
        table.setItems(entities);
    }
    @FXML
    private void editConstruction(Construction selectedConstruction) {
        try{
            SceneManager.openNewModal(
                    "pages/modals/add-construction.fxml",
                    "Edit Construction",
                    true,
                    controller -> {
                        AddConstructionModalController editConstruction = (AddConstructionModalController) controller;
                        editConstruction.enableEdit(selectedConstruction);
                        System.out.println("we are trying VERY HARD" + selectedConstruction.getId());
                    });

        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to edit the project. Please try again.");
        }
        populateTableView();
    }
    @FXML
    public void addMaterial() {
        try {
            SceneManager.openNewModal(
                    "pages/modals/add-materialConstruction.fxml",
                    "Add Material to Construction",
                    true,
                    controller -> {
                        AddMaterialConstructionModalController addMaterial = (AddMaterialConstructionModalController) controller;
                        addMaterial.setConstruction(selectedConstruction);
                    });
        }catch (Exception e){
            Throwable cause = e.getCause();
            cause.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to add the material. Please try again.");
        }
        populateTableView();
    }

    @FXML
    public void openNewConstructionModal() {
        try{
            SceneManager.openNewModal(
                    "pages/modals/add-construction.fxml","Add Construction",true);

        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to edit the project. Please try again.");
        }
        populateTableView();
    }


    public void delete() {
        if (SceneManager.openConfirmationAlert("Delete Construction", "Are you sure you want to delete the construction?")){
            try {
                ConstructionService.delete(Long.valueOf(selectedConstruction.getId()));
                populateTableView();
            } catch (Exception e) {
                e.printStackTrace();
                SceneManager.openErrorAlert("Error", "It was not possible to delete the construction. Please try again.");
            }
        }
        populateTableView();
    }

}
