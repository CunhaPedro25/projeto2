package org.projeto.desktop.pages.dashboard.admin;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.projeto.data.entities.User;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.modals.AddUserModalController;
import org.springframework.stereotype.Component;


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
    Button delete;

    @FXML
    Button newUserButton;

    @FXML
    TextField searchField;
    User selectedUser = null;

    public void initialize(){

        populateTableView();

        // Add combined click listener with a timer
        table.setOnMouseClicked(event -> {
            User selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedUser = selected;

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
                    editUser(selectedUser);
                } else {
                    singleClickPause.playFromStart();
                }
            }
        });
    }

    private void populateTableView() {
        ObservableList<User> entities = FXCollections.observableArrayList(UserService.getAllUsers());
        entities.removeIf(user -> user.getUserType().getType().equals("Admin"));
        FilteredList<User> filteredData = new FilteredList<>(entities, p -> true);
        table.setItems(filteredData);



        delete.setDisable(true);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        status.setCellValueFactory(new PropertyValueFactory<>("active"));

        table.setItems(entities);

    }

    @FXML
    public void openNewUserModal() {
        SceneManager.openNewModal("pages/modals/add-user.fxml", "Add User", true);
        populateTableView();
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
        populateTableView();
    }

     public void delete() throws Exception {
        if (SceneManager.openConfirmationAlert("Delete User","Are you sure you want to delete this user?")) {
            selectedUser.setActive(false);
            UserService.update(selectedUser);
            table.getItems().remove(selectedUser);
        }

    }

}
