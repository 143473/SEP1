import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of students.
 * @author Allan Henriksen
 * @version 3.0
 */
public class EmployeesTab extends Tab
{
  private VBox EmployeesPane;

  private TableView<Employee> EmployeesTable;
  private TableViewSelectionModel<Employee> defaultSelectionModel;
  private TableColumn<Employee, String> firstNameColumn;
  private TableColumn<Employee, String> lastNameColumn;
  private TableColumn<Employee, String> countryColumn;

  private Button getButton;

  private MyActionListener listener;

  private EmployeesAdapter adapterEmp;

  /**
   * Constructor initializing the GUI components
   * @param title The title of the tab
   * @param adapterEmp StudentFileAdapter object used for retrieving and storing student information
   */
  public EmployeesTab(String title, EmployeesAdapter adapterEmp)
  {
    super(title);

    this.adapterEmp = adapterEmp;

    listener = new MyActionListener();

    EmployeesTable = new TableView<Employee>();
    defaultSelectionModel = EmployeesTable.getSelectionModel();
    EmployeesTable.setPrefHeight(290);

    firstNameColumn = new TableColumn<Employee, String>("First Name");
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
    firstNameColumn.setPrefWidth(165);

    lastNameColumn = new TableColumn<Employee, String>("Last Name");
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
    lastNameColumn.setPrefWidth(165);

    countryColumn = new TableColumn<Employee, String>("Country");
    countryColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("country"));
    countryColumn.setPrefWidth(165);

    firstNameColumn.setReorderable(false);
    lastNameColumn.setReorderable(false);
    countryColumn.setReorderable(false);

    EmployeesTable.getColumns().add(firstNameColumn);
    EmployeesTable.getColumns().add(lastNameColumn);
    EmployeesTable.getColumns().add(countryColumn);

    getButton = new Button("Get Students");
    getButton.setOnAction(listener);

    EmployeesPane = new VBox(10);
    EmployeesPane.setAlignment(Pos.CENTER);
    EmployeesPane.getChildren().add(EmployeesTable);
    EmployeesPane.getChildren().add(getButton);

    super.setContent(EmployeesPane);
  }

  /**
   * Enables or disables selection in the allStudentsTable
   * @param bool if true then the area will be editable, if false then it will not
   */
  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      EmployeesTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      EmployeesTable.getSelectionModel().clearSelection();
      EmployeesTable.setSelectionModel(null);
    }
  }

  /**
   * Updates the allStudentsTable tableView with information from the students file
   */
  public void updateStudentArea()
  {
    EmployeesTable.getItems().clear();
    EmployeeList employees = adapterEmp.getAllEmployees();

    for(int i = 0; i<employees.size(); i++)
    {
      EmployeesTable.getItems().add(employees.get(i));
    }
  }

  /*
   * Inner action listener class
   * @author Allan Henriksen
   * @version 4.0
   */
  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      if (e.getSource() == getButton)
      {
        updateStudentArea();
      }
    }
  }
}