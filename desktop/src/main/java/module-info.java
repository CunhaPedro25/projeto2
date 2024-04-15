module org.projeto.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.projeto.data;
    requires spring.boot;
    requires lombok;

    opens org.projeto.desktop to javafx.fxml;
    exports org.projeto.desktop;
    opens org.projeto.desktop.pages to javafx.fxml;
    exports org.projeto.desktop.pages;
}
