package org.projeto.desktop.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.projeto.data.entities.User;
import org.projeto.data.entities.UserType;
import org.projeto.data.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
    public TextField phone;

    @FXML
    VBox addressContainer;

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

    boolean passwordHidden = false;


    public boolean isFormCorrect() {
        return
                email.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                        && phone.getText().matches("\\d{9}")
                        && !firstName.getText().trim().isEmpty()
                        && !lastName.getText().trim().isEmpty()
                        && (passwordHidden || (!password.getText().trim().isEmpty()))
                ;
    }
    public boolean isFormCorrectEdit() {
        return
                email.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                        && phone.getText().matches("\\d{9}")
                        && !firstName.getText().trim().isEmpty()
                        && !lastName.getText().trim().isEmpty();
    }

    public void setValues(String firstName, String lastName, String email, String address,String door, String phone){
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.email.setText(email);
        this.address.setText(address);
        this.door.setText(door);
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

    public void enableAddress(){
        addressContainer.setVisible(true);
        addressContainer.setManaged(true);
    }

    public void disableAddress(){
        addressContainer.setVisible(false);
        addressContainer.setManaged(false);
    }
}
