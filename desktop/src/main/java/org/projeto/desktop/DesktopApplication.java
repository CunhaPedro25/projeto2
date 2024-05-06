package org.projeto.desktop;

import org.springframework.boot.SpringApplication;
import org.projeto.data.DataApplication;
import javafx.application.Application;
import javafx.stage.Stage;

public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.openNewWindow("pages/authentication/login.fxml", false);
    }

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class);

        launch();
    }
}