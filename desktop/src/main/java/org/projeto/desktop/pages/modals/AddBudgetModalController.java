package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import org.projeto.data.entities.users.Client;
import org.projeto.data.services.users.ClientService;

import java.time.LocalDate;


// TODO: Fix problems and make it possible to add new budgets
public class AddBudgetModalController {
    @FXML
    Button save;

    @FXML
    private ComboBox<Client> clientComboBox;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private Button file;

    @FXML
    private DatePicker date;

    public void initialize(){

        clientComboBox.setItems(FXCollections.observableArrayList(ClientService.findAll()));
        clientComboBox.setCellFactory(tc -> new ClientListCell());
        clientComboBox.setButtonCell(new ClientListCell());
        clientComboBox.setValue(ClientService.findAll().get(0));


        date.setValue(LocalDate.now());
    }

    private static class ClientListCell extends ListCell<Client> {
        @Override
        public void updateItem(Client item, boolean empty) {
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
