import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class ViewAssignedTasksGUI2 {
    private VBox mainPane;
    private HBox topPane;
    private HBox hBoxPaneButton;


    private Label titleLabel;

    private ComboBox statusBox;
    private FlowPane comboPane;

    private TableView allAssignedTasksTable;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;;
    private TableColumn dateColumn;

    private Button goBack;

    public ViewAssignedTasksGUI2(){

        titleLabel = new Label("Name - Birthday");
        titleLabel.setFont(new Font("Cambria", 32));

        statusBox = new ComboBox<String>();
        statusBox.getItems().addAll(
                "All upcoming",
                "All"
        );

        comboPane = new FlowPane();
        comboPane.setAlignment(Pos.BASELINE_RIGHT);
        comboPane.setAlignment(Pos.BOTTOM_CENTER);
        comboPane.setPrefWidth(200);
        comboPane.getChildren().add(statusBox);

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel, comboPane);

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn("Project Name");
        projectNameColumn.setPrefWidth(500);

        requirementIDColumn = new TableColumn("Requirement ID");
        requirementIDColumn.setPrefWidth(150);

        taskIDColumn = new TableColumn("Task ID");
        taskIDColumn.setPrefWidth(150);

        dateColumn = new TableColumn("Date");
        dateColumn.setPrefWidth(200);

        projectNameColumn.setReorderable(false);
        requirementIDColumn.setReorderable(false);
        taskIDColumn.setReorderable(false);
        dateColumn.setReorderable(false);

        allAssignedTasksTable.getColumns().add(projectNameColumn);
        allAssignedTasksTable.getColumns().add(requirementIDColumn);
        allAssignedTasksTable.getColumns().add(taskIDColumn);
        allAssignedTasksTable.getColumns().add(dateColumn);

        goBack = new Button("Go Back");

        hBoxPaneButton = new HBox();
        hBoxPaneButton.getChildren().addAll(goBack);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable, hBoxPaneButton);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getGoBack()
    {
        return goBack;
    }
}