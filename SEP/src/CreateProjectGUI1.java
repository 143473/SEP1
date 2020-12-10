import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI1
{
  private ProjectsAdapter projectsAdapter;
  private GridPane gridPane;
  private VBox mainPane;

  private Label title;
  private Label projectName;
  private Label projectDesc;

  private TextField inputName;
  private TextField inputDescription;

  private Button buttonContinue;

  private MyActionListener listener;

  public CreateProjectGUI1(ProjectsAdapter projectsAdapter) {
    this.projectsAdapter = projectsAdapter;

    listener = new MyActionListener();

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
    buttonContinue.setOnAction(listener);

    mainPane = new VBox();
    mainPane.getChildren().addAll(title, gridPane, buttonContinue);
    mainPane.setPadding(new Insets(25, 25, 25, 25));

  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      boolean canCreateAButton = false;
      if(e.getSource() == buttonContinue){
        boolean allValuesCorrect = true;
        if(inputName.getText() == null || inputName.getText().trim().isEmpty()){
          JOptionPane.showMessageDialog(null, "Project name cannot be empty!",
                  "Invalid input", JOptionPane.ERROR_MESSAGE);
          allValuesCorrect = false;
        }
        else if(inputDescription.getText() == null || inputDescription.getText().trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Project description cannot be empty!",
                  "Invalid input", JOptionPane.ERROR_MESSAGE);
          allValuesCorrect = false;
        }
        ProjectList projectList = projectsAdapter.getAllProjects();
        Project newProject = new Project(inputName.getText(), inputDescription.getText());
        if(projectList.containsProject(newProject)){
          JOptionPane.showMessageDialog(null, "This project already exists!",
                  "Duplicate employee", JOptionPane.ERROR_MESSAGE);
          allValuesCorrect = false;
        }
        if(allValuesCorrect == true){
          canCreateAButton = true;

        }
      }
    }
  }
  public Button getButtonContinue()
  {
    return buttonContinue;
  }

}
