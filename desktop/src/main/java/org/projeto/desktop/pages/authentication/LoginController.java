package org.projeto.desktop.pages.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import org.projeto.data.entities.users.User;
import org.projeto.data.services.users.UserService;
import org.projeto.desktop.CurrentUser;
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
    SceneManager.switchScene(registerLink, "pages/authentication/register.fxml");
  }

  @FXML
  protected void onLoginClick() {

    if(email.getText().isEmpty() || password.getText().isEmpty()) {
      SceneManager.openWarningAlert("Warning", "Please fill all of the fields");
      return;
    }

    try {
      User user = UserService.login(email.getText(), password.getText());
      CurrentUser.setUser(user);
      SceneManager.switchScene(
              registerLink,
              "pages/dashboard/dashboard.fxml"
      );
    } catch (Exception err) {
      System.out.println(err.getMessage());
      SceneManager.openErrorAlert("Error", "Wrong credentials");
    }
  }
}
