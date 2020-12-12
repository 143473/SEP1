import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI3
{
  private EmployeeAdapter employeeAdapter;
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private MyActionListener listener;

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

  public CreateProjectGUI3(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI){
    projectList = projectsAdapter.getAllProjects();

    listener = new MyActionListener();

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

  public VBox getMainPane()
  {
    return mainPane;
  }

  public ComboBox getEmployeesBox2()
  {
    return employeesBox2;
  }

  public ComboBox getEmployeesBox1()
  {
    return employeesBox1;
  }

  public ComboBox getEmployeesBox3()
  {
    return employeesBox3;
  }

  public Button getFinishButton()
  {
    return finishButton;
  }

  public Button getGoBackButton()
  {
    return goBackButton;
  }

  public void setProjectList()
  {
    this.projectList = projectsAdapter.getAllProjects();
    if (projectList.size() > 0) {
      Project project = projectList.get(projectList.size()-1);


      /*ArrayList<AssignedEmployee> employeeList = project.getAssignedEmployees();


      for (int i = 0; i < employeeList.size(); i++) {
        employeesBox1.getItems().add(employeeList.get(i));
      }
      employeesBox2 = new ComboBox();
      for (int i = 0; i < employeeList.size(); i++) {
        employeesBox2.getItems().add(employeeList.get(i));
      }
      employeesBox3 = new ComboBox();
      for (int i = 0; i < employeeList.size(); i++) {
        employeesBox3.getItems().add(employeeList.get(i));
      }
      gridPane.addRow(0, scrumMaster, employeesBox1);
      gridPane.addRow(1, projectCreator, employeesBox2);
      gridPane.addRow(2, productOwner, employeesBox3);*/

    }
  }
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
      project.setScrumMaster(assignedEmployees.get(employeesBox1.getSelectionModel().getSelectedIndex()));
      project.setProjectCreator(assignedEmployees.get(employeesBox2.getSelectionModel().getSelectedIndex()));
      project.setProductOwner(assignedEmployees.get(employeesBox3.getSelectionModel().getSelectedIndex()));

      assignedEmployees.get(employeesBox1.getSelectionModel().getSelectedIndex()).setStatus(0);
      assignedEmployees.get(employeesBox2.getSelectionModel().getSelectedIndex()).setStatus(2);
      assignedEmployees.get(employeesBox3.getSelectionModel().getSelectedIndex()).setStatus(1);

      projectsAdapter.saveProjects(projectList);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("Adding successful");
      alert.setContentText("New project was successfully added to the list!");
      alert.showAndWait();
    }
    return OK;

  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      if (e.getSource() == finishButton){

      }

    }
  }
}
