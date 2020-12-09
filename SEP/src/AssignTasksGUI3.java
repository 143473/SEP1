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
public class AssignTasksGUI3 {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private TableView tasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn taskNameColumn;
    private TableColumn taskDescriptionColumn;


    public void start(Stage window){
        window.setTitle("Assign Tasks");

        titleLabel = new Label("Task Table");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

        tasksTable = new TableView();
        tasksTable.setPrefHeight(290);
        tasksTable.setTableMenuButtonVisible(true);

        taskNameColumn = new TableColumn("Task Name");
        taskNameColumn.setPrefWidth(500);

        taskDescriptionColumn = new TableColumn("Task Description");
        taskDescriptionColumn.setPrefWidth(500);

        taskNameColumn.setReorderable(false);
        taskDescriptionColumn.setReorderable(false);

        tasksTable.getColumns().add(taskNameColumn);
        tasksTable.getColumns().add(taskDescriptionColumn);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, tasksTable);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}