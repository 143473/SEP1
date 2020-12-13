import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class ManageRequirementGUI
{

  private Label title;

  private TextField id;
  private TextField name;
  private TextField userStory;
  private TextField estimatedTime;
  private TextField day;
  private TextField month;
  private TextField year;
  private TextField spentTime;

  private ChoiceBox statusBox;
  private ChoiceBox responsibleEmployeeBox;
  private ChoiceBox importanceBox;

  private TableView<Requirement> requirementsTable;
  private TableColumn requirementCol;

  private Label idLabel;
  private Label nameLabel;
  private Label userStoryLabel;
  private Label estimatedTimeLabel;
  private Label deadlineLabel;
  private Label statusLabel;
  private Label responsibleEmployeeLabel;
  private Label importanceLabel;
  private Label spentTimeLabel;

  private Button saveButton;
  private Button cancelButton;
  private Button removeButton;

  private VBox mainPane;
  private HBox mainContent;
  private HBox bottomButtons;
  private GridPane requirementForm;
  private HBox datePane;

  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private MyListListener listListener;
  private MyActionListener listener;

  public ManageRequirementGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listListener = new MyListListener();
    listener = new MyActionListener();

    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

  title = new Label("Manage Requirements");
  title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

  id = new TextField();
  id.setDisable(true);
  name = new TextField();
  userStory = new TextField();
  estimatedTime= new TextField();
  spentTime = new TextField();
  spentTime.setDisable(true);

  day = new TextField();
  day.setPromptText("dd");
  day.setMaxWidth(40);
  month = new TextField();
  month.setPromptText("mm");
  month.setMaxWidth(40);
  year = new TextField();
  year.setPromptText("yyyy");
  year.setMaxWidth(60);

  datePane = new HBox(5);
  datePane.getChildren().addAll(day,month,year);

  statusBox = new ChoiceBox();
  responsibleEmployeeBox = new ChoiceBox();
  importanceBox = new ChoiceBox();

  idLabel = new Label("Id");
  nameLabel = new Label("Name");
  userStoryLabel = new Label("User Story");
  estimatedTimeLabel = new Label("Estimation in hours");
  deadlineLabel = new Label("Deadline");
  statusLabel = new Label("Status");
  responsibleEmployeeLabel = new Label("Responsible Employee");
  importanceLabel = new Label("Importance");
  spentTimeLabel = new Label("Spent time");

  saveButton = new Button("Save");
  saveButton.setOnAction(listener);
  cancelButton = new Button("Cancel");
  removeButton = new Button("Remove");
  removeButton.setOnAction(listener);


    requirementCol = new TableColumn("Requirement name");
    requirementCol.setCellValueFactory(new PropertyValueFactory("name"));

    requirementsTable = new TableView<Requirement>();

    requirementsTable.getColumns().setAll(requirementCol);
    requirementsTable.setPrefWidth(450);
    requirementsTable.setPrefHeight(300);
    requirementsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    requirementsTable.getSelectionModel().selectedItemProperty().addListener(listListener);


  requirementForm = new GridPane();
  requirementForm.setHgap(5);
  requirementForm.setVgap(5);
  requirementForm.addRow(0, idLabel, id);
  requirementForm.addRow(1, nameLabel, name);
  requirementForm.addRow(2, userStoryLabel, userStory);
  requirementForm.addRow(3, estimatedTimeLabel, estimatedTime);
  requirementForm.addRow(4, deadlineLabel, datePane);
  requirementForm.addRow(5, statusLabel, statusBox);
  requirementForm.addRow(6, responsibleEmployeeLabel, responsibleEmployeeBox);
  requirementForm.addRow(7, importanceLabel, importanceBox);
  requirementForm.addRow(8, spentTimeLabel, spentTime);

  mainContent = new HBox(10);
  mainContent.getChildren().addAll(requirementForm, requirementsTable);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(saveButton,cancelButton,removeButton);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,mainContent,bottomButtons);

}
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
  }

  public Button getCancel()
  {
    return cancelButton;
  }

  private void initializeTable(){
    requirementsTable.getItems().clear();
    int selectedProjectIndex = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedIndex();
    ArrayList<Requirement> requirements = projectsAdapter.getAllProjects().get(selectedProjectIndex).getRequirements();

    System.out.println(requirements);
    for (int i = 0; i < requirements.size(); i++) {
      requirementsTable.getItems().add(requirements.get(i));
    }
  }

  public void clearFields(){
    id.setText("");
    name.setText("");
    userStory.setText("");
    estimatedTime.setText("");
    day.setText("");
    month.setText("");
    year.setText("");
    spentTime.setText("");
    statusBox.getSelectionModel().selectFirst();
    responsibleEmployeeBox.getSelectionModel().clearSelection();
    importanceBox.getSelectionModel().selectFirst();
  }
  private class MyListListener implements ChangeListener<Requirement> {
    public void changed(ObservableValue<? extends Requirement> requirement, Requirement oldRequirement, Requirement newRequirement)
    {

      Requirement temp = requirementsTable.getSelectionModel().getSelectedItem();
      int projectIndex = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getFocusedIndex();
      int requirementIndex = requirementsTable.getSelectionModel().getSelectedIndex();
      if (temp != null)
      {
        Requirement selectedRequirement = projectsAdapter.getSelectedRequirement(projectIndex, requirementIndex);
        id.setText(String.valueOf(selectedRequirement.getId()));
        name.setText(selectedRequirement.getName());
        userStory.setText(selectedRequirement.getUserStory());
        estimatedTime.setText(String.valueOf(selectedRequirement.getEstimatedTime()));
        day.setText(String.valueOf(selectedRequirement.getDeadline().getDay()));
        month.setText(String.valueOf(selectedRequirement.getDeadline().getMonth()));
        year.setText(String.valueOf(selectedRequirement.getDeadline().getYear()));
        spentTime.setText(String.valueOf(selectedRequirement.getSpentTime()));

        statusBox.getItems().clear();
        responsibleEmployeeBox.getItems().clear();
        importanceBox.getItems().clear();

        importanceBox.getItems().addAll(1, 2, 3);
        importanceBox.getSelectionModel().select(selectedRequirement.getImportance()-1);

        AssignedEmployeeList assignedEmployeeList = projectsAdapter.getAllProjects().get(projectIndex).getAssignedEmployeeList();
        for (int i = 0; i < assignedEmployeeList.size(); i++) {
          if(!responsibleEmployeeBox.getItems().contains(assignedEmployeeList.get(i))){
            responsibleEmployeeBox.getItems().add(assignedEmployeeList.get(i));
          }
        }
        responsibleEmployeeBox.getSelectionModel().select(selectedRequirement.getResponsibleEmployee());

        ProgressStatus status = new ProgressStatus();
        for (int i = 0; i < status.getStatuses().length; i++) {
          statusBox.getItems().add(status.getStatuses()[i]);
        }
        statusBox.getSelectionModel().select(selectedRequirement.getStatus());
      }
    }
  }

  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      if(e.getSource() == removeButton){
        if (!(requirementsTable.getSelectionModel().getSelectedItem() == null))
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "Do you really want to delete project "+requirementsTable.getSelectionModel().getSelectedItem().getName()+"?", ButtonType.YES, ButtonType.NO);
          alert.setTitle("Delete project");
          alert.setHeaderText(null);

          alert.showAndWait();
          int requirementIndex = requirementsTable.getSelectionModel().getSelectedIndex();
          int projectIndex = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedIndex();
          if (alert.getResult() == ButtonType.YES)
          {
            projectsAdapter.deleteRequirement(projectIndex, requirementIndex);


            id.setText("");
            name.setText("");
            userStory.setText("");
            estimatedTime.setText("");
            day.setText("");
            month.setText("");
            year.setText("");
            spentTime.setText("");
            statusBox.getSelectionModel().selectFirst();
            responsibleEmployeeBox.getSelectionModel().clearSelection();
            importanceBox.getSelectionModel().selectFirst();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("Deleting successful");
            alert2.setContentText("Changes were saved successfully!");
            alert2.showAndWait();


            initializeTable();
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("No project was chosen!");
          alert.showAndWait();
        }
      }
    }
  }
}

