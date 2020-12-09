import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class ViewAssignedTasksGUI1 extends Application{
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private HBox searchPane;
    private TextField searchField;
    private Button searchButton;

    private TableView allAssignedTasksTable;
    private TableColumn nameColumn;
    private TableColumn birthdayColumn;


    public void start(Stage window){
        window.setTitle("View Assigned Tasks");

        titleLabel = new Label("View Assigned Tasks");
        titleLabel.setFont(new Font("Cambria", 30));

        searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchButton = new Button("Search");

        searchPane = new HBox(8);
        searchPane.getChildren().addAll(searchField, searchButton);
        searchPane.setAlignment(Pos.BOTTOM_RIGHT);

        topPane = new HBox(500);
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
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}