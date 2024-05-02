package org.projeto.desktop.components;

import lombok.Setter;
import org.projeto.desktop.factory.Page;
import org.projeto.desktop.factory.PageFactory;
import java.util.List;

@Setter
public class SideBarController {
    private PageFactory pageFactory;

    public void displayPages() {
        List<Page> pages = pageFactory.createPages();
    }
}
