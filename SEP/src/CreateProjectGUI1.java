import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * The 1st part of the project creation user interface, that allows for
 * setting up the project name and project description.
 * @author Claudiu Emanuel Cordunianu, Timothy Johan Engkar, Marketa Lapcikova
 * @version 1.0
 */
public class CreateProjectGUI1
{
  private SepGUI sepGUI;
  private ProjectsAdapter projectsAdapter;
  private GridPane gridPane;
  private VBox mainPane;

  private Label title;
  private Label projectName;
  private Label projectDesc;

  private TextField inputName;
  private TextField inputDescription;

  private Button buttonContinue;

  private boolean goingBack;
  private String previousProjectName;

  /**
   * 3-argument constructor initializing the GUI components
   * @param projectsAdapter adapter of the projects, requirements and tasks
   * @param sepGUI the main GUI where all the other GUIs are connected
   */
  public CreateProjectGUI1(ProjectsAdapter projectsAdapter, SepGUI sepGUI) {
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;


    title = new Label("Create a New Project");
    title.getStyleClass().add("heading");

    projectName = new Label("Project name:");
    inputName = new TextField();
    projectDesc = new Label("Project Description");
    inputDescription = new TextField();

    gridPane = new GridPane();
    gridPane.setVgap(8);
    gridPane.setHgap(8);
    gridPane.addRow(0, projectName, inputName);
    gridPane.addRow(1, projectDesc, inputDescription);

    buttonContinue = new Button("Continue");

    mainPane = new VBox(8);
    mainPane.getChildren().addAll(title, gridPane, buttonContinue);

  }
  /**
   * Method for getting the first part of Create Project GUI, to be used in mainGUI
   * @return VBox mainPane
   */
  public VBox getMainPane()
  {
    return mainPane;
  }

  /**
   * Checks all the fields necessary to continue for their values
   * @return boolean if all the values are correct, true if they are, false otherwise
   */
  public boolean callContinueButton(){
    boolean OK = true;
    if(inputName.getText().equals("") || inputName.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Project name cannot be empty!");
      alert.showAndWait();
      OK = false;
    }
    else if(inputDescription.getText().equals("") || inputDescription.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Project description cannot be empty!");
      alert.showAndWait();
      OK = false;
    }
    if(OK) {
      ProjectList projectList = projectsAdapter.getAllProjects();
      Project newProject = new Project(inputName.getText(), inputDescription.getText());
      boolean contains = false;
      if(!goingBack){
        for (int i = 0; i < projectList.size(); i++) {
          if(projectList.get(i).getName().equals(newProject.getName())){
            contains = true;
          }
        }
      }
      else{
        for (int i = 0; i < projectList.size()-1; i++) {
          if(projectList.get(i).getName().equals(newProject.getName())){
            contains = true;
          }
        }
      }
      if(contains){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Duplicate project");
        alert.setContentText("This project already exists!");
        alert.showAndWait();
        OK = false;
      }
      else{
        if(!goingBack){
          projectList.addProject(newProject);
          projectsAdapter.saveProjects(projectList);
        }
        else{
          Project previousProject = projectList.getProjectByName(previousProjectName);
          previousProject.setName(inputName.getText());
          previousProject.setDescription(inputDescription.getText());
          projectsAdapter.saveProjects(projectList);
        }

      }

    }
    return OK;
  }


  /**
   *Gets the continue Button
   * @return Button of the continueButton
   */
  public Button getButtonContinue()
  {
    return buttonContinue;
  }

  /**
   * Clears all the fields, sets the textFields to empty
   */
  public void clearFields(){
    inputName.setText("");
    inputDescription.setText("");
  }

  /**
   * Sets variable goingBack to true and previousProjectName to be able to indicate
   * whether the going back button from CreateProjectGUI2 has been pressed
   */
  public void goingBack(String previousProjectName){
    goingBack = true;
    this.previousProjectName = previousProjectName;
  }

  /**
   * Gets the entered name of project
   * @return String type of inputed name
   */
  public String getNameOfProject(){
    return inputName.getText();
  }
}
