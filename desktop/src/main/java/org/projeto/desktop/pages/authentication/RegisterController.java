package org.projeto.desktop.pages.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.projeto.data.entities.users.Client;
import org.projeto.data.services.ClientTypeService;
import org.projeto.data.services.users.UserService;
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

    Client client = new Client(
            registerFormController.firstName.getText() +  " " + registerFormController.lastName.getText(),
            registerFormController.email.getText(),
            registerFormController.password.getText(),
            registerFormController.phone.getText(),
            ClientTypeService.getAllClientTypes().get(0)
    );

    try {
      UserService.register(client);

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
