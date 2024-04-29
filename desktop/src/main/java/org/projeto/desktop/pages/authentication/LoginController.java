package org.projeto.desktop.pages.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import org.projeto.data.entities.users.Client;
import org.projeto.data.entities.users.User;
import org.projeto.data.services.users.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.pages.dashboard.DashBoardController;

public class LoginController {
  @FXML
  public TextField email;
  @FXML
  public TextField password;
  @FXML
  private Hyperlink registerLink;

  @FXML
  protected void onRegisterClick() {
    SceneManager.switchScene(registerLink, "pages/authentication/register.fxml");
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
      User user = UserService.login(email.getText(), password.getText());
      System.out.println(user.getClass().getSimpleName());

      SceneManager.switchScene(
              registerLink,
              "pages/dashboard/dashboard.fxml",
              controller -> {
                DashBoardController _controller = (DashBoardController) controller;
                _controller.setUser(user);
              }
      );
    } catch (Exception err) {
      System.out.println(err.getMessage());
      System.out.println(err.getCause());
      SceneManager.openErrorAlert("Erro a iniciar sessão", "Credenciais erradas");
    }
  }
}
