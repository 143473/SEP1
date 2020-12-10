import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI1 {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;
    private Label tableLabel;

    private TableView assignTasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn projectNameColumn;
    private TableColumn projectDescriptionColumn;

    private Button continueButton;


    public AssignTasksGUI1(){

        titleLabel = new Label("Assign Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        tableLabel = new Label("Choose a project from the list");

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

        continueButton = new Button("Continue");

        mainPane = new VBox(5);
        mainPane.getChildren().addAll(topPane, tableLabel,assignTasksTable,continueButton);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getContinueButton()
    {
        return continueButton;
    }
}