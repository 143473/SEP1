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
  private ProjectOverviewGUI projectOverviewGUI;

  private EmployeeStatisticsGUI employeeStatisticsGUI;
  private AddANewEmployeeGUI addANewEmployeeGUI;
  private EditRemoveEmployeeGUI editRemoveEmployeeGUI;

  private AssignTasksGUI1 assignTasksGUI1;
  private ReportTasksGUI1 reportTasksGUI1;
  private ViewAssignedTasksGUI1 viewAssignedTasksGUI1;

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
  private Menu fileMenu;

  private MenuItem exitMenuItem;
  private MenuItem homeMenuItem;
  private MenuItem createProject;
  private MenuItem projectsOverview;
  private MenuItem addEmployeeMenuItem;
  private MenuItem employeesStatisticsMenuItem;
  private MenuItem editRemoveMenuItem;
  private MenuItem assignMenuItem;
  private MenuItem reportMenuItem;
  private MenuItem viewTasksMenuItem;


  private MyActionListener listener;

  public void start(Stage window)
  {
    window.setTitle("Student File Adapter GUI 4");
    listener = new MyActionListener();
    createProjectGUI1 = new CreateProjectGUI1();
    projectOverviewGUI = new ProjectOverviewGUI();

    employeeStatisticsGUI = new EmployeeStatisticsGUI();
    addANewEmployeeGUI = new AddANewEmployeeGUI();
    editRemoveEmployeeGUI = new EditRemoveEmployeeGUI();

    assignTasksGUI1 = new AssignTasksGUI1();
    reportTasksGUI1 = new ReportTasksGUI1();
    viewAssignedTasksGUI1 = new ViewAssignedTasksGUI1();

    addEmployeeMenuItem = new MenuItem("Add a new employee");
    addEmployeeMenuItem.setOnAction(listener);
    employeesStatisticsMenuItem = new MenuItem("Employees Statistics");
    employeesStatisticsMenuItem.setOnAction(listener);
    editRemoveMenuItem = new MenuItem("Edit/Remove Employee");
    editRemoveMenuItem.setOnAction(listener);
    assignMenuItem = new MenuItem("Assign Tasks");
    assignMenuItem.setOnAction(listener);
    reportMenuItem = new MenuItem("Report Tasks");
    reportMenuItem.setOnAction(listener);
    viewTasksMenuItem = new MenuItem("View Tasks");
    viewTasksMenuItem.setOnAction(listener);
    createProject = new MenuItem("Create Project");
    createProject.setOnAction(listener);
    projectsOverview = new MenuItem("Projects Overview");
    projectsOverview.setOnAction(listener);
    homeMenuItem = new MenuItem("Home");
    homeMenuItem.setOnAction(listener);
    exitMenuItem = new MenuItem("Exit");
    exitMenuItem.setOnAction(listener);

    fileMenu = new Menu("File");
    projectsMenu = new Menu("Projects");
    employeesMenu = new Menu("Employees");
    assignedTasksMenu = new Menu("AssignedTasks");

    fileMenu.getItems().addAll(homeMenuItem,exitMenuItem);
    projectsMenu.getItems().addAll(createProject, projectsOverview);
    employeesMenu.getItems()
        .addAll(employeesStatisticsMenuItem, addEmployeeMenuItem,
            editRemoveMenuItem);
    assignedTasksMenu.getItems()
        .addAll(assignMenuItem, reportMenuItem, viewTasksMenuItem);


    menuBar = new MenuBar();
    menuBar.getMenus()
        .addAll(fileMenu, projectsMenu, employeesMenu, assignedTasksMenu);

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
      if (e.getSource() == homeMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(vBox);
      }
      //PROJECTS
      else if (e.getSource() == createProject)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }
      else if (e.getSource() == projectsOverview)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(projectOverviewGUI.getMainPane());
      }

      //EMPLOYEES
      else if(e.getSource() == employeesStatisticsMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(employeeStatisticsGUI.getMainPane());
      }
      else if(e.getSource() == addEmployeeMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(addANewEmployeeGUI.getMainPane());
      }
      else if(e.getSource() == editRemoveMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(editRemoveEmployeeGUI.getMainPane());
      }


      //ASSIGNED TASKS
      else if(e.getSource() == assignMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI1.getMainPane());
      }
      else if(e.getSource() == reportMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reportTasksGUI1.getMainPane());
      }
      else if(e.getSource() == viewTasksMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(viewAssignedTasksGUI1.getMainPane());
      }

      //EXIT
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
