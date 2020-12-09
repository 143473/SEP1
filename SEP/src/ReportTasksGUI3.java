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
public class ReportTasksGUI3 {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;
    private Label numberOfHoursLabel;

    private TextField numberOfHoursField;
    private CheckBox finishedField;

    private Button reportButton;
    private GridPane informationPane;

    public void start(Stage window){
        window.setTitle("Report Tasks");

        titleLabel = new Label("Report a Tasks");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel);

        numberOfHoursLabel = new Label("Number of hours:");
        numberOfHoursField = new TextField();
        numberOfHoursField.setPromptText("hh:hh");
        numberOfHoursField.setMaxWidth(60);

        finishedField = new CheckBox("finished");

        informationPane = new GridPane();
        informationPane.add(numberOfHoursLabel, 0, 0);
        informationPane.add(numberOfHoursField, 1, 0);
        informationPane.add(finishedField, 0, 1);
        informationPane.setVgap(10);

        reportButton = new Button("Report");

        mainPane = new VBox();
        mainPane.getChildren().addAll(titleLabel, informationPane, reportButton);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}