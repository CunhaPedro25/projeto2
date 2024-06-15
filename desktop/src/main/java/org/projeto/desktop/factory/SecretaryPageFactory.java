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
        pages.add(new Page("Teams", "teams.fxml", "fa-sharp fa-regular fa-people-group"));
        pages.add(new Page("Stock", "stock.fxml", "fas-warehouse"));
        return pages;
    }
}
