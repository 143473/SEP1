import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of tasks.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI5 {

    private SepGUI sepGUI;
    private VBox mainPane;

    private Label titleLabel;

    private Label dayLabel;
    private TextField dayField;
    private TextField monthField;
    private TextField yearField;
    private HBox dayPane;

    private HBox bottomButtons;

    private GridPane informationPane;
    private FlowPane listPane;

    private Button assignButton;
    private Button goBackButton;

    private ListView employeeListView;

    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI5(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        titleLabel = new Label("Assign a task");
        titleLabel.getStyleClass().add("heading");

        dayLabel = new Label("Date for the task: ");
        dayField = new TextField();
        dayField.setPromptText("dd");
        dayField.setMaxWidth(40);
        monthField = new TextField();
        monthField.setPromptText("mm");
        monthField.setMaxWidth(40);
        yearField = new TextField();
        yearField.setPromptText("yyyy");
        yearField.setMaxWidth(60);

        dayPane = new HBox(5);
        dayPane.getChildren().addAll(dayField, monthField, yearField);

        informationPane = new GridPane();
        informationPane.setVgap(8);
        informationPane.addRow(0,dayLabel,dayPane);
        /*informationPane.add(dayLabel, 0, 2);
        informationPane.add(dayPane, 1, 2);*/
        informationPane.setVgap(8);

        employeeListView = new ListView<Employee>();
        listPane = new FlowPane();
        listPane.setAlignment(Pos.BASELINE_RIGHT);
        listPane.setPrefWidth(200);
        listPane.getChildren().add(employeeListView);

        assignButton = new Button("Assign");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(assignButton, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(titleLabel, informationPane, bottomButtons, employeeListView);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
    public boolean callContinueButton(){
        boolean gogo = true;
        if(sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No employee was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else
        {
           /* String name = sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getFirstName() + " "+
                sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getLastName();
            employeeName.setText(name);*/
            gogo =true;
        }
        return gogo;
    }
}