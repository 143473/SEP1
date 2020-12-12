import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *  A class handling adding a new employee
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */

public class AddANewEmployeeGUI{
    private EmployeeAdapter employeeAdapter;

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

    private MyActionListener listener;

    /**
     *  1-argument constructor initializing all the parts of the GUI
     * @param employeeAdapter adapter of this class
     */
    public AddANewEmployeeGUI(EmployeeAdapter employeeAdapter) {
        this.employeeAdapter = employeeAdapter;

        listener = new MyActionListener();

        mainPane = new VBox(8);

        titleLabel = new Label("Add a new employee");
        titleLabel.getStyleClass().add("heading");

        nameLabel = new Label("Name(s):");
        nameField = new TextField();
        nameField.setMaxWidth(100);

        lastNameLabel = new Label("Last name(s):");
        lastNameField = new TextField();
        lastNameField.setMaxWidth(100);

        birthdayLabel = new Label("Birthday:");
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
        informationPane.setVgap(8);
        informationPane.setHgap(8);

        addButton = new Button("Add");
        addButton.setOnAction(listener);


        mainPane.getChildren().addAll(titleLabel, informationPane, addButton);
    }

    /**
     * Gets the main pane of the class
     * @return VBox main pane
     */
    public VBox getMainPane(){
        return mainPane;
    }

    /**
     * Handles the actions in this class
     */
    private class MyActionListener implements EventHandler<ActionEvent> {
        /**
         * Handles the actions of this class
         * @param e event that happens
         */
        public void handle(ActionEvent e) {
            if(e.getSource() == addButton){
                boolean allValuesCorrect = true;
                MyDate dateOfBirth;
                if(nameField.getText() == null || nameField.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Invalid input");
                    alert.setContentText("First name cannot be empty!");
                    alert.showAndWait();
                    allValuesCorrect = false;
                }
                else if(lastNameField.getText() == null || lastNameField.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Invalid input");
                    alert.setContentText("Last name cannot be empty!");
                    alert.showAndWait();
                    allValuesCorrect = false;
                }
                else if(dayField.getText().isEmpty() || monthField.getText().isEmpty() || yearField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Invalid input");
                    alert.setContentText("Date of birth cannot be empty!");
                    alert.showAndWait();
                    allValuesCorrect = false;
                }
                else{
                    try {
                        int temporary = Integer.parseInt(dayField.getText());
                        temporary = Integer.parseInt(monthField.getText());
                        temporary = Integer.parseInt(yearField.getText());
                    } catch (NumberFormatException nfe) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Invalid input");
                        alert.setContentText("Values in date of birth have to be numbers!");
                        alert.showAndWait();
                        allValuesCorrect = false;
                    }
                }
                if(allValuesCorrect){
                    dateOfBirth = new MyDate(Integer.parseInt(dayField.getText().replaceFirst("^0+(?!$)", "")), Integer.parseInt(monthField.getText().replaceFirst("^0+(?!$)", "")), Integer.parseInt(yearField.getText().replaceFirst("^0+(?!$)", "")));
                    if(!dateOfBirth.is15Years()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Invalid input");
                        alert.setContentText("Employee has to be at least 15 years old!");
                        alert.showAndWait();
                        allValuesCorrect = false;
                    }
                    else if(!dateOfBirth.isValidDate()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Invalid input");
                        alert.setContentText("Entered date is not valid!");
                        alert.showAndWait();
                        allValuesCorrect = false;
                    }
                    if(allValuesCorrect){
                        EmployeeList employeeList = employeeAdapter.getAllEmployees();
                        Employee newEmployee = new Employee(nameField.getText(), lastNameField.getText(), dateOfBirth);
                        if(!employeeList.containsEmployee(newEmployee)){
                            employeeList.addEmployee(newEmployee);
                            employeeAdapter.saveEmployees(employeeList);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Message");
                            alert.setContentText("New employee was successfully added!");
                            alert.showAndWait();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText("Duplicate employee");
                            alert.setContentText("This employee is already in the list!");
                            alert.showAndWait();
                        }

                    }
                }
            }
        }
    }



}