package org.projeto.desktop.pages.authentication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.projeto.data.entities.User;
import org.projeto.data.entities.UserType;
import org.projeto.data.services.UserService;
import org.projeto.data.services.UserTypeService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.RegisterFormController;

import java.util.Optional;

public class  RegisterController {
  @FXML
  RegisterFormController registerFormController;

  @FXML
  Button register;
  @FXML
  ToggleGroup userType;


  public void initialize(){
    ObservableList<UserType> userTypes = FXCollections.observableArrayList(UserTypeService.getAllUserTypes());
    FilteredList<UserType> filteredData = new FilteredList<>(userTypes, p -> true);
  }

  @FXML
  protected void onRegisterSubmit() {
    if (!registerFormController.isFormCorrect()) {
      SceneManager.openErrorAlert("Error", "Please fill all the required fields correctly");
      return;
    }

    RadioButton selectedToggle = (RadioButton) userType.getSelectedToggle();
    String selectedUserTypeText = selectedToggle.getText();
    Optional<UserType> userTypeOptional = UserTypeService.getByType(selectedUserTypeText);
    UserType userTypeEntity = userTypeOptional.orElseThrow(() ->
            new IllegalArgumentException("UserType not found for description: " + selectedUserTypeText));

    User user = User.builder()
            .name(registerFormController.firstName.getText()+ " "+ registerFormController.lastName.getText())
            .email(registerFormController.email.getText())
            .password(registerFormController.password.getText())
            .phone(registerFormController.phone.getText())
            .address(registerFormController.address.getText())
            .door(Integer.valueOf(registerFormController.door.getText()))
            .userType(userTypeEntity)
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
