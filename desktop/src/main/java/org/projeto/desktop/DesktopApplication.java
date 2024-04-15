package org.projeto.desktop;

import org.projeto.data.entities.enums.ClientType;
import org.projeto.data.services.ClientTypeService;
import org.springframework.boot.SpringApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.projeto.data.DataApplication;

import java.io.IOException;

public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DesktopApplication.class.getResource("pages/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class);
//        ClientType clientType = new ClientType("Empresa");
//        ClientTypeService.addNew(clientType);

        launch();
    }
}