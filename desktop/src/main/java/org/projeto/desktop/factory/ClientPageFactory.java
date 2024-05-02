package org.projeto.desktop.factory;

import java.util.ArrayList;
import java.util.List;

// Concrete page factory for client user type
public class ClientPageFactory implements PageFactory {
    @Override
    public List<Page> createPages() {
        List<Page> pages = new ArrayList<>();
        pages.add(new Page("Home", "HomePage.fxml", "HomeIcon.png"));
        pages.add(new Page("Projects", "ProjectsPage.fxml", "ProjectsIcon.png"));
        pages.add(new Page("Constructions", "ConstructionsPage.fxml", "ConstructionsIcon.png"));
        return pages;
    }
}
