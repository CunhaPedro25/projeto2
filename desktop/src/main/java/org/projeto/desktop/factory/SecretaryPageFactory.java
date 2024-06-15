package org.projeto.desktop.factory;

import java.util.ArrayList;
import java.util.List;

// Concrete page factory for client user type
public class SecretaryPageFactory implements PageFactory {
    @Override
    public List<Page> createPages() {
        List<Page> pages = new ArrayList<>();
        pages.add(new Page("Home", "home.fxml", "fas-home"));
        pages.add(new Page("Users", "users.fxml", "fas-users"));
        pages.add(new Page("Projects", "projects.fxml", "fas-archive"));
        pages.add(new Page("Constructions", "constructions.fxml", "fas-hard-hat"));
        pages.add(new Page("Teams", "teams.fxml", "fas-people-carry"));
        pages.add(new Page("Stock", "materials.fxml", "fas-warehouse"));
        return pages;
    }
}
