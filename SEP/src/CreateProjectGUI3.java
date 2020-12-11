import com.sun.javafx.scene.paint.GradientUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

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

  public CreateProjectGUI3(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter){
    projectList = projectsAdapter.getAllProjects();

    this.employeeAdapter = employeeAdapter;
    this.projectsAdapter = projectsAdapter;

    title = new Label("Set team members roles");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    scrumMaster = new Label("Scrum Master: ");
    projectCreator = new Label("Project Creator: ");
    productOwner = new Label("Product Owner");
    employeesBox1 = new ComboBox();


    gridPane = new GridPane();
    gridPane.setVgap(10);
    gridPane.setHgap(10);

    finishButton = new Button("Finish");
    goBackButton = new Button("Go Back");

    hBoxPane = new HBox();
    hBoxPane.setSpacing(20);
    hBoxPane.getChildren().addAll(finishButton, goBackButton);

    mainPane = new VBox();
    mainPane.setSpacing(5);
    mainPane.getChildren().addAll(title, gridPane, hBoxPane);
  }

  public VBox getMainPane()
  {
    return mainPane;
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
      ArrayList<AssignedEmployee> employeeList = project.getEmployees();
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
      gridPane.addRow(2, productOwner, employeesBox3);

    }



  }
}
