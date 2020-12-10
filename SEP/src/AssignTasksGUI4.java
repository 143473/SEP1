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
public class AssignTasksGUI4 {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private TableView allAssignedTasksTable;
    private TableColumn nameColumn;
    private TableColumn birthdayColumn;

    private Button buttonContinue;

    public AssignTasksGUI4(){

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

        buttonContinue = new Button("Continue");

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable, buttonContinue);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getButtonContinue()
    {
        return buttonContinue;
    }
}