
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *  A class handling changing the team members
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
public class ChangeTeamMembersGUI {

  private VBox mainPane;
  private HBox buttonsPane;

  private Label title;
  private Button addButton;
  private Button removeButton;
  private Button cancelButton;

  private ListView<Employee> employeeListView;
  private ListView<AssignedEmployee> teamMembersTable;

  private ProjectsAdapter projectsAdapter;
  private EmployeeAdapter employeeAdapter;
  private SepGUI sepGUI;

  private Project currentProject;

  /**
   *  2-argument constructor initializing all the parts of the GUI
   *
   */
  public ChangeTeamMembersGUI(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI)
  {
    this.projectsAdapter = projectsAdapter;
    this.employeeAdapter = employeeAdapter;
    this.sepGUI = sepGUI;

    title = new Label();
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    removeButton = new Button("Remove");
    cancelButton = new Button("Cancel");
    addButton = new Button("Add Team Member");

    employeeListView = new ListView<Employee>();
    employeeListView.setPrefHeight(120);

    teamMembersTable = new ListView<AssignedEmployee>();

    buttonsPane = new HBox();
    buttonsPane.setSpacing(50);
    buttonsPane.getChildren().addAll(addButton, removeButton,cancelButton);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,teamMembersTable,buttonsPane);


  }

  /**
   * Gets the main pane of the class
   * @return VBox Main pane
   */
  public VBox getMainPane()
  {
    return mainPane;
  }

  /**
   * Gets the button Cancel of type Button
   * @return Button cancelButton
   */
  public Button getCancel()
  {
    return cancelButton;
  }

  /**
   * Gets the button Add of type Button
   * @return Button addButtons
   */
  public Button getAddButton()
  {
    return addButton;
  }

  public void initializeCurrentProject(){
    currentProject = sepGUI.getManageProjectGUI().getSelectedProject();

    title.setText("Team Members of "+currentProject.getName());
    initializeTeamMembersTable();
  }

  public void initializeTeamMembersTable(){
    teamMembersTable.getItems().clear();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      teamMembersTable.getItems().add(chosenAssignedEmployees.get(i));
      System.out.println(chosenAssignedEmployees.get(i));
    }
  }

  public void initializeListView()
  {
    employeeListView.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    EmployeeList chosenEmployees = new EmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      chosenEmployees.addEmployee(new Employee(chosenAssignedEmployees.get(i).getFirstName(), chosenAssignedEmployees.get(i).getLastName(),
              chosenAssignedEmployees.get(i).getDateOfBirth()));
    }
    for (int i = 0; i < employees.size(); i++) {
      if(chosenEmployees.containsEmployee(employees.get(i))){
        employees.removeEmployee(employees.get(i));
      }
    }
    for (int i = 0; i < employees.size(); i++)
    {
      employeeListView.getItems().add(employees.get(i));
    }
  }

  public boolean callAdd()
  {
    boolean OK = true;
    Employee employeeAdded = employeeListView.getSelectionModel().getSelectedItem();
    if (employeeAdded == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No employee was chosen!");
      alert.showAndWait();
      OK = false;
    }
    if (OK)
    {
      currentProject.addTeamMember(new AssignedEmployee(employeeAdded.getFirstName(), employeeAdded.getLastName(), employeeAdded.getDateOfBirth()));

      initializeListView();
    }

    return OK;
  }
}