package org.projeto.desktop.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.projeto.data.entities.users.Client;
import org.projeto.data.entities.users.User;
import org.projeto.data.services.users.UserService;
import org.projeto.desktop.SceneManager;

public class LoginController {
  @FXML
  public TextField email;
  @FXML
  public TextField password;
  @FXML
  private Hyperlink registerLink;

  @FXML
  protected void onRegisterClick() {
    SceneManager.switchScene(registerLink, "pages/register.fxml");
  }

  @FXML
  protected void onLoginClick() {

    if(email.getText().isEmpty() || password.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Aviso");
      alert.setHeaderText("Por favor preencha todos os campos");
      alert.showAndWait();
      return;
    }

    try {
      User client = UserService.login(email.getText(), password.getText());
      System.out.println(client.getName());
      SceneManager.openErrorAlert("Hello", "How u doing" + client.getName());
    } catch (Exception err) {
      System.out.println(err.getMessage());
      SceneManager.openErrorAlert("Erro a iniciar sessão", "Credenciais erradas");
    }
  }
}
