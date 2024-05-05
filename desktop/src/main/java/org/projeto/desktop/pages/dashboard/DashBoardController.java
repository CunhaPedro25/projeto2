package org.projeto.desktop.pages.dashboard;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import org.projeto.desktop.CurrentUser;
import org.projeto.desktop.events.EventBus;
import org.projeto.desktop.events.SideBarButtonEvent;

import java.io.File;

public class DashBoardController {
    @FXML
    private ScrollPane mainContent;

    public void initialize() {
        EventBus.getInstance().subscribe(this::handleSidebarButtonEvent);
        changePage();
    }

    private void handleSidebarButtonEvent(Event event) {
        if (event instanceof SideBarButtonEvent) {
            changePage();
        }
    }

    private void changePage(){
        try {
            String path = CurrentUser.type + File.separator + CurrentUser.currentPage.fileName();
            System.out.println(path);
            FXMLLoader loader = new FXMLLoader(DashBoardController.class.getResource(path));
            Parent content = loader.load();
            mainContent.setContent(content);
        }catch (Exception e){
//                System.out.println("DashBoardController SidebarButtonEvent: " + e.getCause());
            e.printStackTrace();
        }
    }
}
