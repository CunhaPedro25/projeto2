package org.projeto.desktop.components;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.projeto.desktop.CurrentUser;
import org.projeto.desktop.events.EventBus;
import org.projeto.desktop.events.SideBarButtonEvent;

public class HeaderController {
    @FXML
    private HBox title;
    @FXML
    private HBox userButton;
    @FXML
    private Label user;

    public void initialize() {
        EventBus.getInstance().subscribe(this::handleSidebarButtonEvent);

        user.setText(CurrentUser.name);
    }

    private void handleSidebarButtonEvent(Event event) {
        if (event instanceof SideBarButtonEvent) {
            try {
                title.getChildren().clear();
                FontIcon icon = new FontIcon(CurrentUser.currentPage.iconName());
                title.getChildren().add(icon);
                title.getChildren().add(new Label(CurrentUser.currentPage.pageName()));
            }catch (Exception e){
                System.out.println("HeaderController SidebarButtonEvent: " + e.getMessage());
            }
        }
    }
}
