import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class EmployeeStatisticsGUI extends Application {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private HBox searchPane;
    private Label searchLabel;
    private TextField searchField;
    private Button searchButton;

    private TableView allEmployeesTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn birthdayColumn;
    private TableColumn expectedColumn;
    private TableColumn actualColumn;
    private TableColumn productivityColumn;
    private TableColumn coWorkersColumn;
    private TableColumn projectsColumn;


    public void start(Stage window){
        window.setTitle("Employee Statistics");

        titleLabel = new Label("Employee statistics");
        titleLabel.setFont(new Font("Cambria", 32));

        searchLabel = new Label("Search by name: ");
        searchField = new TextField();
        searchField.setPromptText("Bob");
        searchButton = new Button("Search");

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchLabel, searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel, searchPane);

        allEmployeesTable = new TableView();
        allEmployeesTable.setPrefHeight(290);
        allEmployeesTable.setTableMenuButtonVisible(true);

        firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setPrefWidth(165);

        lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setPrefWidth(165);

        birthdayColumn = new TableColumn("Birthday");
        birthdayColumn.setPrefWidth(165);

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

        firstNameColumn.setReorderable(false);
        lastNameColumn.setReorderable(false);
        birthdayColumn.setReorderable(false);
        expectedColumn.setReorderable(false);
        actualColumn.setReorderable(false);
        productivityColumn.setReorderable(false);
        coWorkersColumn.setReorderable(false);
        projectsColumn.setReorderable(false);

        allEmployeesTable.getColumns().add(firstNameColumn);
        allEmployeesTable.getColumns().add(lastNameColumn);
        allEmployeesTable.getColumns().add(birthdayColumn);
        allEmployeesTable.getColumns().add(expectedColumn);
        allEmployeesTable.getColumns().add(actualColumn);
        allEmployeesTable.getColumns().add(productivityColumn);
        allEmployeesTable.getColumns().add(coWorkersColumn);
        allEmployeesTable.getColumns().add(projectsColumn);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allEmployeesTable);

        Scene scene = new Scene(mainPane, 1000, 390);

        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }

}