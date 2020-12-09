import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class SepGUI extends Application
{

  private CreateProjectGUI1 createProjectGUI1;

  private VBox mainPane;
  private StackPane stackPane;
  private VBox vBox;

  private Label companyName;
  private MenuBar menuBar;

  private Image logo;
  private ImageView logoView;

  private Menu projectsMenu;
  private Menu employeesMenu;
  private Menu assignedTasksMenu;
  private Menu exitMenu;

  private MenuItem createProject;
  private MenuItem projectsOverview;
  private MenuItem addEmployeeMenuItem;
  private MenuItem employeesStatisticsMenuItem;
  private MenuItem editRemoveMenuItem;
  private MenuItem assignMenuItem;
  private MenuItem reportMenuItem;
  private MenuItem viewTasksMenuItem;
  private MenuItem exitMenuItem;

  private MyActionListener listener;

  public void start(Stage window)
  {
    window.setTitle("Student File Adapter GUI 4");
    listener = new MyActionListener();
    createProjectGUI1 = new CreateProjectGUI1();

    addEmployeeMenuItem = new MenuItem("Add a new employee");
    employeesStatisticsMenuItem = new MenuItem("Employees Statistics");
    editRemoveMenuItem = new MenuItem("Edit/Remove Employee");
    assignMenuItem = new MenuItem("Assign Tasks");
    reportMenuItem = new MenuItem("Report Tasks");
    viewTasksMenuItem = new MenuItem("View Tasks");
    createProject = new MenuItem("Create Project");
    createProject.setOnAction(listener);
    projectsOverview = new MenuItem("Projects Overview");
    exitMenuItem = new MenuItem("Exit");

    projectsMenu = new Menu("Projects");
    employeesMenu = new Menu("Employees");
    assignedTasksMenu = new Menu("AssignedTasks");
    exitMenu = new Menu("Exit");

    projectsMenu.getItems().addAll(createProject, projectsOverview, exitMenuItem);
    employeesMenu.getItems()
        .addAll(employeesStatisticsMenuItem, addEmployeeMenuItem,
            editRemoveMenuItem);
    assignedTasksMenu.getItems()
        .addAll(assignMenuItem, reportMenuItem, viewTasksMenuItem);

    menuBar = new MenuBar();
    menuBar.getMenus()
        .addAll(projectsMenu, employeesMenu, assignedTasksMenu, exitMenu);

    Font fontSize = new Font("Courier New", 60);
    companyName = new Label("ColourIT");
    companyName.setFont(fontSize);

    logo = new Image("vialogoah.gif");
    logoView = new ImageView(logo);

    vBox = new VBox();
    vBox.getChildren().addAll(companyName, logoView);
    vBox.setAlignment(Pos.BOTTOM_CENTER);

    stackPane = new StackPane();
    stackPane.getChildren().add(vBox);

    mainPane = new VBox();
    mainPane.getChildren().addAll(menuBar, stackPane);
    Scene scene = new Scene(mainPane, 1000, 500);

    window.setScene(scene);
    window.setResizable(false);
    window.show();
  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {

    public void handle(ActionEvent e)
    {
      if (e.getSource() == createProject)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }
      else if (e.getSource() == exitMenuItem)
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you really want to exit the program?",
            ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit");
        alert.setHeaderText(null);

        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
          System.exit(0);
        }
      }
    }
  }
}
