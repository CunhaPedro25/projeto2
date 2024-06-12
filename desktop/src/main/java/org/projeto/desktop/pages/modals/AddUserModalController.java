package org.projeto.desktop.pages.modals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.projeto.data.entities.*;
import org.projeto.data.services.UserTypeService;
import org.projeto.data.services.UserService;
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
        user = User.builder()
                .name(registerFormController.firstName.getText()+ " "+ registerFormController.lastName.getText())
                .email(registerFormController.email.getText())
                .password(registerFormController.password.getText())
                .phone(registerFormController.phone.getText())
                .address(registerFormController.address.getText())
                .door(Integer.valueOf(registerFormController.door.getText()))
                .userType(registerFormController.userTypeComboBox.getValue())
                .build();
/*        user = switch (selectedToggle.getText()) {
            case "Client" -> User.builder()
                    .name(registerFormController.firstName.getText()+ " "+ registerFormController.lastName.getText())
                    .email(registerFormController.email.getText())
                    .password(registerFormController.password.getText())
                    .phone(registerFormController.phone.getText())
                    .address(registerFormController.address.getText())
                    .door(Integer.valueOf(registerFormController.door.getText()))
                    .userType()


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
        };*/

        try {
            assert user != null;
            UserService.register(user);

            SceneManager.closeWindow(save);
        } catch (Exception exc) {
            SceneManager.openErrorAlert("Error", "It was not possible to register. Please try again.");
        }
    }
}
