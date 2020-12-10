import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ReportTasksGUI2 {
    private VBox mainPane;
    private HBox topPane;
    private HBox hBoxPaneButton;

    private Label titleLabel;

    private TableView allAssignedTasksTable;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;;
    private TableColumn dateColumn;

    private Button goBack;
    private Button continueButton;


    public ReportTasksGUI2(){

        titleLabel = new Label("Report Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel);

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
        continueButton = new Button("Continue");

        hBoxPaneButton = new HBox();
        hBoxPaneButton.getChildren().addAll(continueButton, goBack);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable, hBoxPaneButton);
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