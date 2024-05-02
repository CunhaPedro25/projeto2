package org.projeto.desktop;

import org.springframework.boot.SpringApplication;
import org.projeto.data.DataApplication;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.openNewWindow("pages/dashboard/dashboard.fxml", false);
    }

    public static void main(String[] args) {
//        SpringApplication.run(DataApplication.class);

        launch();
    }
}