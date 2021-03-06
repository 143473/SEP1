import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * Class for the overview of the assigned tasks to see all the employees
 * @author Claudiu Emanuel Cordunianu, Timothy Johan Engkar
 * @version 1.0
 */
public class ViewAssignedTasksGUI1 {

    private EmployeeAdapter employeeAdapter;
    private ProjectsAdapter projectsAdapter;
    private AssignedTasksAdapter assignedTasksAdapter;
    private VBox mainPane;

    private Label titleLabel;
    private Label tableLabel;

    private Button continueButton;

    private TableView<Employee> allAssignedTasksTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn birthdayColumn;

    /**
     * 3-argument constructor initializing the GUI components
     * @param projectsAdapter adapter of the projects, requirements and tasks
     * @param employeeAdapter adapter of the employees
     * @param assignedTasksAdapter adapter of the assigned tasks
     */
    public ViewAssignedTasksGUI1(EmployeeAdapter employeeAdapter,ProjectsAdapter projectsAdapter, AssignedTasksAdapter assignedTasksAdapter){

        this.projectsAdapter = projectsAdapter;
        this.employeeAdapter = employeeAdapter;
        this.assignedTasksAdapter = assignedTasksAdapter;

        titleLabel = new Label("View Assigned Tasks");
        titleLabel.setPrefWidth(520);
        titleLabel.getStyleClass().add("heading");

        tableLabel = new Label("Choose an employee from the list ");

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        firstNameColumn = new TableColumn<Employee, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        firstNameColumn.setPrefWidth(165);

        lastNameColumn = new TableColumn<Employee, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        lastNameColumn.setPrefWidth(165);

        birthdayColumn = new TableColumn<Employee, MyDate>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, MyDate>("dateOfBirth"));
        birthdayColumn.setPrefWidth(165);
        allAssignedTasksTable.getColumns().addAll(firstNameColumn,lastNameColumn,birthdayColumn);

        continueButton = new Button("Continue");

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(titleLabel,tableLabel, allAssignedTasksTable, continueButton);
    }

    /**
     * Intializes the allAssignedTasksTable with updated values
     */
    private void initializeTable(){
        allAssignedTasksTable.getItems().clear();
        EmployeeList employees = assignedTasksAdapter.getAllAssignedEmployees();

        for (int i = 0; i < employees.size(); i++) {
            allAssignedTasksTable.getItems().add(employees.get(i));
        }
    }

    /**
     * Gets the mainPane and initializes the allAssignedTasksTable
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    /**
     * Gets the allAssignedTasksTable TableView
     * @return TableView allAssignedTasksTable
     */
    public TableView<Employee> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
    }

    /**
     * Gets the continueButton Button
     * @return Button continueButton
     */
    public Button getContinueButton()
    {
        return continueButton;
    }

}