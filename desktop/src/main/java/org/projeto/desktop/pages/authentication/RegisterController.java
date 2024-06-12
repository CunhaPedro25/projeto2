package org.projeto.desktop.pages.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.User;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.RegisterFormController;

public class  RegisterController {
  @FXML
  RegisterFormController registerFormController;

  @FXML
  Button register;


  @FXML
  protected void onRegisterSubmit() {
    if (!registerFormController.isFormCorrect()) {
      SceneManager.openErrorAlert("Error", "Please fill all the required fields correctly");
      return;
    }

    User user = User.builder()
            .name(registerFormController.firstName.getText()+ " "+ registerFormController.lastName.getText())
            .email(registerFormController.email.getText())
            .password(registerFormController.password.getText())
            .phone(registerFormController.phone.getText())
            .address(registerFormController.address.getText())
            .door(Integer.valueOf(registerFormController.door.getText()))
            .userType(registerFormController.userTypeComboBox.getValue())
            .build();

    try {
      UserService.register(user);

      returnToLogin();
    } catch (Exception exc) {
      SceneManager.openErrorAlert("Error", "It was not possible to register. Please try again.");
    }
  }

  @FXML
  protected void returnToLogin() {
    try {
      SceneManager.switchScene(register, "pages/authentication/login.fxml");
    } catch (Exception e) {
      System.out.println("SceneManager");
    }
  }
}
