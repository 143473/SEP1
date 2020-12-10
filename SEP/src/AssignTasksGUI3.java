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
public class AssignTasksGUI3 {
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label titleLabel;

    private TableView tasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn taskNameColumn;
    private TableColumn taskDescriptionColumn;

    private Button buttonContinue;
    private Button goBackButton;


    public AssignTasksGUI3(){

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

        buttonContinue = new Button("Continue");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(5);
        bottomButtons.getChildren().addAll(buttonContinue, goBackButton);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, tasksTable,bottomButtons);
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