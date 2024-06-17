package org.projeto.desktop.pages.modals;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.projeto.data.entities.ConstructionType;
import org.projeto.data.entities.Project;
import org.projeto.data.entities.User;
import org.projeto.data.services.ConstructionTypeService;
import org.projeto.data.services.ProjectService;
import org.projeto.data.services.UserService;
import org.projeto.desktop.SceneManager;
import org.projeto.desktop.components.ProjectFormController;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class AddProjectModalController {

    @FXML
    private RadioButton residentialRadioButton;

    @FXML
    private RadioButton commercialRadioButton;

    @FXML
    private RadioButton industrialRadioButton;

    @FXML
    private ProjectFormController projectFormController;

    @FXML
    private Button save;

    @FXML
    private HBox constructionTypeSelection;

    private ToggleGroup constructionTypeGroup;

    private List<ConstructionType> constructionTypes;
    boolean edit = false;
    Project editProject = null;

    @FXML
    public void initialize() {
        this.constructionTypes = ConstructionTypeService.getAllConstructionTypes();

        constructionTypeGroup = new ToggleGroup();
        residentialRadioButton.setToggleGroup(constructionTypeGroup);
        commercialRadioButton.setToggleGroup(constructionTypeGroup);
        industrialRadioButton.setToggleGroup(constructionTypeGroup);
        List<String> clientEmails = UserService.getUserTypeAllEmails(1);
        List<String> engineerEmails = UserService.getUserTypeAllEmails(2);

        for (String email : clientEmails) {
            System.out.println("hi" + email);
        }

        for (String email : engineerEmails) {
            System.out.println("hi" + email);
        }

        projectFormController.clientComboBox.setItems(FXCollections.observableArrayList(clientEmails));
        projectFormController.engineerComboBox.setItems(FXCollections.observableArrayList(engineerEmails));
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        SceneManager.closeWindow(save);
    }

    @FXML
    protected void save() {
        RadioButton selectedToggle = (RadioButton) constructionTypeGroup.getSelectedToggle();
        String selectedConstructionTypeText = selectedToggle.getText();
        ConstructionType constructionType = constructionTypes.stream()
                .filter(type -> type.getType().equals(selectedConstructionTypeText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ConstructionType not found for description: " + selectedConstructionTypeText));
        try {
            if (edit) {
                System.out.println("editing");
                if (!projectFormController.isFormCorrect()) {
                    SceneManager.openErrorAlert("Error edit", "Please fill all the required fields correctly");
                    return;
                } else {
                    editProject.setRequirementsCreateDate(LocalDate.now());
                    editProject.setClient(UserService.findUserByEmail(projectFormController.clientComboBox.getValue()));
                    editProject.setEngineer(UserService.findUserByEmail(projectFormController.engineerComboBox.getValue()));
                    editProject.setBudget(new BigDecimal(projectFormController.budget.getText()));

                    ProjectService.update(editProject);
                    SceneManager.closeWindow(save);

                }
            } else {
                System.out.println("adding");
                if (!projectFormController.isFormCorrect()) {
                    SceneManager.openErrorAlert("Error register", "Please fill all the required fields correctly");
                    return;
                }
                Project project = Project.builder()
                        .client(UserService.findUserByEmail(projectFormController.clientComboBox.getValue()))
                        .engineer(UserService.findUserByEmail(projectFormController.engineerComboBox.getValue()))
                        .requirementsCreateDate(LocalDate.now())
                        .constructionType(constructionType)
                        .budgetCreateDate(null)
                        .budgetState(null)
                        .build();
                ProjectService.addNew(project);
                SceneManager.closeWindow(save);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableEdit(Project selectedProject) {
        edit = true;
        editProject = selectedProject;
        projectFormController.clientComboBox.setValue(selectedProject.getClient().getEmail());
        if (selectedProject.getEngineer() != null)
            projectFormController.engineerComboBox.setValue(selectedProject.getEngineer().getEmail());
        if (selectedProject.getRequirementsDocument() != null)
            projectFormController.requirements_document.setText(selectedProject.getRequirementsDocument());
    }

    private static class ClientListCell extends ListCell<User> {
        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getName());
            }
        }
    }
}
