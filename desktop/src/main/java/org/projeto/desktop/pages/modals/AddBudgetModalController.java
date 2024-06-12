package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import org.projeto.data.entities.User;
import org.projeto.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


// TODO: Fix problems and make it possible to add new budgets
@Component
public class AddBudgetModalController {
    private final UserService userService;
    @FXML
    Button save;

    @FXML
    private ComboBox<User> clientComboBox;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private Button file;

    @FXML
    private DatePicker date;
    @Autowired
    public AddBudgetModalController(UserService userService) {
        this.userService = userService;
    }

    public void initialize(){

        clientComboBox.setItems(FXCollections.observableArrayList(userService.getAllClients()));
        clientComboBox.setCellFactory(tc -> new ClientListCell());
        clientComboBox.setButtonCell(new ClientListCell());
        clientComboBox.setValue(userService.getAllClients().get(0));
        date.setValue(LocalDate.now());
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
