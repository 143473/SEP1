import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class SepGUI extends Application
{
  private Stage parentStage;
  private Stage newWindow;
  private Scene scene;

  private CreateProjectGUI1 createProjectGUI1;
  private CreateProjectGUI2 createProjectGUI2;
  private CreateProjectGUI3 createProjectGUI3;
  private ProjectOverviewGUI projectOverviewGUI;
  private ManageProjectGUI manageProjectGUI;
  private ChangeTeamMembersGUI changeTeamMembersGUI;
  private ReqOfSelectedPrjGUI reqOfSelectedPrjGUI;
  private TasksOfReqOfPrjGUI tasksOfReqOfPrjGUI;
  private ManageRequirementGUI manageRequirementGUI;
  private ManageTaskGUI manageTaskGUI;

  private ProjectsAdapter projectsAdapter;

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

  public void start(Stage parentStage)
  {
    this.parentStage = parentStage;
    parentStage.setTitle("Student File Adapter GUI 4");
    listener = new MyActionListener();
    EmployeeAdapter employeeAdapter = new EmployeeAdapter("employees.bin");
    ProjectsAdapter projectsAdapter = new ProjectsAdapter("y");

    createProjectGUI1 = new CreateProjectGUI1(projectsAdapter);
    createProjectGUI2 = new CreateProjectGUI2(employeeAdapter);
    createProjectGUI3 = new CreateProjectGUI3();
    projectOverviewGUI = new ProjectOverviewGUI(projectsAdapter);
    manageProjectGUI = new ManageProjectGUI();
    changeTeamMembersGUI = new ChangeTeamMembersGUI();
    manageRequirementGUI = new ManageRequirementGUI();
    manageTaskGUI = new ManageTaskGUI();
    reqOfSelectedPrjGUI = new ReqOfSelectedPrjGUI();
    tasksOfReqOfPrjGUI = new TasksOfReqOfPrjGUI();

    employeeStatisticsGUI = new EmployeeStatisticsGUI(employeeAdapter);
    addANewEmployeeGUI = new AddANewEmployeeGUI(employeeAdapter);
    editRemoveEmployeeGUI = new EditRemoveEmployeeGUI(employeeAdapter);

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

    fileMenu.getItems().addAll(homeMenuItem, exitMenuItem);
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
    scene = new Scene(mainPane, 1000, 500);

    parentStage.setScene(scene);
    parentStage.setResizable(false);
    parentStage.show();

    // Pop -up window with full list of employees
    // New window (Stage)
    newWindow = new Stage();
    newWindow.setTitle("Choose Employee");
    scene = new Scene(createProjectGUI2.getNewWindowPane());
    newWindow.setScene(scene);

    // Specifies the modality for new window.
    newWindow.initModality(Modality.WINDOW_MODAL);

    // Specifies the owner Window (parent) for new window
    newWindow.initOwner(parentStage);

    // Set position of second window, related to primary window.
    newWindow.setX(parentStage.getX() + 200);
    newWindow.setY(parentStage.getY() + 100);


  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      createProjectGUI1.getButtonContinue().setOnAction(listener);
      createProjectGUI2.getContinueButton().setOnAction(listener);
      createProjectGUI2.getGoBackButton().setOnAction(listener);
      createProjectGUI2.getAddTeamMember().setOnAction(listener);
      createProjectGUI2.getAdd().setOnAction(listener);
      createProjectGUI3.getFinishButton().setOnAction(listener);
      createProjectGUI3.getGoBackButton().setOnAction(listener);
      projectOverviewGUI.getAdd().setOnAction(listener);
      projectOverviewGUI.getManage().setOnAction(listener);
      projectOverviewGUI.getContinueButton().setOnAction(listener);
      reqOfSelectedPrjGUI.getAdd().setOnAction(listener);
      reqOfSelectedPrjGUI.getContinueButton().setOnAction(listener);
      reqOfSelectedPrjGUI.getManage().setOnAction(listener);
      reqOfSelectedPrjGUI.getGoBackButton().setOnAction(listener);
      tasksOfReqOfPrjGUI.getGoBackButton().setOnAction(listener);
      tasksOfReqOfPrjGUI.getAdd().setOnAction(listener);
      tasksOfReqOfPrjGUI.getManage().setOnAction(listener);
      manageProjectGUI.getManageTeamMembers().setOnAction(listener);
      manageProjectGUI.getCancel().setOnAction(listener);
      changeTeamMembersGUI.getCancel().setOnAction(listener);
      changeTeamMembersGUI.getAddButton().setOnAction(listener);




      if (e.getSource() == homeMenuItem)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(vBox);
      }

      //PROJECTS
      //Create Project
      else if (e.getSource() == createProject)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }
      else if (e.getSource() == createProjectGUI1.getButtonContinue())
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(createProjectGUI2.getMainPane());
        }
      else if (e.getSource() == createProjectGUI2.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI3.getMainPane());
      }
      else if (e.getSource() == createProjectGUI2.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }
      //Pop-up Change Team Members
      else if (e.getSource() == createProjectGUI2.getAddTeamMember())
      {
        newWindow.show();
      }
      else if (e.getSource() == createProjectGUI2.getAdd())
      {
        newWindow.close();
      }
      //Continue Create Project
      else if (e.getSource() == createProjectGUI3.getFinishButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(projectOverviewGUI.getMainPane());
      }
      else if (e.getSource() == createProjectGUI3.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI2.getMainPane());
      }

      //Projects Overview
        else if (e.getSource() == projectsOverview)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(projectOverviewGUI.getMainPane());
        }
        else if (e.getSource() == projectOverviewGUI.getAdd())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }

        //Manage Projects
        else if (e.getSource() == projectOverviewGUI.getManage())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageProjectGUI.getMainPane());
      }
        else if(e.getSource() == manageProjectGUI.getCancel())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(projectOverviewGUI.getMainPane());
      }

        //Change Team Members - editing existing list of team-members
        else if(e.getSource() == manageProjectGUI.getManageTeamMembers())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(changeTeamMembersGUI.getMainPane());
      }
        else if(e.getSource() == changeTeamMembersGUI.getCancel())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageProjectGUI.getMainPane());
      }
        //Pop-up Change Team Members
      else if (e.getSource() == changeTeamMembersGUI.getAddButton())
      {
        newWindow.show();
      }
      else if (e.getSource() == createProjectGUI2.getAdd())
      {
        newWindow.close();
      }
      //List of requirements for the selected project
      else if(e.getSource() == projectOverviewGUI.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reqOfSelectedPrjGUI.getMainPane());
      }

      //List of tasks for the selected requirement
      else if (e.getSource() == reqOfSelectedPrjGUI.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(tasksOfReqOfPrjGUI.getMainPane());
      }
      else if (e.getSource() == tasksOfReqOfPrjGUI.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reqOfSelectedPrjGUI.getMainPane());
      }
      else if(e.getSource() == reqOfSelectedPrjGUI.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(projectOverviewGUI.getMainPane());
      }

      //add requirement
      else if(e.getSource() == reqOfSelectedPrjGUI.getAdd())
      {
        
      }
      //manage requirements
      else if(e.getSource() == reqOfSelectedPrjGUI.getManage())
      {

      }

        //Manage Tasks


        //EMPLOYEES
        else if (e.getSource() == employeesStatisticsMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(employeeStatisticsGUI.getMainPane());
        }
        else if (e.getSource() == addEmployeeMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(addANewEmployeeGUI.getMainPane());
        }
        else if (e.getSource() == editRemoveMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(editRemoveEmployeeGUI.getMainPane());
        }

        //ASSIGNED TASKS
        else if (e.getSource() == assignMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(assignTasksGUI1.getMainPane());
        }
        else if (e.getSource() == reportMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(reportTasksGUI1.getMainPane());
        }
        else if (e.getSource() == viewTasksMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(viewAssignedTasksGUI1.getMainPane());
        }

        //EXIT
        else if (e.getSource() == exitMenuItem)
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
              "Do you really want to exit the program?", ButtonType.YES,
              ButtonType.NO);
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


