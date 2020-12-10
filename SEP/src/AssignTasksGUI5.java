import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI5 {
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

    public AssignTasksGUI5(){

        titleLabel = new Label("Assign a task");
        titleLabel.setFont(new Font("Cambria", 32));

        dayLabel = new Label("Birthday:");
        dayLabel.setPadding(new Insets(10, 0, 0, 10));
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
        informationPane.setVgap(10);
        informationPane.addRow(0,dayLabel,dayPane);
        /*informationPane.add(dayLabel, 0, 2);
        informationPane.add(dayPane, 1, 2);*/
        informationPane.setVgap(10);

        employeeListView = new ListView<Employee>();
        listPane = new FlowPane();
        listPane.setAlignment(Pos.BASELINE_RIGHT);
        listPane.setPrefWidth(200);
        listPane.getChildren().add(employeeListView);

        assignButton = new Button("Assign");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(5);
        bottomButtons.getChildren().addAll(assignButton, goBackButton);

        mainPane = new VBox(5);
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
}