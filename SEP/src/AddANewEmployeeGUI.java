import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddANewEmployeeGUI{
    private VBox mainPane;

    private Label titleLabel;

    private Label nameLabel;
    private TextField nameField;

    private Label lastNameLabel;
    private TextField lastNameField;

    private Label birthdayLabel;
    private TextField dayField;
    private TextField monthField;
    private TextField yearField;
    private HBox birthdayPane;

    private GridPane informationPane;

    private Button addButton;

    public AddANewEmployeeGUI() {

        mainPane = new VBox();

        titleLabel = new Label("Add a new employee");
        titleLabel.setFont(new Font("Cambria", 32));

        nameLabel = new Label("Name(s):");
        nameLabel.setPadding(new Insets(10, 0, 5, 10));
        nameField = new TextField();
        nameField.setMaxWidth(100);

        lastNameLabel = new Label("Last name(s):");
        lastNameLabel.setPadding(new Insets(10, 0, 5, 10));
        lastNameField = new TextField();
        lastNameField.setMaxWidth(100);

        birthdayLabel = new Label("Birthday:");
        birthdayLabel.setPadding(new Insets(10, 0, 5, 10));
        dayField = new TextField();
        dayField.setPromptText("dd");
        dayField.setMaxWidth(40);
        monthField = new TextField();
        monthField.setPromptText("mm");
        monthField.setMaxWidth(40);
        yearField = new TextField();
        yearField.setPromptText("yyyy");
        yearField.setMaxWidth(60);

        birthdayPane = new HBox(5);
        birthdayPane.getChildren().addAll(dayField, monthField, yearField);

        informationPane = new GridPane();
        informationPane.add(nameLabel, 0, 0);
        informationPane.add(nameField, 1, 0);
        informationPane.add(lastNameLabel, 0, 1);
        informationPane.add(lastNameField, 1, 1);
        informationPane.add(birthdayLabel, 0, 2);
        informationPane.add(birthdayPane, 1, 2);
        informationPane.setVgap(10);

        addButton = new Button("Add");


        mainPane.getChildren().addAll(titleLabel, informationPane, addButton);
    }
    public VBox getMainPane(){
        return mainPane;
    }

}