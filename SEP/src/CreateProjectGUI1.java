import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.*;

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
   * @param sepGUI
   */

  public CreateProjectGUI1(ProjectsAdapter projectsAdapter, SepGUI sepGUI) {
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;


    title = new Label("Create a New Project");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    projectName = new Label("Project name:");
    inputName = new TextField();
    projectDesc = new Label("Project Description");
    inputDescription = new TextField();

    gridPane = new GridPane();
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.addRow(0, projectName, inputName);
    gridPane.addRow(1, projectDesc, inputDescription);

    buttonContinue = new Button("Continue");

    mainPane = new VBox();
    mainPane.getChildren().addAll(title, gridPane, buttonContinue);
    mainPane.setPadding(new Insets(25, 25, 25, 25));

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
    if(inputName.getText().equals("") || inputName.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Project name cannot be empty!");
      alert.showAndWait();
      return false;
    }
    else if(inputDescription.getText().equals("") || inputDescription.getText().trim().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Invalid input");
      alert.setContentText("Project description cannot be empty!");
      alert.showAndWait();
      return false;
    }
    else {
      ProjectList projectList = projectsAdapter.getAllProjects();
      Project newProject = new Project(inputName.getText(), inputDescription.getText());
      if(!projectList.containsProject(newProject)){
        projectList.addProject(newProject);
        sepGUI.getCreateProjectGUI2().setProjectList(projectList);
      }
      else{
        System.out.println("not adding it");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Duplicate project");
        alert.setContentText("This project already exists!");
        alert.showAndWait();
        return false;
      }
    }
    return true;
  }

  /**
   *Gets the continue button
   * @return Button of the continueButton
   */
  public Button getButtonContinue()
  {
    return buttonContinue;
  }
}
