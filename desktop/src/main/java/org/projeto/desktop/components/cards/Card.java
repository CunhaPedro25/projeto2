package org.projeto.desktop.components.cards;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.List;

// TODO: Make an abstract card component to use it in the home page of all users
public abstract class Card extends GridPane {
    public String title;
    public String subtitle;
    public List<String> infoList;
    public String chipText;

    public Card(String title) {
        this.title = title;

        // Set up column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.NEVER);
        this.getColumnConstraints().addAll(col1, col2, col3);

        // Set up row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(javafx.scene.layout.Priority.ALWAYS);
        this.getRowConstraints().addAll(row1, row2);

        // Set up VBox for title
        Label titleLabel = new Label(title);
        VBox titleBox = new VBox();
        titleBox.getChildren().addAll(titleLabel);

        // Add titleBox to the grid pane
        this.add(titleBox, 0, 0);
    }


    public void addChip(String chipText) {
        if (this.chipText != null) {
            System.out.println("Chip already exists, replacing current chip...");
            this.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().equals(this.chipText));
        }

        this.chipText = chipText;
        Label chipLabel = new Label(chipText);
        this.add(chipLabel, 1, 1);
    }
}
