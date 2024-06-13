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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddUserModalController {
    @FXML
    RegisterFormController registerFormController;

    @FXML
    Button save;

    @FXML
    ToggleGroup userType;
    @FXML
    HBox userTypeSelection;

    List<UserType> userTypes;

    public void initialize(){
        this.userTypes = UserTypeService.getAllUserTypes();
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
        String selectedUserTypeText = selectedToggle.getText();
        Optional<UserType> userTypeOptional = UserTypeService.getByType(selectedUserTypeText);

        UserType userTypeEntity = userTypeOptional.orElseThrow(() ->
                new IllegalArgumentException("UserType not found for description: " + selectedUserTypeText));

        user = User.builder()
                .name(registerFormController.firstName.getText()+ " "+ registerFormController.lastName.getText())
                .email(registerFormController.email.getText())
                .password(registerFormController.password.getText())
                .phone(registerFormController.phone.getText())
                .address(registerFormController.address.getText())
                .door(Integer.valueOf(registerFormController.door.getText()))
                .userType(userTypeEntity)
                .build();

        try {
            assert user != null;
            UserService.register(user);

            SceneManager.closeWindow(save);
        } catch (Exception exc) {
            exc.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to register. Please try again.");
        }
    }
}
