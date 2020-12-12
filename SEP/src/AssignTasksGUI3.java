import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of tasks.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI3 {

    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label titleLabel;
    private Label tableLabel;

    private TableView tasksTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn taskNameColumn;
    private TableColumn taskDescriptionColumn;

    private Button buttonContinue;
    private Button goBackButton;


    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI3(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        titleLabel = new Label();
        titleLabel.getStyleClass().add("heading");

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

        tableLabel = new Label("Choose a requirement from the list");

        tasksTable = new TableView();
        tasksTable.setPrefHeight(290);
        tasksTable.setTableMenuButtonVisible(true);

        taskNameColumn = new TableColumn("Task Name");
        taskNameColumn.setPrefWidth(460);

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
            titleLabel.setText("HAVE TO FIGURE IT OUT");
            gogo =true;
        }
        return gogo;
    }
}