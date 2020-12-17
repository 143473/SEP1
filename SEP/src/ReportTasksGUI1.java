import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * Class for reporting the assigned tasks
 * @author Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class ReportTasksGUI1 {

    private EmployeeAdapter employeeAdapter;
    private AssignedTasksAdapter assignedTasksAdapter;

    private VBox mainPane;

    private Label titleLabel;
    private Label tableLabel;


    private Button continueButton;

    private TableView<Employee> allAssignedTasksTable;
    private TableColumn<Employee, String> firstNameColumn;
    private TableColumn<Employee, String> lastNameColumn;
    private TableColumn<Employee, MyDate> birthdayColumn;

    /**
     * 2-argument constructor initializing the GUI components
     * @param employeeAdapter adapter of the employees
     * @param assignedTasksAdapter adapter of the assigned tasks
     */
    public ReportTasksGUI1(EmployeeAdapter employeeAdapter, AssignedTasksAdapter assignedTasksAdapter)
    {
        this.assignedTasksAdapter = assignedTasksAdapter;
        this.employeeAdapter = employeeAdapter;
        titleLabel = new Label("Report Tasks");
        titleLabel.getStyleClass().add("heading");
        titleLabel.setPrefWidth(520);

        tableLabel = new Label("Choose an employee from the list ");

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

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
        mainPane.getChildren().addAll(titleLabel, tableLabel, allAssignedTasksTable,continueButton);
    }

    /**
     * Initializes the allAssignedTasksTable with updated values
     */
    private void initializeTable(){
        allAssignedTasksTable.getItems().clear();
        EmployeeList employees = assignedTasksAdapter.getAllAssignedEmployees();

        for (int i = 0; i < employees.size(); i++) {
            allAssignedTasksTable.getItems().add(employees.get(i));
        }
    }

    /**
     * Gets allAssignedTasksTable TableView
     * @return TableView allAssignedTasksTable
     */
    public TableView<Employee> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
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
     * Gets the continueButton Button
     * @return Button continueButton
     */
    public Button getContinueButton()
    {
        return continueButton;
    }
}