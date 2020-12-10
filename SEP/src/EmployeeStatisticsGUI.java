import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    /*private TableColumn expectedColumn;
    private TableColumn actualColumn;
    private TableColumn productivityColumn;
    private TableColumn coWorkersColumn;
    private TableColumn projectsColumn;
*/

    private MyActionListener listener;
    public EmployeeStatisticsGUI(EmployeeAdapter employeeAdapter){
        this.employeeAdapter = employeeAdapter;

        listener = new MyActionListener();

        titleLabel = new Label("Employee statistics");
        titleLabel.setFont(new Font("Cambria", 32));

        searchLabel = new Label("Search by name: ");
        searchField = new TextField();
        searchField.setPromptText("Bob");
        searchButton = new Button("Search");
        searchButton.setOnAction(listener);

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchLabel, searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(400);
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

        /*
        expectedColumn = new TableColumn("Expected number of hours");
        expectedColumn.setPrefWidth(165);

        actualColumn = new TableColumn("Actual number of hours");
        actualColumn.setPrefWidth(165);

        productivityColumn = new TableColumn("Productivity");
        productivityColumn.setPrefWidth(165);

        coWorkersColumn = new TableColumn("Co workers");
        coWorkersColumn.setPrefWidth(165);

        projectsColumn = new TableColumn("Projects");
        projectsColumn.setPrefWidth(165);


         */
        firstNameColumn.setReorderable(false);
        lastNameColumn.setReorderable(false);
        birthdayColumn.setReorderable(false);
        birthdayColumn.setSortable(false);
        /*
        expectedColumn.setReorderable(false);
        actualColumn.setReorderable(false);
        productivityColumn.setReorderable(false);
        coWorkersColumn.setReorderable(false);
        projectsColumn.setReorderable(false);


         */
        allEmployeesTable.getColumns().add(firstNameColumn);
        allEmployeesTable.getColumns().add(lastNameColumn);
        allEmployeesTable.getColumns().add(birthdayColumn);

        /*
        allEmployeesTable.getColumns().add(expectedColumn);
        allEmployeesTable.getColumns().add(actualColumn);
        allEmployeesTable.getColumns().add(productivityColumn);
        allEmployeesTable.getColumns().add(coWorkersColumn);
        allEmployeesTable.getColumns().add(projectsColumn);


         */
        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allEmployeesTable);
    }
    private void initializeTable(){
        allEmployeesTable.getItems().clear();
        EmployeeList employees = employeeAdapter.getAllEmployees();

        for (int i = 0; i < employees.size(); i++) {
            allEmployeesTable.getItems().add(employees.get(i));
        }
    }

    public VBox getMainPane(){
        initializeTable();
        return mainPane;
    }

    private class MyActionListener implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if(e.getSource() == searchButton){
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
