import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI5 extends Application {
    private VBox mainPane;

    private Label titleLabel;

    private Label dayLabel;
    private TextField dayField;
    private TextField monthField;
    private TextField yearField;
    private HBox dayPane;

    private GridPane informationPane;

    private Button assignButton;

    public void start(Stage window) {
        window.setTitle("Assign Tasks");

        mainPane = new VBox();

        titleLabel = new Label("Assign a task");
        titleLabel.setFont(new Font("Cambria", 32));

        dayLabel = new Label("Birthday:");
        dayLabel.setPadding(new Insets(10, 0, 5, 10));
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
        informationPane.add(dayLabel, 0, 2);
        informationPane.add(dayPane, 1, 2);
        informationPane.setVgap(10);

        assignButton = new Button("Assign");

        mainPane.getChildren().addAll(titleLabel, informationPane, assignButton);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}