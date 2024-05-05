package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterFormController {
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
    public TextField door;
    @FXML
    public TextField zipcode;
    @FXML
    public TextField district;
    @FXML
    public TextField city;
    @FXML
    public TextField locale;
    @FXML
    public TextField phone;

    boolean passwordHidden = false;

    public boolean isFormCorrect() {
        return
                email.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                        && phone.getText().matches("\\d{9}")
                        && !firstName.getText().trim().isEmpty()
                        && !lastName.getText().trim().isEmpty()
                        && (passwordHidden || (!password.getText().trim().isEmpty()))
                        && !address.getText().trim().isEmpty()
                        && !city.getText().trim().isEmpty()
                ;
    }

    public void setValues(String firstName, String lastName, String email, String address, String city, String phone){
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.email.setText(email);
        this.address.setText(address);
        this.city.setText(city);
        this.phone.setText(phone);
    }

    public void setValues(String firstName, String lastName, String email, String password, String address, String city, String phone){
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.email.setText(email);
        this.password.setText(password);
        this.address.setText(address);
        this.city.setText(city);
        this.phone.setText(phone);
    }

    public void clearValues(){
        this.firstName.clear();
        this.lastName.clear();
        this.email.clear();
        this.password.clear();
        this.address.clear();
        this.city.clear();
        this.phone.clear();
    }

    public void hidePassword() {
        passwordHidden = true;
        passwordLabel.setVisible(false);
        passwordLabel.setManaged(false);
        password.setVisible(false);
        password.setManaged(false);
    }
}
