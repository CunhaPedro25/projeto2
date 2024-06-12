module org.projeto.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.projeto.data;
    requires spring.boot;
    requires lombok;
    requires java.smartcardio;
    requires spring.context;
    requires spring.beans;

    opens org.projeto.desktop to javafx.fxml;
    exports org.projeto.desktop;
//    opens org.projeto.desktop.pages to javafx.fxml;
//    exports org.projeto.desktop.pages;
    opens org.projeto.desktop.pages.authentication to javafx.fxml;
    exports org.projeto.desktop.pages.authentication;
    opens org.projeto.desktop.pages.modals to javafx.fxml;
    exports org.projeto.desktop.pages.modals;
    opens org.projeto.desktop.pages.dashboard to javafx.fxml;
    exports org.projeto.desktop.pages.dashboard;
    opens org.projeto.desktop.pages.dashboard.secretary to javafx.fxml;
    exports org.projeto.desktop.pages.dashboard.secretary;
    opens org.projeto.desktop.pages.dashboard.client to javafx.fxml;
    exports org.projeto.desktop.pages.dashboard.client;
    opens org.projeto.desktop.components to javafx.fxml;
    exports org.projeto.desktop.components;
    exports org.projeto.desktop.factory;
    opens org.projeto.desktop.factory to javafx.fxml;
}
