package org.projeto.desktop.factory;

import java.util.ArrayList;
import java.util.List;

// Concrete page factory for worker user type
public class WorkerPageFactory implements PageFactory {
    @Override
    public List<Page> createPages() {
        List<Page> pages = new ArrayList<>();
        pages.add(new Page("Home", "WorkerHomePage.fxml", "HomeIcon.png")); // Different home page for workers
        pages.add(new Page("Materials", "MaterialsPage.fxml", "MaterialsIcon.png"));
        pages.add(new Page("Constructions", "ConstructionsPage.fxml", "ConstructionsIcon.png"));
        return pages;
    }
}
