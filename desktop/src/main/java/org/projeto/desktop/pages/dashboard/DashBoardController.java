package org.projeto.desktop.pages.dashboard;

import org.projeto.data.entities.users.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DashBoardController {
    private static final String FXML_FOLDER = "assets/pages/dashboard/";
    private List<String> fxmlFileNames;
    private User user;

    public void setUser(User user) {
        this.user = user;
        String userType = user.getClass().getSimpleName().toLowerCase();

        fxmlFileNames = new ArrayList<>();
        String folderPath = FXML_FOLDER + userType + "/";
        File folder = new File(folderPath);

        System.out.println(FXML_FOLDER);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".fxml"));
            if (files != null) {
                for (File file : files) {
//                    System.out.println(file.getName());
                    fxmlFileNames.add(file.getName());
                }
            }
        }
    }

    public List<String> getFXMLFileNames() {
        return fxmlFileNames;
    }
}
