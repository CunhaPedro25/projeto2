package org.projeto.desktop.pages.dashboard.secretary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.projeto.data.entities.User;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersPageController {
    private final UserService userService;
    @FXML
    TableView<User> table;
    @FXML
    TableColumn<User, String> name;
    @FXML
    TableColumn<User, String> email;
    @FXML
    TableColumn<User, String> phone;
    @FXML
    TableColumn<User, Boolean> status;

    @FXML
    TextField searchField;
    @Autowired
    public UsersPageController(UserService userService) {
        this.userService = userService;
    }

    public void initialize(){
        ObservableList<User> entities = FXCollections.observableArrayList(userService.getAllUsers());
        FilteredList<User> filteredData = new FilteredList<>(entities, p -> true);

        // Bind the search functionality to the text property of the search TextField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all users if the search field is empty
                }

                // Convert search query to lowercase for case-insensitive search
                String lowerCaseFilter = newValue.toLowerCase();

                // Check if user's name contains the search query
                return user.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        status.setCellValueFactory(new PropertyValueFactory<>("active"));

        table.setItems(entities);
    }

    @FXML
    public void openModal() {
        SceneManager.openNewModal("pages/modals/add-user.fxml", "Add User", true);
    }
}
