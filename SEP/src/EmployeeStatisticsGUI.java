import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for showing the full list of employees and their statistics
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class EmployeeStatisticsGUI{
    private EmployeeAdapter employeeAdapter;

    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private HBox searchPane;
    private Label searchLabel;
    private TextField searchField;
    private Button searchButton;

    private TableView<Employee> allEmployeesTable;
    private TableViewSelectionModel<Employee> defaultSelectionModel;
    private TableColumn<Employee, String> firstNameColumn;
    private TableColumn<Employee, String> lastNameColumn;
    private TableColumn<Employee, MyDate> birthdayColumn;
    private TableColumn<Employee, EmployeeList> coWorkersColumn;
    private TableColumn<Employee, ProjectList> projectsColumn;


    private MyActionListener listener;

    /**
     * 3-argument constructor initializing the GUI components
     * @param employeeAdapter adapter of the employees
     */
    public EmployeeStatisticsGUI(EmployeeAdapter employeeAdapter){
        this.employeeAdapter = employeeAdapter;

        listener = new MyActionListener();

        titleLabel = new Label("Employee statistics");
        titleLabel.getStyleClass().add("heading");

        searchField = new TextField();
        searchField.setPromptText("Search by name:");
        searchButton = new Button("Search");
        searchButton.setOnAction(listener);

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(515);
        topPane.getChildren().addAll(titleLabel, searchPane);

        allEmployeesTable = new TableView<Employee>();
        allEmployeesTable.setPrefHeight(290);
        defaultSelectionModel = allEmployeesTable.getSelectionModel();
        allEmployeesTable.setTableMenuButtonVisible(true);

        firstNameColumn = new TableColumn<Employee, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        firstNameColumn.setPrefWidth(165);

        lastNameColumn = new TableColumn<Employee, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        lastNameColumn.setPrefWidth(165);

        birthdayColumn = new TableColumn<Employee, MyDate>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, MyDate>("dateOfBirth"));
        birthdayColumn.setPrefWidth(165);

        coWorkersColumn = new TableColumn("Co workers");
        coWorkersColumn.setCellValueFactory(new PropertyValueFactory<Employee, EmployeeList>("coworkers"));
        coWorkersColumn.setPrefWidth(165);

        projectsColumn = new TableColumn("Projects");
        projectsColumn.setCellValueFactory(new PropertyValueFactory<Employee, ProjectList>("projects"));
        projectsColumn.setPrefWidth(165);

        firstNameColumn.setReorderable(false);
        lastNameColumn.setReorderable(false);
        birthdayColumn.setReorderable(false);
        birthdayColumn.setSortable(false);
        coWorkersColumn.setReorderable(false);
        projectsColumn.setReorderable(false);

        allEmployeesTable.getColumns().add(firstNameColumn);
        allEmployeesTable.getColumns().add(lastNameColumn);
        allEmployeesTable.getColumns().add(birthdayColumn);
        allEmployeesTable.getColumns().add(coWorkersColumn);
        allEmployeesTable.getColumns().add(projectsColumn);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allEmployeesTable);
    }

    /**
     * Initializes the allEmployeesTable and updates the values in it
     */
    private void initializeTable(){
        allEmployeesTable.getItems().clear();
        EmployeeList employees = employeeAdapter.getAllEmployees();

        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).getProjectsWorkedOn();
            employees.get(i).getCoworkersOfEmployee();
            allEmployeesTable.getItems().add(employees.get(i));
        }
    }

    /**
     * Initializes the allEmployeesTable and gets the mainPane
     * @return VBox mainPane
     */
    public VBox getMainPane(){
        initializeTable();
        return mainPane;
    }

    /**
     * Handles the actions in this class
     */
    private class MyActionListener implements EventHandler<ActionEvent> {
        /**
         * Handles the actions of this class
         * @param e event that happens
         */
        public void handle(ActionEvent e) {
            if (e.getSource() == searchButton) {
                String searchingFor = searchField.getText();
                allEmployeesTable.getItems().clear();
                EmployeeList chosenEmployees = employeeAdapter.getEmployeesByName(searchingFor);
                for (int i = 0; i < chosenEmployees.size(); i++) {
                    allEmployeesTable.getItems().add(chosenEmployees.get(i));
                }
            }
        }
    }
}
