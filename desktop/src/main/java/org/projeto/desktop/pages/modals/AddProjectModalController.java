package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.projeto.data.entities.ConstructionType;
import org.projeto.data.entities.User;
import org.projeto.data.services.ConstructionTypeService;
import org.projeto.data.services.UserService;
import org.projeto.desktop.components.ProjectFormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.util.List;


// TODO: Fix problems and make it possible to add new budgets
@Component
public class AddProjectModalController {
    @FXML
    ProjectFormController projectFormController;

    @FXML
    Button save;

    @FXML
    ToggleGroup constructionType;

    @FXML
    HBox constructionTypeSelection;

    List<ConstructionType> constructionTypes;

/*    @FXML
    private Button file;
    idk wus dis*/

/*    @FXML
    private DatePicker date;
    could be a db trigger*/

    public void initialize(){
        this.constructionTypes = ConstructionTypeService.getAllConstructionTypes();
    }

    public void cancel(ActionEvent actionEvent) {
    }

    private static class ClientListCell extends ListCell<User> {
        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getName());
            }
        }
    }

    @FXML
    protected void save() {

    }
}
