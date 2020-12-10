import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * The 3rd part of the project creation user interface, that allows for
 * setting the status for the employees
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI3 {
  private ProjectsAdapter projectsAdapter;
  private ProjectList projectList;
  private GridPane gridPane;
  private HBox hBoxPane;
  private VBox mainPane;

  private CreateProjectGUI2 createProjectGUI2;

  private Label title;
  private Label scrumMaster;
  private Label productOwner;
  private Label projectCreator;

  private ComboBox<String> employeesBox1;
  private ComboBox<String> employeesBox2;
  private ComboBox<String> employeesBox3;

  private Button finishButton;
  private Button goBackButton;

  public CreateProjectGUI3(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    title = new Label("Set team members roles");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    scrumMaster = new Label("Scrum Master: ");
    projectCreator = new Label("Project Creator: ");
    productOwner = new Label("Product Owner");

    employeesBox1 = new ComboBox<String>();
    employeesBox2 = new ComboBox<String>();
    employeesBox3 = new ComboBox<String>();

    gridPane = new GridPane();
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.addRow(0, scrumMaster, employeesBox1);
    gridPane.addRow(1, projectCreator, employeesBox2);
    gridPane.addRow(2, productOwner, employeesBox3);

    finishButton = new Button("Finish");
    goBackButton = new Button("Go Back");

    hBoxPane = new HBox();
    hBoxPane.setSpacing(20);
    hBoxPane.getChildren().addAll(finishButton, goBackButton);

    mainPane = new VBox();
    mainPane.setSpacing(5);
    mainPane.getChildren().addAll(title, gridPane, hBoxPane);
  }

  public void setProjectList(ProjectList projectList) {
    this.projectList = projectList;
  }

  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getFinishButton()
  {
    return finishButton;
  }

  /*public boolean callFinishButton() {
    boolean allValuesCorrect = true;
    if (employeesBox1.getItems().isEmpty() &&
            employeesBox2.getItems().isEmpty() &&
            employeesBox3.getItems().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No statuses were chosen");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    if(allValuesCorrect){
      EmployeeList employeeList = employeeAdapter.getAllEmployees();
      Employee newEmployee = new Employee(nameField.getText(), lastNameField.getText(), dateOfBirth);
      if(!employeeList.containsEmployee(newEmployee)){
        employeeList.addEmployee(newEmployee);
        employeeAdapter.saveEmployees(employeeList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Message");
        alert.setContentText("New project was successfully added!");
        alert.showAndWait();
      }
      else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Duplicate project");
        alert.setContentText("This project already exists!");
        alert.showAndWait();
      }

    return allValuesCorrect;
  }*/

  public Button getGoBackButton()
  {
    return goBackButton;
  }
}
