package org.projeto.desktop.pages.dashboard;

import org.projeto.data.entities.users.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DashBoardController {
    private static final String FXML_FOLDER = "desktop/src/main/resources/org/projeto/desktop/pages/dashboard/";
    private User user;


    public void setUser(User user) {
        this.user = user;
        System.out.println(this.user);
    }

}
