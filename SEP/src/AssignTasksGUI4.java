import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * A GUI tab containing components for displaying a list of assigned employees.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI4 {
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label titleLabel;

    private TableView allAssignedTasksTable;
    private TableColumn nameColumn;
    private TableColumn birthdayColumn;

    private Button buttonContinue;
    private Button goBackButton;

    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI4(){

        titleLabel = new Label("Assign Employee");
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
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(5);
        bottomButtons.getChildren().addAll(buttonContinue, goBackButton);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable, bottomButtons);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getButtonContinue()
    {
        return buttonContinue;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
}