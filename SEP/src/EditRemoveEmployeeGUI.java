import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

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
    private EmployeeAdapter employeeAdapter;

    private MyActionListener listener;
    private MyListListener listListener;

    /**
     *  1-argument constructor initializing all the parts of the GUI
     * @param employeeAdapter adapter of this class
     */
    public EditRemoveEmployeeGUI(EmployeeAdapter employeeAdapter) {
        this.employeeAdapter = employeeAdapter;
        listener = new MyActionListener();
        listListener = new MyListListener();

        titleLabel = new Label("Edit or Remove Employee");
        titleLabel.setFont(new Font("Cambria", 32));


        firstNameLabel = new Label("First name:");
        firstNameLabel.setPadding(new Insets(10, 0, 5, 10));
        firstNameField = new TextField();
        firstNameField.setMaxWidth(100);

        lastNameLabel = new Label("Last name:");
        lastNameLabel.setPadding(new Insets(10, 0, 5, 10));
        lastNameField = new TextField();
        lastNameField.setMaxWidth(100);

        birthdayLabel = new Label("Birthday:");
        birthdayLabel.setPadding(new Insets(10, 0, 5, 10));

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
        informationPane.setHgap(5);
        informationPane.setVgap(5);
        informationPane.addRow(0, firstNameLabel, firstNameField);
        informationPane.addRow(1, lastNameLabel, lastNameField);
        informationPane.addRow(2, birthdayLabel, birthdayPane);

        employeeListView = new ListView<Employee>();
        employeeListView.setPrefHeight(120);
        employeeListView.getSelectionModel().selectedItemProperty().addListener((listListener));

        listPane = new FlowPane();
        listPane.setAlignment(Pos.BASELINE_RIGHT);
        listPane.setPrefWidth(200);
        listPane.getChildren().add(employeeListView);

        employeePane = new HBox();
        employeePane.getChildren().addAll(informationPane, listPane);

        saveButton = new Button("Save");
        saveButton.setOnAction(listener);
        removeButton = new Button("Remove");
        removeButton.setOnAction(listener);

        buttonsPane = new HBox(15);
        buttonsPane.getChildren().addAll(saveButton, removeButton);

        mainPane = new VBox();
        mainPane.getChildren().addAll(titleLabel, employeePane, buttonsPane);
    }

    /**
     * Handles the actions in this class
     */
    private class MyActionListener implements EventHandler<ActionEvent>
    {
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
                if(!employeeListView.getSelectionModel().getSelectedItem().equals("-")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Do you really want to delete employee " + firstNameField.getText() + " " + lastNameField.getText() + " (" + dayField.getText() + "/" + monthField.getText() + "/" + yearField.getText() + ")?",
                            ButtonType.YES,
                            ButtonType.NO);
                    alert.setTitle("Delete employee");
                    alert.setHeaderText(null);

                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                        employeeAdapter.deleteEmployee(index);
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Editing successful");
                        alert2.setContentText("Changes were saved successfully!");
                        alert2.showAndWait();
                        initializeListView();
                        //clear fields
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
    public void initializeListView(){
        employeeListView.getItems().clear();
        EmployeeList employees = employeeAdapter.getAllEmployees();
        employeeListView.getItems().add(null);
        for (int i = 0; i < employees.size(); i++) {
            employeeListView.getItems().add(employees.get(i));
        }
    }
    public VBox getMainPane(){
        initializeListView();
        return mainPane;
    }

    private class MyListListener implements ChangeListener<Employee>
    {
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