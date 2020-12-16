import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of projects.
 * @author Timothy Engkar, Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class AssignTasksGUI1 {
    private ProjectsAdapter projectsAdapter;

    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;
    private Label tableLabel;

    private TableView<Project> assignTasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn projectNameColumn;
    private TableColumn projectDescriptionColumn;

    private Button continueButton;

    /**
     * 1-argument constructor initializing the GUI components
     * @param projectsAdapter adapter of the projects, requirements and tasks
     */
    public AssignTasksGUI1(ProjectsAdapter projectsAdapter){

        this.projectsAdapter = projectsAdapter;
        titleLabel = new Label("Assign Tasks");
        titleLabel.getStyleClass().add("heading");

        tableLabel = new Label("Choose a project from the list");

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

        assignTasksTable = new TableView();
        assignTasksTable.setPrefHeight(290);
        assignTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn("Project Name");
        projectNameColumn.setPrefWidth(460);

        projectDescriptionColumn = new TableColumn("Project Description");
        projectDescriptionColumn.setPrefWidth(500);

        projectNameColumn.setReorderable(false);
        projectDescriptionColumn.setReorderable(false);

        assignTasksTable.getColumns().add(projectNameColumn);
        projectNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        assignTasksTable.getColumns().add(projectDescriptionColumn);
        projectDescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));

        continueButton = new Button("Continue");

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(topPane, tableLabel,assignTasksTable,continueButton);
    }

    /**
     * Initializes the assignTaskTable with updated values
     */
    private void initializeTable()
    {
        assignTasksTable.getItems().clear();
        ProjectList projects = projectsAdapter.getAllProjects();
        for (int i = 0; i < projects.size(); i++)
        {
            assignTasksTable.getItems().add(projects.get(i));
        }
    }

    /**
     * Gets the mainPane and initializes the assignTasksTable
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    /**
     * Gets the continueButton Button
     * @return Button continueButton
     */
    public Button getContinueButton()
    {
        return continueButton;
    }

    /**
     * Gets the assignTasksTable
     * @return TableView assignTasksTable
     */
    public TableView<Project> getAssignTasksTable()
    {
        return assignTasksTable;
    }
}