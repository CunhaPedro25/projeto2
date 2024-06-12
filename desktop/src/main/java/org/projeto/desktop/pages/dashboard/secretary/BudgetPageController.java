/*
package org.projeto.desktop.pages.dashboard.secretary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.projeto.data.entities.Budget;
import org.projeto.desktop.SceneManager;

import java.time.LocalDate;

public class BudgetPageController {
    @FXML
    public Button newProject;
    @FXML
    public TableColumn<Budget, String> clientColumn;
    @FXML
    public TableColumn<Budget, String> engineerColumn;
    @FXML
    public TableColumn<Budget, Integer> projectColumn;
    @FXML
    public TableColumn<Budget, LocalDate> create_dateColumn;
    @FXML
    public TableColumn<Budget, String> acceptedColumn;

    @FXML
    private TableView<Budget> table;

    public void initialize() {
//        populateTableView();
    }

//    private void populateTableView() {
//        List<Budget> budgets = BudgetService.getAllBudgets();
//
//        // Convert List to ObservableList
//        ObservableList<Budget> budgetObservableList = FXCollections.observableArrayList(budgets);
//
//        clientColumn.setCellValueFactory(cellData -> {
//            Budget budget = cellData.getValue();
//            String client = budget.getClient().getName();
//            return new SimpleStringProperty(client);
//        });
//        engineerColumn.setCellValueFactory(cellData -> {
//            Budget budget = cellData.getValue();
//            String engineer = budget.getEngineer().getName();
//            return new SimpleStringProperty(engineer);
//        });
//
//        projectColumn.setCellValueFactory(cellData -> {
//            Budget budget = cellData.getValue();
//            int project = budget.getProject().getId();
//            return new SimpleIntegerProperty(project).asObject();
//        });
//
//        create_dateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
//
//        acceptedColumn.setCellValueFactory(cellData -> {
//            Budget budget = cellData.getValue();
//            if(budget.getAccepted() == null){
//                return new SimpleStringProperty("Pending");
//            }
//            return new SimpleStringProperty(budget.getAccepted().toString());
//        });
//
//        // Populate the TableView
//        table.setItems(budgetObservableList);
//    }

    @FXML
    public void openModal() {
        SceneManager.openNewModal("pages/modals/add-budget.fxml", "Add Budget", true);
    }
}
*/
