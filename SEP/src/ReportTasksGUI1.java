import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReportTasksGUI1 {

    private EmployeeAdapter employeeAdapter;
    private MyActionListener listener;

    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;
    private Label tableLabel;

    private HBox searchPane;
    private TextField searchField;
    private Button searchButton;

    private Button continueButton;

    private TableView<Employee> allAssignedTasksTable;
    private TableColumn<Employee, String> firstNameColumn;
    private TableColumn<Employee, String> lastNameColumn;
    private TableColumn<Employee, MyDate> birthdayColumn;


    public ReportTasksGUI1(EmployeeAdapter employeeAdapter)
    {
        this.listener = new MyActionListener();
        this.employeeAdapter = employeeAdapter;
        titleLabel = new Label("Report Tasks");
        titleLabel.getStyleClass().add("heading");
        titleLabel.setPrefWidth(520);

        tableLabel = new Label("Choose an employee from the list ");

        searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchButton = new Button("Search");
        searchButton.setOnAction(listener);

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(250);
        topPane.getChildren().addAll(titleLabel, searchPane);

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
        mainPane.getChildren().addAll(topPane,tableLabel, allAssignedTasksTable,continueButton);
    }


    private void initializeTable(){
        allAssignedTasksTable.getItems().clear();
        EmployeeList employees = employeeAdapter.getAllEmployees();

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
    private class MyActionListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e) {
            if (e.getSource() == searchButton) {
                String searchingFor = searchField.getText();
                allAssignedTasksTable.getItems().clear();
                EmployeeList chosenEmployees = employeeAdapter.getEmployeesByName(searchingFor);
                for (int i = 0; i < chosenEmployees.size(); i++) {
                    allAssignedTasksTable.getItems().add(chosenEmployees.get(i));
                }
            }
        }
    }
}