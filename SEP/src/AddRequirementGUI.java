import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddRequirementGUI
{
  private ProjectsAdapter projectsAdapter;
  private ProjectList projectList;
  private SepGUI sepGUI;
  private Project currentProject;

  private Label title;

  private TextField name;
  private TextField userStory;
  private TextField estimation;
  private TextField day;
  private TextField month;
  private TextField year;

  private ChoiceBox<String> statusBox;
  private ChoiceBox<AssignedEmployee> responsibleEmployeeBox;

  private Label nameLabel;
  private Label userStoryLabel;
  private Label estimatedTimeLabel;
  private Label deadlineLabel;
  private Label statusLabel;
  private Label responsibleEmployeeLabel;

  private Button save;
  private Button cancel;

  private VBox mainPane;
  private HBox bottomButtons;
  private GridPane requirementForm;
  private HBox datePane;

  public AddRequirementGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    this.projectsAdapter = projectsAdapter;
    projectList = projectsAdapter.getAllProjects();
    this.sepGUI = sepGUI;

    title = new Label("Add Requirement");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

    nameLabel = new Label("Name");
    name = new TextField();

    userStoryLabel = new Label("User Story");
    userStory = new TextField();

    estimation = new TextField();
    estimatedTimeLabel = new Label("Estimation");

    deadlineLabel = new Label("Deadline");
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

    requirementForm = new GridPane();
    requirementForm.setHgap(5);
    requirementForm.setVgap(5);
    requirementForm.addRow(0,nameLabel,name);
    requirementForm.addRow(1,userStoryLabel, userStory);
    requirementForm.addRow(2,estimatedTimeLabel, estimation);
    requirementForm.addRow(3,deadlineLabel,datePane);
    requirementForm.addRow(4,statusLabel,statusBox);
    requirementForm.addRow(5,responsibleEmployeeLabel,responsibleEmployeeBox);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,cancel);


    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,requirementForm,bottomButtons);


  }

  public void setProjectList() {
    projectList = projectsAdapter.getAllProjects();
  }
  public void initializeCurrentProject(){
    currentProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();

    title.setText("Requirement for:  " + currentProject.getName());
    initializeResponsibleEmployeeBox();
  }

  public void initializeResponsibleEmployeeBox(){
    responsibleEmployeeBox.getItems().clear();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      responsibleEmployeeBox.getItems().add(chosenAssignedEmployees.get(i));
      System.out.println(chosenAssignedEmployees.get(i));
    }
  }

  public boolean callSaveButton(){
    Requirement requirement;
    MyDate deadline;
    boolean allValuesCorrect = true;
    if(name.getText().equals("") || name.getText().trim().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Requirement name cannot be empty!");
      alert.showAndWait();
      allValuesCorrect =  false;
    }
    else if(userStory.getText().equals("") || userStory.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("User story cannot be empty!");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    else if(estimation.getText().equals("") || estimation.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("User story cannot be empty!");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    else if(day.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Date of birth cannot be empty!");
      alert.showAndWait();
      allValuesCorrect= false;
    }
    else
    {
      try
      {
        int temporary = Integer.parseInt(day.getText());
        temporary = Integer.parseInt(month.getText());
        temporary = Integer.parseInt(year.getText());
      }
      catch (NumberFormatException nfe)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Invalid input");
        alert.setContentText("Values in deadline have to be numbers!");
        alert.showAndWait();
        allValuesCorrect = false;
      }

      if (allValuesCorrect)
      {
        deadline = new MyDate(
            Integer.parseInt(day.getText().replaceFirst("^0+(?!$)", "")),
            Integer.parseInt(month.getText().replaceFirst("^0+(?!$)", "")),
            Integer.parseInt(year.getText().replaceFirst("^0+(?!$)", "")));
        requirement = new Requirement(name.getText(), userStory.getText(),
            Double.parseDouble(estimation.getText().replaceFirst("^0+(?!$)", "")),
            deadline);
        requirement.setProgressStatus(statusBox.getSelectionModel().getSelectedItem());
        requirement.setResponsibleEmployee(responsibleEmployeeBox.getSelectionModel().getSelectedItem());

        if (!deadline.isValidDate())
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Invalid input");
          alert.setContentText("Entered date is not valid!");
          alert.showAndWait();
          allValuesCorrect = false;
        }
        if (allValuesCorrect)
        {

          int index = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedIndex();
          Project selectedProject = projectsAdapter.getSelectedProject(index);

          for (int i = 0; i < selectedProject.getRequirements().size(); i++)
          {
            if (!(selectedProject.getRequirements().get(i).equals(requirement)))
            {
              selectedProject.addRequirement(requirement);
              projectsAdapter.saveProjects(projectList);
            }
            else
            {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Duplicate requirement");
              alert.setContentText("This requirement already exists!");
              alert.showAndWait();
              allValuesCorrect = false;
            }
          }
        }
      }
    }
    return allValuesCorrect;

  }

  public VBox getMainPane(){
    initializeCurrentProject();
    return mainPane;

  }

  public Button getCancel(){
    return cancel;
  }
  public Button getSave(){
    return save;
  }
}
