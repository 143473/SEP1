import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * The 1st part of the project creation user interface, that allows for
 * setting up the project name and project description.
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI1 extends Application
{
  private Scene scene;
  private GridPane gridPane;
  private VBox vBoxPane;

  private Label title;
  private Label projectName;
  private Label projectDesc;

  private TextField inputName;
  private TextField inputDescription;

  private Button buttonContinue;

  public void start(Stage window)
  {
    window.setTitle("Sasd");

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

    vBoxPane = new VBox();
    vBoxPane.getChildren().addAll(title, gridPane, buttonContinue);

    scene = new Scene(vBoxPane, 670, 204);
    window.setScene(scene);
    window.show();
  }



}
