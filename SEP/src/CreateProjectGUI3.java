import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The 3rd part of the project creation user interface, that allows for
 * setting the status for the employees
 * @author Claudiu Emanuel Cordunianu, Marketa Lapcikova
 * @version 1.0
 */
public class CreateProjectGUI3
{
  private EmployeeAdapter employeeAdapter;
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private ProjectList projectList;
  private GridPane gridPane;
  private HBox hBoxPane;
  private VBox mainPane;

  private Label title;
  private Label scrumMaster;
  private Label productOwner;
  private Label projectCreator;

  private ComboBox employeesBox1;
  private ComboBox employeesBox2;
  private ComboBox employeesBox3;

  private Button finishButton;
  private Button goBackButton;

  /**
   * 3-argument constructor initializing the GUI components
   * @param projectsAdapter adapter of the projects, requirements and tasks
   * @param employeeAdapter adapter of the employees
   * @param sepGUI the main GUI where all the other GUIs are connected
   */
  public CreateProjectGUI3(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI){
    projectList = projectsAdapter.getAllProjects();

    this.employeeAdapter = employeeAdapter;
    this.projectsAdapter = projectsAdapter;
    sepGUI = new SepGUI();
    title = new Label("Set team members roles");
    title.getStyleClass().add("heading");

    scrumMaster = new Label("Scrum Master: ");
    projectCreator = new Label("Project Creator: ");
    productOwner = new Label("Product Owner");
    employeesBox1 = new ComboBox();
    employeesBox2 = new ComboBox();
    employeesBox3 = new ComboBox();

    gridPane = new GridPane();
    gridPane.setVgap(8);
    gridPane.setHgap(8);
    gridPane.addRow(0, scrumMaster, employeesBox1);
    gridPane.addRow(1, projectCreator, employeesBox2);
    gridPane.addRow(2, productOwner, employeesBox3);

    finishButton = new Button("Finish");
    goBackButton = new Button("Go Back");

    hBoxPane = new HBox(8);
    hBoxPane.getChildren().addAll(finishButton, goBackButton);

    mainPane = new VBox(8);
    mainPane.getChildren().addAll(title, gridPane, hBoxPane);
  }

  /**
   * Gets the mainPane of the GUI
   * @return VBox mainPane
   */
  public VBox getMainPane()
  {
    return mainPane;
  }

  /**
   * Gets the employeeBox2
   * @return ComboBox employeesBox2
   */
  public ComboBox getEmployeesBox2()
  {
    return employeesBox2;
  }

  /**
   * Gets the employeeBox1
   * @return ComboBox employeesBox1
   */
  public ComboBox getEmployeesBox1()
  {
    return employeesBox1;
  }

  /**
   * Gets the employeeBox3
   * @return ComboBox employeesBox3
   */
  public ComboBox getEmployeesBox3()
  {
    return employeesBox3;
  }

  /**
   * Gets the finishButton Button
   * @return Button finishButton
   */
  public Button getFinishButton()
  {
    return finishButton;
  }

  /**
   * Gets the goBackButton Button
   * @return Button goBackButton
   */
  public Button getGoBackButton()
  {
    return goBackButton;
  }

  /**
   * Sets the projectList to all projects
   */
  public void setProjectList()
  {
    this.projectList = projectsAdapter.getAllProjects();
    if (projectList.size() > 0) {
      Project project = projectList.get(projectList.size()-1);
    }
  }

  /**
   * Checks the validity of entered data after the finish Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callFinishButton(){
    boolean OK = true;

    if(employeesBox1.getSelectionModel().getSelectedItem().equals(employeesBox2.getSelectionModel().getSelectedItem()) ||
        employeesBox1.getSelectionModel().getSelectedItem().equals(employeesBox3.getSelectionModel().getSelectedItem()) ||
        employeesBox2.getSelectionModel().getSelectedItem().equals(employeesBox3.getSelectionModel().getSelectedItem())){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Duplicate team member");
      alert.setContentText("One team member cannot be assigned multiple statuses!");
      alert.showAndWait();
      OK = false;
    }
    if(OK){
      Project project = projectList.get(projectList.size()-1);
      AssignedEmployeeList assignedEmployees = projectList.get(projectList.size()-1).getAssignedEmployeeList();
      AssignedEmployee employee1 = assignedEmployees.get(employeesBox1.getSelectionModel().getSelectedIndex());
      AssignedEmployee employee2 = assignedEmployees.get(employeesBox2.getSelectionModel().getSelectedIndex());
      AssignedEmployee employee3 = assignedEmployees.get(employeesBox3.getSelectionModel().getSelectedIndex());

      project.setScrumMaster(employee1);
      project.setProjectCreator(employee2);
      project.setProductOwner(employee3);

      employee1.setStatus(0);
      employee2.setStatus(2);
      employee3.setStatus(1);


      projectsAdapter.saveProjects(projectList);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("Adding successful");
      alert.setContentText("New project was successfully added to the list!");
      alert.showAndWait();
    }
    return OK;

  }
}
