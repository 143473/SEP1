import javafx.application.Application;
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
public class AssignTasksGUI4 extends Application{
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private TableView allAssignedTasksTable;
    private TableColumn nameColumn;
    private TableColumn birthdayColumn;


    public void start(Stage window){
        window.setTitle("View Assigned Tasks");

        titleLabel = new Label("View Assigned Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel);

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