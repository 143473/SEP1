import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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


    private void initializeTable(){
        allAssignedTasksTable.getItems().clear();
        EmployeeList employees = assignedTasksAdapter.getAllAssignedEmployees();

        for (int i = 0; i < employees.size(); i++) {
            allAssignedTasksTable.getItems().add(employees.get(i));
        }
    }

    public TableView<Employee> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
    }

    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }


    public Button getContinueButton()
    {
        return continueButton;
    }
}