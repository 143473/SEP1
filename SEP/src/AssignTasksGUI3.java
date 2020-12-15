import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of tasks.
 * @author Timothy Johan Engkar, Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class AssignTasksGUI3 {

    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label titleLabel;
    private Label tableLabel;

    private TableView<Task> tasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn taskNameColumn;
    private TableColumn taskDescriptionColumn;

    private Button buttonContinue;
    private Button goBackButton;


    /**
     * 2-argument constructor initializing the GUI components
     * @param sepGUI the main GUI where all the other GUIs are connected
     */
    public AssignTasksGUI3(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        titleLabel = new Label();
        titleLabel.getStyleClass().add("heading");

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

        tableLabel = new Label("Choose a task from the list");

        tasksTable = new TableView();
        tasksTable.setPrefHeight(290);
        tasksTable.setTableMenuButtonVisible(true);

        taskNameColumn = new TableColumn("Task Name");
        taskNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        taskNameColumn.setPrefWidth(460);

        taskDescriptionColumn = new TableColumn("Task Description");
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        taskDescriptionColumn.setPrefWidth(500);

        taskNameColumn.setReorderable(false);
        taskDescriptionColumn.setReorderable(false);

        tasksTable.getColumns().add(taskNameColumn);
        tasksTable.getColumns().add(taskDescriptionColumn);

        buttonContinue = new Button("Continue");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(buttonContinue, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(topPane, tableLabel, tasksTable,bottomButtons);
    }

    /**
     * Initializes the table and gets the mainPane
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    /**
     * Gets the buttonContinue Button
     * @return Button continueButton
     */
    public Button getButtonContinue()
    {
        return buttonContinue;
    }

    /**
     * Gets the goBackButton Button
     * @return Button goBackButton
     */
    public Button getGoBackButton()
    {
        return goBackButton;
    }

    /**
     * Gets the tasksTable TableView<Task>
     * @return TableView<Task> tasksTable
     */
    public TableView<Task> getTasksTable()
    {
        return tasksTable;
    }

    /**
     * Checks the validity of entered data after the continue Button is called
     * @return boolean true if all the input data is correct, otherwise false
     */
    public boolean callContinueButton(){
        boolean gogo = true;
        if(sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No requirement was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else
        {
            String projectName = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem().getName();
            String requirementName = sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem().toString();
            titleLabel.setText(projectName+"\\"+requirementName);
            gogo =true;
        }
        return gogo;
    }

    /**
     * Initializes the tasksTable with updated values
     */
    private void initializeTable()
    {
        tasksTable.getItems().clear();
        Requirement requirement = (Requirement)sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem();
        for (int i = 0; i < requirement.getTasks().size(); i++)
        {
            if(requirement.getTasks().get(i).getStatus().equals("Started"))
            {
                tasksTable.getItems().add(requirement.getTasks().get(i));
            }
        }
    }
}