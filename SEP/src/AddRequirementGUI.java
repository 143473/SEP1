import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *  A class handling adding a new employee
 *  * @author Timothy Johan Engkar, Marketa Lapcikova, Claudiu Emanuel Cordunianu
 *  * @version 1.0
 */

public class AddRequirementGUI
{
  private ProjectsAdapter projectsAdapter;
  private ProjectList projectList;
  private SepGUI sepGUI;
  private Project currentProject;

  private Label title;

  private TextField name;
  private TextField userStory;
  private TextField day;
  private TextField month;
  private TextField year;

  private ChoiceBox<Integer> importanceBox;
  private ChoiceBox<String> statusBox;
  private ChoiceBox<AssignedEmployee> responsibleEmployeeBox;

  private Label nameLabel;
  private Label userStoryLabel;
  private Label deadlineLabel;
  private Label statusLabel;
  private Label responsibleEmployeeLabel;
  private Label importanceLabel;

  private Button save;
  private Button cancel;

  private VBox mainPane;
  private HBox bottomButtons;
  private GridPane requirementForm;
  private HBox datePane;

  /**
   * 2-argument constructor initializing all the parts of the GUI
   * @param projectsAdapter adapter of projects, requirements and tasks
   * @param sepGUI the main GUI where all the other GUIs are connected
   */
  public AddRequirementGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){
    this.projectsAdapter = projectsAdapter;
    projectList = projectsAdapter.getAllProjects();
    this.sepGUI = sepGUI;

    title = new Label("Add Requirement");
    title.getStyleClass().add("heading");

    nameLabel = new Label("Name");
    name = new TextField();

    userStoryLabel = new Label("User Story");
    userStory = new TextField();

    importanceLabel = new Label("Importance");
    importanceBox = new ChoiceBox<Integer>();
    importanceBox.getItems().addAll(1, 2, 3);
    importanceBox.getSelectionModel().selectFirst();

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
    requirementForm.addRow(3,deadlineLabel,datePane);
    requirementForm.addRow(4,statusLabel,statusBox);
    requirementForm.addRow(5,responsibleEmployeeLabel,responsibleEmployeeBox);
    requirementForm.addRow(6, importanceLabel, importanceBox);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,cancel);


    mainPane = new VBox(5);
    mainPane.getChildren().addAll(title,requirementForm,bottomButtons);


  }

  /**
   * Sets the projectList to all projects
   */
  public void setProjectList() {
    projectList = projectsAdapter.getAllProjects();
  }

  /**
   * Initializes the current project in the ResponsibleEmployeeBox
   */
  public void initializeCurrentProject(){
    currentProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();

    title.setText("Requirement for:  " + currentProject.getName());
    initializeResponsibleEmployeeBox();
  }

  /**
   * Initializes ResponsibleEmployeeBox
   */
  public void initializeResponsibleEmployeeBox(){
    responsibleEmployeeBox.getItems().clear();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      responsibleEmployeeBox.getItems().add(chosenAssignedEmployees.get(i));
    }
    responsibleEmployeeBox.getSelectionModel().selectFirst();
  }

  /**
   * Checks the validity of entered data after the save Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callSaveButton(){
    Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
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
    else{
      for (int i = 0; i < projectsAdapter.getSelectedProject(project.getName()).getRequirements().size(); i++) {
        if(projectsAdapter.getSelectedProject(project.getName()).getRequirements().get(i).getName().equals(name.getText())){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("This name of requirement for this project already exists!");
          alert.showAndWait();
          allValuesCorrect = false;
        }
      }
    }

    if(userStory.getText().equals("") || userStory.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("User story cannot be empty!");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    else{
      for (int i = 0; i < projectsAdapter.getSelectedProject(project.getName()).getRequirements().size(); i++) {
        if(projectsAdapter.getSelectedProject(project.getName()).getRequirements().get(i).getUserStory().equals(userStory.getText())){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("This user story for this project already exists!");
          alert.showAndWait();
          allValuesCorrect = false;
        }
      }
    }

    if(day.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Deadline cannot be empty!");
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
          ProjectList projectList = projectsAdapter.getAllProjects();

          /*double estimationTime = Double.parseDouble(estimation.getText().replaceFirst("^0+(?!$)", ""));*/

          requirement = new Requirement(name.getText(), userStory.getText(),
                 importanceBox.getValue().intValue(), responsibleEmployeeBox.getSelectionModel().getSelectedItem(),
                  deadline, project.getRequirements().size(), statusBox.getValue());
          requirement.setProgressStatus(statusBox.getSelectionModel().getSelectedItem());
          requirement.setProject(project);

          boolean equals = false;
          for (int i = 0; i < project.getRequirements().size(); i++) {
            if(project.getRequirements().get(i).equals(requirement)){
              equals = true;
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Duplicate requirement");
              alert.setContentText("This requirement already exists!");
              alert.showAndWait();
              allValuesCorrect = false;
            }
          }
          if(!equals){
            projectList.removeProject(project.getName());
            project.addRequirement(requirement);
            projectList.addProject(project);
            projectsAdapter.saveProjects(projectList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adding successful");
            alert.setContentText("New requirement was successfully added to the list!");
            alert.showAndWait();
          }
        }
      }
    }
    return allValuesCorrect;
  }

  /**
   * Initializes the currentProject and gets the mainPane of the GUI
   * @return VBox mainPane with all the elements of the GUI AddRequirement
   */
  public VBox getMainPane(){
    initializeCurrentProject();
    return mainPane;
  }

  /**
   * Gets the cancel Button
   * @return Button cancel
   */
  public Button getCancel(){
    return cancel;
  }

  /**
   * Gets the save Button
   * @return Button save
   */
  public Button getSave(){
    return save;
  }

  /**
   * Clears all the fields, sets the textFields to empty and clears the choice in choiceBoxes
   */
  public void clearFields(){
    name.setText("");
    userStory.setText("");
    day.setText("");
    month.setText("");
    year.setText("");
    statusBox.getSelectionModel().selectFirst();
    responsibleEmployeeBox.getSelectionModel().selectFirst();
  }
}