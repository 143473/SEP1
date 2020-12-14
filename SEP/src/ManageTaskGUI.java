import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ManageTaskGUI
{
  private ProjectsAdapter projectsAdapter;
  private ProjectList projectList;
  private SepGUI sepGUI;
  private Requirement currentRequirement;
  private Project project;

  private TableView<Task> tasksTable;
  private TableColumn taskCol;

  private Label title;

  private TextField nameField;
  private TextField descriptionField;
  private TextField estimationField;
  private TextField dayField;
  private TextField monthField;
  private TextField yearField;
  private TextField idField;
  private TextField spentTimeField;

  private ChoiceBox<String> statusBox;
  private ChoiceBox<AssignedEmployee> responsibleEmployeeBox;

  private Label nameLabel;
  private Label descriptionLabel;
  private Label estimationLabel;
  private Label deadlineLabel;
  private Label idLabel;
  private Label statusLabel;
  private Label responsibleEmployeeLabel;
  private Label spentTimeLabel;

  private Button saveButton;
  private Button cancelButton;
  private Button removeButton;

  private VBox mainPane;
  private VBox vboxforlabels;
  private VBox vbox;

  private HBox bottomButtons;
  private HBox hboxfordate;
  private HBox mainContent;
  private HBox hbox;

  private MyListListener listListener;
  private MyActionListener listener;

  public ManageTaskGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listener = new MyActionListener();
    listListener = new MyListListener();

    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    title = new Label("Manage Tasks");
    title.getStyleClass().add("heading");

    idLabel = new Label("ID");
    idField = new TextField();
    idField.setDisable(true);

    nameLabel = new Label("Name");
    nameField = new TextField();

    descriptionLabel = new Label("Description");
    descriptionField = new TextField();

    estimationLabel = new Label("Estimation");
    estimationField = new TextField();

    spentTimeLabel = new Label("Spent time");
    spentTimeField = new TextField();
    spentTimeField.setDisable(true);

    deadlineLabel = new Label("Deadline");
    dayField = new TextField();
    dayField.setPromptText("dd");
    dayField.setMaxWidth(40);
    monthField = new TextField();
    monthField.setPromptText("mm");
    monthField.setMaxWidth(40);
    yearField = new TextField();
    yearField.setPromptText("yyyy");
    yearField.setMaxWidth(60);

    statusLabel = new Label("Status");
    statusBox = new ChoiceBox();
    ProgressStatus progressStatus = new ProgressStatus();
    String[] statuses = progressStatus.getStatuses();
    for (int i = 0; i < statuses.length; i++)
    {
      statusBox.getItems().add(statuses[i]);
    }
    statusBox.setValue(statuses[progressStatus.getDefaultIndex()]);

    responsibleEmployeeLabel = new Label("Responsible Employee");
    responsibleEmployeeBox = new ChoiceBox<AssignedEmployee>();


    taskCol = new TableColumn("Requirement name");
    taskCol.setCellValueFactory(new PropertyValueFactory("name"));

    tasksTable = new TableView<Task>();

    tasksTable.getColumns().setAll(taskCol);
    tasksTable.setPrefWidth(450);
    tasksTable.setPrefHeight(300);
    tasksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    tasksTable.getSelectionModel().selectedItemProperty().addListener(listListener);

    saveButton = new Button("Save");
    saveButton.setOnAction(listener);
    cancelButton = new Button("Cancel");
    removeButton = new Button("Remove");
    removeButton.setOnAction(listener);

    vboxforlabels = new VBox();
    vboxforlabels.setSpacing(20);
    vboxforlabels.getChildren().addAll(nameLabel, descriptionLabel, estimationLabel, spentTimeLabel, deadlineLabel, idLabel, statusLabel, responsibleEmployeeLabel);

    hboxfordate = new HBox();
    hboxfordate.setSpacing(5);
    hboxfordate.getChildren().addAll(dayField, monthField, yearField);

    vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(nameField, descriptionField, estimationField, spentTimeField, hboxfordate, idField, statusBox, responsibleEmployeeBox);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(saveButton,cancelButton,removeButton);

    hbox = new HBox();
    hbox.setSpacing(20);
    hbox.getChildren().addAll(vboxforlabels,vbox);

    mainContent = new HBox(20);
    mainContent.getChildren().addAll(hbox, tasksTable);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
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
    tasksTable.getItems().clear();
    Project selectedProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
    Requirement selectedRequirement = sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
    ArrayList<Task> tasks = projectsAdapter.getSelectedRequirement(selectedProject.getName(), selectedRequirement.getId()).getTasks();

    System.out.println(tasks);
    for (int i = 0; i < tasks.size(); i++) {
      tasksTable.getItems().add(tasks.get(i));
    }
  }

  public void clearFields(){
    nameField.setText("");
    descriptionField.setText("");
    estimationField.setText("");
    dayField.setText("");
    monthField.setText("");
    yearField.setText("");
    idField.setText("");
    statusBox.getSelectionModel().selectFirst();
    responsibleEmployeeBox.getSelectionModel().clearSelection();
  }

  private class MyListListener implements ChangeListener<Task> {
    public void changed(ObservableValue<? extends Task> task, Task oldTask, Task newTask)
    {
      Task temp = tasksTable.getSelectionModel().getSelectedItem();
      Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
      Requirement requirement = sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
      Task previouslySelectedTask = tasksTable.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        Task selectedTask = projectsAdapter.getSelectedTask(project.getName(), requirement.getId(), previouslySelectedTask.getId());

        nameField.setText(selectedTask.getName());
        descriptionField.setText(selectedTask.getDescription());
        estimationField.setText(String.valueOf(selectedTask.getEstimatedTime()));
        dayField.setText(String.valueOf(selectedTask.getDeadline().getDay()));
        monthField.setText(String.valueOf(selectedTask.getDeadline().getMonth()));
        yearField.setText(String.valueOf(selectedTask.getDeadline().getYear()));
        idField.setText(String.valueOf(selectedTask.getId()));
        spentTimeField.setText(String.valueOf(selectedTask.getSpentTime()));

        statusBox.getItems().clear();
        responsibleEmployeeBox.getItems().clear();

        AssignedEmployeeList assignedEmployeeList = projectsAdapter.getAllProjects().getProject(project).getAssignedEmployeeList();
        for (int i = 0; i < assignedEmployeeList.size(); i++) {
          if(!responsibleEmployeeBox.getItems().contains(assignedEmployeeList.get(i))){
            responsibleEmployeeBox.getItems().add(assignedEmployeeList.get(i));
          }
        }
        responsibleEmployeeBox.getSelectionModel().select(selectedTask.getResponsibleEmployee());

        ProgressStatus status = new ProgressStatus();
        for (int i = 0; i < status.getStatuses().length; i++) {
          statusBox.getItems().add(status.getStatuses()[i]);
        }
        statusBox.getSelectionModel().select(selectedTask.getStatus());
      }
    }
  }

  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
      Requirement requirement = sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
      Task task = tasksTable.getSelectionModel().getSelectedItem();

      if(e.getSource() == saveButton){
        Task selectedTask = projectsAdapter.getSelectedTask(project.getName(), requirement.getId(), task.getId());

        boolean OK = true;

        if(nameField.getText().trim().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Name of task cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        else{
          for (int i = 0; i < projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().size(); i++) {
            if(projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().get(i).getName().equals(nameField.getText())
                    && !(projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().get(i).getId() == Integer.parseInt(idField.getText()))){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Warning");
              alert.setContentText("This name of task for this requirement in this project already exists!");
              alert.showAndWait();
              OK = false;
            }
          }
        }

        if(descriptionField.getText().trim().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Description cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        else{
          for (int i = 0; i < projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().size(); i++) {
            if(projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().get(i).getDescription().equals(descriptionField.getText())
                    && !(projectsAdapter.getSelectedRequirement(project.getName(), requirement.getId()).getTasks().get(i).getId() == Integer.parseInt(idField.getText()))){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Warning");
              alert.setContentText("This description of task for this requirement in this project already exists!");
              alert.showAndWait();
              OK = false;
            }
          }
        }

        if(estimationField.getText().trim().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Estimated time cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        else{
          try{
            double estimationTemporary = Double.parseDouble(estimationField.getText());
          }
          catch (NumberFormatException nfe)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Value in estimated time has to be a number!");
            alert.showAndWait();
            OK = false;
          }
        }
        if(dayField.getText().isEmpty() || monthField.getText().isEmpty() || yearField.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Invalid input");
          alert.setContentText("Deadline cannot be empty!");
          alert.showAndWait();
          OK= false;
        }
        else
        {
          try
          {
            int temporary = Integer.parseInt(dayField.getText());
            temporary = Integer.parseInt(monthField.getText());
            temporary = Integer.parseInt(yearField.getText());
          }
          catch (NumberFormatException nfe)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Values in deadline have to be numbers!");
            alert.showAndWait();
            OK = false;
          }
        }
        if(OK){
          MyDate deadline = new MyDate(
                  Integer.parseInt(dayField.getText().replaceFirst("^0+(?!$)", "")),
                  Integer.parseInt(monthField.getText().replaceFirst("^0+(?!$)", "")),
                  Integer.parseInt(yearField.getText().replaceFirst("^0+(?!$)", "")));

          if (!deadline.isValidDate())
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Entered date is not valid!");
            alert.showAndWait();
            OK = false;
          }
          if(OK){
            ProjectList projectList = projectsAdapter.getAllProjects();
            Task chosenTask = projectList.getProject(project).getRequirements().get(requirement.getId()).getTasks().get(task.getId());

            chosenTask.setName(nameField.getText());
            chosenTask.setDescription(descriptionField.getText());
            chosenTask.setEstimatedTime(Double.parseDouble(estimationField.getText()));
            chosenTask.setDeadline(deadline);
            chosenTask.setStatus(statusBox.getSelectionModel().getSelectedItem());
            chosenTask.setResponsibleEmployee(responsibleEmployeeBox.getSelectionModel().getSelectedItem());
            projectsAdapter.saveProjects(projectList);

            initializeTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Managing successful");
            alert.setContentText("Requirement was successfully edited!");
            alert.showAndWait();
          }
        }

      }
      if(e.getSource() == removeButton){
        if (!(tasksTable.getSelectionModel().getSelectedItem() == null))
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "Do you really want to delete task "+tasksTable.getSelectionModel().getSelectedItem().getName()+"?", ButtonType.YES, ButtonType.NO);
          alert.setTitle("Delete task");
          alert.setHeaderText(null);

          alert.showAndWait();
          if (alert.getResult() == ButtonType.YES)
          {
            projectsAdapter.deleteTask(project, requirement.getId(), task.getId());

            clearFields();

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
          alert.setContentText("No task was chosen!");
          alert.showAndWait();
        }
      }
    }
  }
}
