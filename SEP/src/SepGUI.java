import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *  A class handling the main application
 *  * @author Claudiu Corduianu
 *  * @version 1.0
 */

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
  private AddRequirementGUI addRequirementGUI;
  private AddTaskGUI addTaskGUI;

  private AssignTasksGUI1 assignTasksGUI1;
  private AssignTasksGUI2 assignTasksGUI2;
  private AssignTasksGUI3 assignTasksGUI3;
  private AssignTasksGUI4 assignTasksGUI4;
  private AssignTasksGUI5 assignTasksGUI5;
  private ReportTasksGUI1 reportTasksGUI1;
  private ReportTasksGUI2 reportTasksGUI2;
  private ReportTasksGUI3 reportTasksGUI3;
  private ViewAssignedTasksGUI2 viewAssignedTasksGUI2;
  private ViewAssignedTasksGUI1 viewAssignedTasksGUI1;

  private ProjectsAdapter projectsAdapter;

  private EmployeeStatisticsGUI employeeStatisticsGUI;
  private AddANewEmployeeGUI addANewEmployeeGUI;
  private EditRemoveEmployeeGUI editRemoveEmployeeGUI;

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
    ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");

    createProjectGUI1 = new CreateProjectGUI1(projectsAdapter, this);
    createProjectGUI2 = new CreateProjectGUI2(employeeAdapter, projectsAdapter,this);
    createProjectGUI3 = new CreateProjectGUI3(employeeAdapter, projectsAdapter,this);
    projectOverviewGUI = new ProjectOverviewGUI(projectsAdapter);
    manageProjectGUI = new ManageProjectGUI(projectsAdapter);
    changeTeamMembersGUI = new ChangeTeamMembersGUI();
    manageRequirementGUI = new ManageRequirementGUI();
    manageTaskGUI = new ManageTaskGUI();
    reqOfSelectedPrjGUI = new ReqOfSelectedPrjGUI();
    tasksOfReqOfPrjGUI = new TasksOfReqOfPrjGUI();
    manageRequirementGUI = new ManageRequirementGUI();
    manageTaskGUI = new ManageTaskGUI();
    addRequirementGUI = new AddRequirementGUI();
    addTaskGUI = new AddTaskGUI();


    employeeStatisticsGUI = new EmployeeStatisticsGUI(employeeAdapter);
    addANewEmployeeGUI = new AddANewEmployeeGUI(employeeAdapter);
    editRemoveEmployeeGUI = new EditRemoveEmployeeGUI(employeeAdapter);

    assignTasksGUI1 = new AssignTasksGUI1();
    assignTasksGUI2 = new AssignTasksGUI2();
    assignTasksGUI3 = new AssignTasksGUI3();
    assignTasksGUI4 = new AssignTasksGUI4();
    assignTasksGUI5 = new AssignTasksGUI5();
    reportTasksGUI1 = new ReportTasksGUI1();
    reportTasksGUI2 = new ReportTasksGUI2();
    reportTasksGUI3 = new ReportTasksGUI3();
    viewAssignedTasksGUI1 = new ViewAssignedTasksGUI1();
    viewAssignedTasksGUI2 = new ViewAssignedTasksGUI2();

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
    manageRequirementGUI.getCancel().setOnAction(listener);
    addRequirementGUI.getCancel().setOnAction(listener);
    manageTaskGUI.getCancel().setOnAction(listener);
    addTaskGUI.getCancel().setOnAction(listener);
    changeTeamMembersGUI.getCancel().setOnAction(listener);
    changeTeamMembersGUI.getAddButton().setOnAction(listener);

    assignTasksGUI1.getContinueButton().setOnAction(listener);
    assignTasksGUI2.getContinueButton().setOnAction(listener);
    assignTasksGUI2.getGoBackButton().setOnAction(listener);
    assignTasksGUI3.getButtonContinue().setOnAction(listener);
    assignTasksGUI3.getGoBackButton().setOnAction(listener);
    assignTasksGUI4.getButtonContinue().setOnAction(listener);
    assignTasksGUI4.getGoBackButton().setOnAction(listener);
    assignTasksGUI5.getGoBackButton().setOnAction(listener);

    reportTasksGUI1.getContinueButton().setOnAction(listener);
    reportTasksGUI2.getContinueButton().setOnAction(listener);
    reportTasksGUI3.getGoBackButton().setOnAction(listener);
    reportTasksGUI3.getReportButton().setOnAction(listener);

    viewAssignedTasksGUI1.getContinueButton().setOnAction(listener);
    viewAssignedTasksGUI2.getGoBack().setOnAction(listener);


  }
  public CreateProjectGUI2 getCreateProjectGUI2(){
    return createProjectGUI2;
  }

  public CreateProjectGUI3 getCreateProjectGUI3(){
    return createProjectGUI3;
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
      //Create Project
      else if (e.getSource() == createProject)
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(createProjectGUI1.getMainPane());
      }

      else if (e.getSource() == createProjectGUI1.getButtonContinue())
        {

          if(createProjectGUI1.callContinueButton()){
            createProjectGUI2.setProjectList();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(createProjectGUI2.getMainPane());
          }

        }
      else if (e.getSource() == createProjectGUI2.getContinueButton())
      {
        if(createProjectGUI2.callContinueButton()){
          createProjectGUI3.setProjectList();
          stackPane.getChildren().clear();
          stackPane.getChildren().add(createProjectGUI3.getMainPane());
        }

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
        if(createProjectGUI2.callAdd()){
          newWindow.close();
        }

      }
      //Continue Create Project
      else if (e.getSource() == createProjectGUI3.getFinishButton())
      {
        if(createProjectGUI3.callFinishButton()){
          stackPane.getChildren().clear();
          stackPane.getChildren().add(projectOverviewGUI.getMainPane());
        }

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
      else if(e.getSource() == manageRequirementGUI.getCancel())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reqOfSelectedPrjGUI.getMainPane());
      }
      else if(e.getSource() == projectOverviewGUI.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reqOfSelectedPrjGUI.getMainPane());
      }
      //Manage Requirements
      else if(e.getSource() == reqOfSelectedPrjGUI.getManage())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageRequirementGUI.getMainPane());
      }

      //List of tasks for the selected requirement
      else if (e.getSource() == reqOfSelectedPrjGUI.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(tasksOfReqOfPrjGUI.getMainPane());
      }
      //Manage Tasks
      else if(e.getSource() == tasksOfReqOfPrjGUI.getManage())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(manageTaskGUI.getMainPane());
      }
      else if (e.getSource() == manageTaskGUI.getCancel())
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
        stackPane.getChildren().clear();
        stackPane.getChildren().add(addRequirementGUI.getMainPane());
      }
      else if(e.getSource() == addRequirementGUI.getCancel())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reqOfSelectedPrjGUI.getMainPane());
      }
      //add task
      else if(e.getSource() == tasksOfReqOfPrjGUI.getAdd())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(addTaskGUI.getMainPane());
      }
      else if(e.getSource() == addTaskGUI.getCancel())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(tasksOfReqOfPrjGUI.getMainPane());
      }
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
        //Assign tasks
        else if (e.getSource() == assignMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(assignTasksGUI1.getMainPane());
        }
        else if (e.getSource() == assignTasksGUI1.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI2.getMainPane());
      }
        else if (e.getSource() == assignTasksGUI2.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI3.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI2.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI1.getMainPane());
      }

        else if (e.getSource() == assignTasksGUI3.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI2.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI3.getButtonContinue())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI4.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI4.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI3.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI3.getButtonContinue())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI4.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI4.getButtonContinue())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI5.getMainPane());
      }
      else if (e.getSource() == assignTasksGUI5.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(assignTasksGUI4.getMainPane());
      }


        //Report Task
        else if (e.getSource() == reportMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(reportTasksGUI1.getMainPane());
        }
      else if (e.getSource() == reportTasksGUI1.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reportTasksGUI2.getMainPane());
      }
      else if (e.getSource() == reportTasksGUI2.getContinueButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reportTasksGUI3.getMainPane());
      }
      else if(e.getSource() == reportTasksGUI3.getReportButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reportTasksGUI2.getMainPane());
      }
      else if (e.getSource() == reportTasksGUI3.getGoBackButton())
      {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(reportTasksGUI2.getMainPane());
      }


        //View Task
        else if (e.getSource() == viewTasksMenuItem)
        {
          stackPane.getChildren().clear();
          stackPane.getChildren().add(viewAssignedTasksGUI1.getMainPane());
        }
        else if (e.getSource() == viewAssignedTasksGUI1.getContinueButton())
        {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(viewAssignedTasksGUI2.getMainPane());
      }
      else if (e.getSource() == viewAssignedTasksGUI2.getGoBack())
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


