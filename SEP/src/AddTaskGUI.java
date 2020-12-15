import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddTaskGUI
{
  private ProjectsAdapter projectsAdapter;
  private ProjectList projectList;
  private SepGUI sepGUI;
  private Requirement currentRequirement;
  private Project project;

  private Label title;

  private TextField nameField;
  private TextField descriptionField;
  private TextField estimationField;
  private TextField dayField;
  private TextField monthField;
  private TextField yearField;

  private ChoiceBox<String> statusBox;
  private ChoiceBox<AssignedEmployee> responsibleEmployeeBox;

  private Label nameLabel;
  private Label descriptionLabel;
  private Label estimationLabel;
  private Label deadline;
  private Label statusLabel;
  private Label responsibleEmployeeLabel;

  private Button save;
  private Button cancel;
  private Button remove;

  private VBox mainPane;
  private VBox vboxforlabels;
  private VBox vbox;

  private HBox bottomButtons;
  private HBox hboxfordate;
  private HBox hbox;


  public AddTaskGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;
    title = new Label("Add Task");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

    nameLabel = new Label("Name");
    nameField = new TextField();

    descriptionLabel = new Label("Description");
    descriptionField = new TextField();

    estimationLabel = new Label("Estimation");
    estimationField = new TextField();

    deadline = new Label("Deadline");
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

    save = new Button("Save");
    cancel = new Button("Cancel");

    vboxforlabels = new VBox();
    vboxforlabels.setSpacing(20);
    vboxforlabels.getChildren().addAll(nameLabel, descriptionLabel, estimationLabel,deadline, statusLabel, responsibleEmployeeLabel);

    hboxfordate = new HBox();
    hboxfordate.setSpacing(5);
    hboxfordate.getChildren().addAll(dayField, monthField, yearField);

    vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(nameField, descriptionField, estimationField,hboxfordate,statusBox, responsibleEmployeeBox);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,cancel);

    hbox = new HBox();
    hbox.setSpacing(20);
    hbox.getChildren().addAll(vboxforlabels,vbox);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,hbox,bottomButtons);


  }

  public VBox getMainPane()
  {
    initializeResponsibleEmployeeBox();
    return mainPane;
  }

  public Button getCancel()
  {
    return cancel;
  }

  public Button getSave(){
    return save;
  }

  public void setProjectList() {
    projectList = projectsAdapter.getAllProjects();
  }

  public void initializeResponsibleEmployeeBox(){

    responsibleEmployeeBox.getItems().clear();
    project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
    AssignedEmployeeList chosenAssignedEmployees = project.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      responsibleEmployeeBox.getItems().add(chosenAssignedEmployees.get(i));
      System.out.println(chosenAssignedEmployees.get(i));
    }
    responsibleEmployeeBox.getSelectionModel().selectFirst();
  }

  public boolean callSaveButton(){
    Task task;
    MyDate deadline;
    boolean allValuesCorrect = true;
    if(nameField.getText().equals("") || nameField.getText().trim().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Task name cannot be empty!");
      alert.showAndWait();
      allValuesCorrect =  false;
    }
    else if(descriptionField.getText().equals("") || descriptionField.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Task description cannot be empty!");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    else if(estimationField.getText().equals("") || estimationField.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Estimation cannot be empty!");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    else{
      try{
        double estimationTemporary = Double.parseDouble(estimationField.getText());
      }
      catch (NumberFormatException nfe) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Invalid input");
        alert.setContentText("Value in estimation has to be a number!");
        alert.showAndWait();
        allValuesCorrect = false;
      }
    }

    if(dayField.getText().isEmpty() || monthField.getText().isEmpty() || yearField.getText().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Date of birth cannot be empty!");
      alert.showAndWait();
      allValuesCorrect= false;
    }
    else
    {
      try {
        int temporary = Integer.parseInt(dayField.getText());
        temporary = Integer.parseInt(monthField.getText());
        temporary = Integer.parseInt(yearField.getText());
      }
      catch (NumberFormatException nfe) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Invalid input");
        alert.setContentText("Values in deadline have to be numbers!");
        alert.showAndWait();
        allValuesCorrect = false;
      }

      if (allValuesCorrect) {
        deadline = new MyDate(
                Integer.parseInt(dayField.getText().replaceFirst("^0+(?!$)", "")),
                Integer.parseInt(monthField.getText().replaceFirst("^0+(?!$)", "")),
                Integer.parseInt(yearField.getText().replaceFirst("^0+(?!$)", "")));

        if (!deadline.isValidDate()) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Invalid input");
          alert.setContentText("Entered date is not valid!");
          alert.showAndWait();
          allValuesCorrect = false;
        }
        if (allValuesCorrect) {
          ProjectList projectList = projectsAdapter.getAllProjects();
          Project selectedProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
          Project project = projectList.getProjectByName(selectedProject.getName());
          Requirement selectedRequirement = sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
          Requirement requirement = project.getRequirementByName(selectedRequirement.getName());


          double estimationTime = Double.parseDouble(estimationField.getText().replaceFirst("^0+(?!$)", ""));

          Task newTask = new Task(nameField.getText(), descriptionField.getText(), estimationTime,
                  deadline, requirement.getTasks().size(), statusBox.getSelectionModel().getSelectedItem(),
                  responsibleEmployeeBox.getSelectionModel().getSelectedItem());
          /*
          newTask.setProject(project);
          newTask.setRequirement(requirement);


           */
          boolean equals = false;
          for (int i = 0; i < requirement.getTasks().size(); i++) {
            if(project.getRequirements().get(requirement.getId()).getTasks().get(i).equals(newTask)){
              equals = true;
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Duplicate task");
              alert.setContentText("This task already exists!");
              alert.showAndWait();
              allValuesCorrect = false;
            }
          }
          if(!equals){
            requirement.addTask(newTask);
            /*
            projectList.removeProject(project.getName());
            project.removeRequirement(requirement);
            System.out.println("before "+ requirement.getTasks().size());
            requirement.addTask(newTask);
            System.out.println("after "+ requirement.getTasks().size());
            project.addRequirement(requirement);
            System.out.println(requirement.getTasks());
            projectList.addProject(project);
            System.out.println(project.getRequirements().get(0).getTasks());
             */
            projectsAdapter.saveProjects(projectList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adding successful");
            alert.setContentText("New task was successfully added to the list!");
            alert.showAndWait();
          }
        }
      }
    }
    return allValuesCorrect;
  }


  public void clearFields(){
    nameField.setText("");
    descriptionField.setText("");
    estimationField.setText("");
    dayField.setText("");
    monthField.setText("");
    yearField.setText("");
    statusBox.getSelectionModel().selectFirst();
    responsibleEmployeeBox.getSelectionModel().clearSelection();
  }

}
