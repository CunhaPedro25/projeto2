package org.projeto.desktop.pages.modals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.projeto.data.entities.*;
import org.projeto.data.services.UserTypeService;
import org.projeto.data.services.UserService;
import org.projeto.data.services.ZipcodeService;
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
    boolean edit = false;
    User editUser = null;

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
        RadioButton selectedToggle = (RadioButton) userType.getSelectedToggle();
        String selectedUserTypeText = selectedToggle.getText();
        Optional<UserType> userTypeOptional = UserTypeService.getByType(selectedUserTypeText);

        UserType userTypeEntity = userTypeOptional.orElseThrow(() ->
                new IllegalArgumentException("UserType not found for description: " + selectedUserTypeText));

        try {
            if (edit) {
                System.out.println("editing");
                if (!registerFormController.isFormCorrectEdit()) {
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                }
                editUser.setName(registerFormController.firstName.getText() + " " + registerFormController.lastName.getText());
                editUser.setEmail(registerFormController.email.getText());
                editUser.setPhone(registerFormController.phone.getText());
                editUser.setAddress(registerFormController.address.getText());
                editUser.setDoor(Integer.valueOf(registerFormController.door.getText()));
                editUser.setUserType(userTypeEntity);
                editUser.setActive(true);

                UserService.update(editUser);
                registerFormController.passwordLabel.setDisable(false);
                registerFormController.password.setDisable(false);
                SceneManager.closeWindow(save);
            } else {
                System.out.println("adding");
                if (!registerFormController.isFormCorrect()) {
                    SceneManager.openErrorAlert("Error register", "Please fill all the required fields correctly");
                    return;
                }

                User user = User.builder()
                        .name(registerFormController.firstName.getText() + " " + registerFormController.lastName.getText())
                        .email(registerFormController.email.getText())
                        .password(registerFormController.password.getText())
                        .phone(registerFormController.phone.getText())
                        .address(registerFormController.address.getText())
                        .door(Integer.valueOf(registerFormController.door.getText()))
                        .userType(userTypeEntity)
                        .zipcode(ZipcodeService.findById(registerFormController.zipcode.getText()))
                        .active(true)
                        .build();

                UserService.register(user);
                SceneManager.closeWindow(save);
            }
        } catch (NumberFormatException e) {
            SceneManager.openErrorAlert("Error", "Invalid number format in door field.");
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openErrorAlert("Error", "It was not possible to register. Please try again.");
        }
    }


    public void enableEdit(User user) {
        registerFormController.passwordLabel.setDisable(true);
        registerFormController.password.setDisable(true);
        edit = true;
        editUser = user;
        String[] nameParts = user.getName().split(" ",2);
        String first_name;
        String last_name;
        if (nameParts.length == 2) {
            first_name = nameParts[0];
            last_name = nameParts[1];
        } else {
            // Handle the case where there's no space in the name
            first_name = user.getName();
            last_name = "";
        }
        registerFormController.setValues(
                first_name,
                last_name,
                user.getEmail(),
                user.getAddress(),
                user.getDoor().toString(),
                user.getPhone()
        );

    }

    public void cancel() {
        SceneManager.closeWindow(save);
    }
}
