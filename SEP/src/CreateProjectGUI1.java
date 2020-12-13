import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * The 1st part of the project creation user interface, that allows for
 * setting up the project name and project description.
 * @author Claudiu Cordunianu, Timothy Engkar, Marketa Lapcikova
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


  /**
   * 2-argument constructor setting the file name.
   * @param projectsAdapter
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
   * @return Returns the main pane
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
      for (int i = 0; i < projectList.size(); i++) {
        if(projectList.get(i).getName().equals(newProject.getName())){
          contains = true;
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
        projectList.addProject(newProject);
        projectsAdapter.saveProjects(projectList);
      }

    }
    return OK;
  }


  /**
   *Gets the continue button
   * @return Button of the continueButton
   */
  public Button getButtonContinue()
  {
    return buttonContinue;
  }

  public void clearFields(){
    inputName.setText("");
    inputDescription.setText("");
  }
}
