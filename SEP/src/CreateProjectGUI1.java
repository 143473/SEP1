import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;

/**
 * The 1st part of the project creation user interface, that allows for
 * setting up the project name and project description.
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI1
{
  private GridPane gridPane;
  private VBox mainPane;

  private Label title;
  private Label projectName;
  private Label projectDesc;

  private TextField inputName;
  private TextField inputDescription;

  private Button buttonContinue;

  public CreateProjectGUI1()
  {
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
  }

  public VBox getMainPane()
  {
    return mainPane;
  }
}
