import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *  A class handling editing and removing employees
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
public class EditRemoveEmployeeGUI{
    private Label titleLabel;

    private VBox mainPane;

    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label birthdayLabel;

    private TextField firstNameField;
    private TextField lastNameField;

    private TextField dayField;
    private TextField monthField;
    private TextField yearField;
    private HBox birthdayPane;

    private GridPane informationPane;
    private HBox employeePane;

    private ListView<Employee> employeeListView;
    private FlowPane listPane;

    private Button saveButton;
    private Button removeButton;
    private HBox buttonsPane;
    private VBox middlePane;

    private EmployeeAdapter employeeAdapter;

    private MyActionListener listener;
    private MyListListener listListener;

    /**
     *  1-argument constructor initializing all the parts of the GUI
     * @param employeeAdapter adapter of employees
     */
    public EditRemoveEmployeeGUI(EmployeeAdapter employeeAdapter) {
        this.employeeAdapter = employeeAdapter;
        listener = new MyActionListener();
        listListener = new MyListListener();

        titleLabel = new Label("Edit or Remove Employee");
        titleLabel.getStyleClass().add("heading");


        firstNameLabel = new Label("First name:");
        firstNameField = new TextField();
        firstNameField.setMaxWidth(100);

        lastNameLabel = new Label("Last name:");

        lastNameField = new TextField();
        lastNameField.setMaxWidth(100);

        birthdayLabel = new Label("Birthday:");

        dayField = new TextField();
        dayField.setPromptText("dd");
        dayField.setMaxWidth(40);
        dayField.setEditable(false);
        monthField = new TextField();
        monthField.setPromptText("mm");
        monthField.setMaxWidth(40);
        monthField.setEditable(false);
        yearField = new TextField();
        yearField.setPromptText("yyyy");
        yearField.setMaxWidth(60);
        yearField.setEditable(false);

        birthdayPane = new HBox(5);
        birthdayPane.getChildren().addAll(dayField, monthField, yearField);

        firstNameField = new TextField();
        lastNameField = new TextField();

        informationPane = new GridPane();
        informationPane.setHgap(8);
        informationPane.setVgap(8);
        informationPane.addRow(0, firstNameLabel, firstNameField);
        informationPane.addRow(1, lastNameLabel, lastNameField);
        informationPane.addRow(2, birthdayLabel, birthdayPane);

        employeeListView = new ListView<Employee>();
        employeeListView.setPrefWidth(280);
        employeeListView.setPrefHeight(120);
        employeeListView.getSelectionModel().selectedItemProperty().addListener((listListener));

        listPane = new FlowPane();
        listPane.setHgap(8);
        listPane.setAlignment(Pos.BASELINE_RIGHT);
        listPane.setPrefWidth(300);

        saveButton = new Button("Save");
        saveButton.setOnAction(listener);
        removeButton = new Button("Remove");
        removeButton.setOnAction(listener);

        buttonsPane = new HBox(8);
        buttonsPane.getChildren().addAll(saveButton, removeButton);

        middlePane = new VBox(8);
        middlePane.getChildren().addAll(informationPane,buttonsPane);

        employeePane = new HBox(8);
        employeePane.getChildren().addAll(middlePane, employeeListView);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(titleLabel, employeePane);
    }

    /**
     * Listener to the actions
     */
    private class MyActionListener implements EventHandler<ActionEvent>
    {
        /**
         * Handles the actions in this class
         * @param e the action that happened
         */
        public void handle(ActionEvent e)
        {
            Employee temp = employeeListView.getSelectionModel().getSelectedItem();
            //employee's index in the EmployeeList but the first one is invalid
            int index = employeeListView.getSelectionModel().getSelectedIndex()-1;
            if (e.getSource() == saveButton)
            {
                //not selected
                if(employeeListView.getSelectionModel().getSelectedItem() != null){
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        MyDate day = temp.getDateOfBirth();

                        employeeAdapter.saveChangedEmployee(firstName, lastName, day, index);
                        initializeListView();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Editing successful");
                        alert.setContentText("Changes were saved successfully!");
                        alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Editing unsuccessful");
                    alert.setContentText("No employee was chosen!");
                    alert.showAndWait();
                }

            }
            if(e.getSource() == removeButton){
                if(employeeListView.getSelectionModel().getSelectedItem() != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Do you really want to delete employee " + firstNameField.getText() + " " + lastNameField.getText() + " (" + dayField.getText() + "/" + monthField.getText() + "/" + yearField.getText() + ")?",
                            ButtonType.YES,
                            ButtonType.NO);
                    alert.setTitle("Delete employee");
                    alert.setHeaderText(null);

                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                        employeeAdapter.deleteEmployee(index);
                        firstNameField.setText("");
                        lastNameField.setText("");
                        dayField.setText("");
                        monthField.setText("");
                        yearField.setText("");
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Editing successful");
                        alert2.setContentText("Changes were saved successfully!");
                        alert2.showAndWait();
                        initializeListView();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Editing unsuccessful");
                    alert.setContentText("No employee was chosen!");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * Initializes ListView, clears and puts new values in
     */
    public void initializeListView(){
        employeeListView.getItems().clear();
        EmployeeList employees = employeeAdapter.getAllEmployees();
        employeeListView.getItems().add(null);
        for (int i = 0; i < employees.size(); i++) {
            employeeListView.getItems().add(employees.get(i));
        }
    }

    /**
     * Gets mainPane from the GUI and initializes the ListView
     * @return VBox mainPane
     */
    public VBox getMainPane(){
        initializeListView();
        return mainPane;
    }

    /**
     * List Listener to the changes
     */
    private class MyListListener implements ChangeListener<Employee>
    {
        /**
         * Method what happens when any changes in the ListView occure
         * @param employee employee object as the observable value
         * @param oldEmployee Employee type previous employee who was being clicked at
         * @param newEmployee Employee type new employee who was being clicked at
         */
        public void changed(ObservableValue<? extends Employee> employee, Employee oldEmployee, Employee newEmployee)
        {

            Employee temp = employeeListView.getSelectionModel().getSelectedItem();
            //employee's index in the EmployeeList but the first one is invalid
            int index = employeeListView.getSelectionModel().getSelectedIndex()-1;

            if (temp != null)
            {
                Employee selectedEmployee = employeeAdapter.getSelectedEmployee(index);
                firstNameField.setText(selectedEmployee.getFirstName());
                lastNameField.setText(selectedEmployee.getLastName());
                dayField.setText(Integer.toString(selectedEmployee.getDayOfBirth()));
                monthField.setText(Integer.toString(selectedEmployee.getMonthOfBirth()));
                yearField.setText(Integer.toString(selectedEmployee.getYearOfBirth()));
            }
        }
    }
}