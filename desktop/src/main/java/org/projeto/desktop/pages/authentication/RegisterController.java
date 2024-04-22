package org.projeto.desktop.pages.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.projeto.data.entities.users.Client;
import org.projeto.data.services.ClientTypeService;
import org.projeto.data.services.users.UserService;
import org.projeto.desktop.SceneManager;

public class  RegisterController {


  //THIS IS JUST FOR TESTING LATER THERE WILL BE A REGISTER FORM COMPONENT OR SOMETHING
  @FXML
  public TextField firstName;
  @FXML
  public TextField lastName;
  @FXML
  public TextField email;
  @FXML
  public Label passwordLabel;
  @FXML
  public PasswordField password;
  @FXML
  public TextField address;
  @FXML
  public TextField phone;

  public boolean isFormCorrect() {
    return
            email.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                    && phone.getText().matches("\\d{9}")
                    && !firstName.getText().trim().isEmpty()
                    && !lastName.getText().trim().isEmpty()
                    && (!password.getText().trim().isEmpty())
                    && !address.getText().trim().isEmpty()
            ;
  }

  @FXML
  protected void onRegisterSubmit() {
    if (!isFormCorrect()) {
      SceneManager.openErrorAlert("Erro ao registar", "Por favor preencha todos os campos corretamente");
      return;
    }

    Client client = new Client(
            firstName.getText() +  " " + lastName.getText(),
            email.getText(),
            password.getText(),
            phone.getText(),
            ClientTypeService.getAllClientTypes().get(0)
    );

    try {
      UserService.register(client);

      returnToLogin();
    } catch (Exception exc) {
      SceneManager.openErrorAlert("Erro ao registar", "Não foi possível guardar o registo");
    }
  }

  @FXML
  protected void returnToLogin() {
    try {
      SceneManager.switchScene(firstName, "pages/login.fxml");
    } catch (Exception e) {
      System.out.println("SceneManager");
    }
  }
}
