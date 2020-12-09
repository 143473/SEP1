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
public class AssignTasksGUI1 extends Application {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private TableView assignTasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn projectNameColumn;
    private TableColumn projectDescriptionColumn;


    public void start(Stage window){
        window.setTitle("Assign Tasks");

        titleLabel = new Label("Assign Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

        assignTasksTable = new TableView();
        assignTasksTable.setPrefHeight(290);
        assignTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn("Project Name");
        projectNameColumn.setPrefWidth(500);

        projectDescriptionColumn = new TableColumn("Project Description");
        projectDescriptionColumn.setPrefWidth(500);

        projectNameColumn.setReorderable(false);
        projectDescriptionColumn.setReorderable(false);

        assignTasksTable.getColumns().add(projectNameColumn);
        assignTasksTable.getColumns().add(projectDescriptionColumn);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, assignTasksTable);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}