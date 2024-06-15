package org.projeto.desktop.pages.dashboard.secretary;

import javafx.animation.PauseTransition;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionType;
import org.projeto.data.entities.Project;
import org.projeto.data.entities.Stage;
import org.projeto.data.services.ConstructionService;

import java.time.LocalDate;
import java.util.Date;

public class ConstructionsPageController {
    @FXML
    public Button delete;
    @FXML
    public Button newConstruction;
    @FXML
    public TableView<Construction> table;
    @FXML
    public TextField searchField;
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
    public Button manageTeam;
    Construction selectedConstruction;
    public void initialize() {
        populateTableView();
    }
    public void populateTableView() {
        ObservableList<Construction> entities = FXCollections.observableArrayList(ConstructionService.getAllConstructions());

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
    }

    @FXML
    public void openNewConstructionModal(ActionEvent actionEvent) {
    }
    @FXML
    public void manageTeam(ActionEvent actionEvent) {
    }
    public void delete(ActionEvent actionEvent) {
    }
}
