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
import org.projeto.desktop.components.RegisterFormController;
import org.projeto.desktop.pages.modals.AddUserModalController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;

@Component
public class UsersPageController {
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
    RegisterFormController registerFormController;

    @FXML
    TextField searchField;

    public void initialize(){
        ObservableList<User> entities = FXCollections.observableArrayList(UserService.getAllUsers());
        entities.removeIf(user -> user.getUserType().getType().equals("Admin"));
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

        // Add a mouse click listener to the table
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
                    editUser(table.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    public void openModal() {
        SceneManager.openNewModal("pages/modals/add-user.fxml", "Add User", true);
    }
    @FXML
    public void editUser(User user) {

        try{
            SceneManager.openNewModal(
                    "pages/modals/add-user.fxml",
                    "Edit User",
                    true,
                    controller ->{
                        AddUserModalController editUser = (AddUserModalController) controller;
                        editUser.enableEdit(user);

                    });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
