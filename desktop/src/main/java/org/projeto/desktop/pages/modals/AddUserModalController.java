package org.projeto.desktop.pages.modals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.projeto.data.entities.users.*;
import org.projeto.data.services.ClientTypeService;
import org.projeto.data.services.users.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.RegisterFormController;

public class AddUserModalController {
    @FXML
    RegisterFormController registerFormController;

    @FXML
    Button save;

    @FXML
    ToggleGroup userType;
    @FXML
    HBox userTypeSelection;


    @FXML
    protected void enableAddress(){
        registerFormController.enableAddress();
    }

    @FXML
    protected void disableAddress(){
        registerFormController.disableAddress();
    }

    @FXML
    protected void save(){
        if (!registerFormController.isFormCorrect()) {
            SceneManager.openErrorAlert("Error", "Please fill all the required fields correctly");
            return;
        }

        User user = null;
        RadioButton selectedToggle = (RadioButton) userType.getSelectedToggle();
        user = switch (selectedToggle.getText()) {
            case "Client" -> new Client(
                    registerFormController.firstName.getText() + " " + registerFormController.lastName.getText(),
                    registerFormController.email.getText(),
                    registerFormController.password.getText(),
                    registerFormController.phone.getText(),
                    ClientTypeService.getAllClientTypes().get(0)
            );
            case "Secretary" -> new Secretary(
                    registerFormController.firstName.getText() + " " + registerFormController.lastName.getText(),
                    registerFormController.email.getText(),
                    registerFormController.password.getText(),
                    registerFormController.phone.getText()
            );
            case "Worker" -> new Worker(
                    registerFormController.firstName.getText() + " " + registerFormController.lastName.getText(),
                    registerFormController.email.getText(),
                    registerFormController.password.getText(),
                    registerFormController.phone.getText()
            );
            case "Engineer" -> new Engineer(
                    registerFormController.firstName.getText() + " " + registerFormController.lastName.getText(),
                    registerFormController.email.getText(),
                    registerFormController.password.getText(),
                    registerFormController.phone.getText()
            );
            default -> user;
        };

        try {
            assert user != null;
            UserService.register(user);

            SceneManager.closeWindow(save);
        } catch (Exception exc) {
            SceneManager.openErrorAlert("Error", "It was not possible to register. Please try again.");
        }
    }
}
