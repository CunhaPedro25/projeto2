package org.projeto.desktop.components;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

import org.projeto.desktop.CurrentUser;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.events.EventBus;
import org.projeto.desktop.events.SideBarButtonEvent;
import org.projeto.desktop.factory.*;

import java.util.List;

public class SideBarController {
    @FXML
    VBox buttonContainer;

    private PageFactory pageFactory;

    public void initialize() {
        try {
            switch (CurrentUser.type) {
                case "Client":
                    pageFactory = new ClientPageFactory();
                    break;
                case "worker":
                    pageFactory = new WorkerPageFactory();
                    break;
                case "Secretary":
                    pageFactory = new SecretaryPageFactory();
                    break;
            }

            List<Page> pages = pageFactory.createPages();

            for (Page page : pages) {
                Button button = this.getButton(page);

                buttonContainer.getChildren().add(button);
            }

            changeSelectedButtonStyle(pages.get(0));
            CurrentUser.currentPage = pages.get(0);
        } catch (Exception e) {
            System.out.println("SideBarController: " + e.getCause());
        }
    }

    private Button getButton(Page page) {
        Button button = new Button(page.pageName());
        button.setId(page.pageName());
        button.setOnAction(event -> handleButtonClick(page));
        FontIcon icon = new FontIcon(page.iconName());
        button.setGraphic(icon);
        return button;
    }

    private void handleButtonClick(Page page) {
        changeSelectedButtonStyle(page);
        EventBus.getInstance().publish(new SideBarButtonEvent(page.pageName()));
    }

    private void changeSelectedButtonStyle(Page page) {
        // Remove selected style from the previous selected button
        if (CurrentUser.currentPage != null) {
            buttonContainer.getChildren().forEach(node -> {
                if (node instanceof Button button) {
                    if (button.getId().equals(CurrentUser.currentPage.pageName())) {
                        button.getStyleClass().remove("selected");
                    }
                }
            });
        }

        // Add selected style to the current selected button
        buttonContainer.getChildren().forEach(node -> {
            if (node instanceof Button button) {
                if (button.getId().equals(page.pageName())) {
                    button.getStyleClass().add("selected");
                }
            }
        });

        // Update the selectedPageFileName
        CurrentUser.currentPage = page;
    }


    @FXML
    public void logout() {
        if(SceneManager.openConfirmationAlert("Login Out", "Are you sure you want to logout?")){
            CurrentUser.logout();
            SceneManager.switchScene(buttonContainer, "pages/authentication/login.fxml");
        }
    }
}
