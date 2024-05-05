package org.projeto.desktop.factory;

import java.util.ArrayList;
import java.util.List;

// Concrete page factory for client user type
public class ClientPageFactory implements PageFactory {
    @Override
    public List<Page> createPages() {
        List<Page> pages = new ArrayList<>();
        pages.add(new Page("Home", "home.fxml", "fas-home"));
        pages.add(new Page("Projects", "projects.fxml", "fas-archive"));
        pages.add(new Page("Budgets", "budgets.fxml", "fas-file-contract"));
        pages.add(new Page("Constructions", "construction.fxml", "fas-hard-hat"));
        return pages;
    }
}
