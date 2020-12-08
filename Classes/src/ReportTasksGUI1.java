import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReportTasksGUI1 extends Application {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private HBox searchPane;
    private Label searchLabel;
    private TextField searchField;
    private Button searchButton;

    private TableView allAssignedTasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn nameColumn;
    private TableColumn birthdayColumn;


    public void start(Stage window){
        window.setTitle("Report Tasks");

        titleLabel = new Label("Report Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        searchLabel = new Label("Search: ");
        searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchButton = new Button("Search");

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchLabel, searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel, searchPane);

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(500);

        birthdayColumn = new TableColumn("Birthday");
        birthdayColumn.setPrefWidth(500);

        nameColumn.setReorderable(false);
        birthdayColumn.setReorderable(false);

        allAssignedTasksTable.getColumns().add(nameColumn);
        allAssignedTasksTable.getColumns().add(birthdayColumn);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable);

        Scene scene = new Scene(mainPane, 1000, 390);

        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}