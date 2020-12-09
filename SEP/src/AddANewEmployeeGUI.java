import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

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

    public AddANewEmployeeGUI(EmployeeAdapter employeeAdapter) {
        this.employeeAdapter = employeeAdapter;

        listener = new MyActionListener();

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
        addButton.setOnAction(listener);


        mainPane.getChildren().addAll(titleLabel, informationPane, addButton);
    }
    public VBox getMainPane(){
        return mainPane;
    }

    private class MyActionListener implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if(e.getSource() == addButton){
                boolean allValuesCorrect = true;
                MyDate dateOfBirth = new MyDate(Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()));
                if(nameField.getText() == null /*|| nameField.getText().trim().isEmpty()*/){
                    JOptionPane.showMessageDialog(null, "First name cannot be empty!",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                    allValuesCorrect = false;
                }
                else if(lastNameField.getText() == null /*|| nameField.getText().trim().isEmpty()*/){
                    JOptionPane.showMessageDialog(null, "Last name cannot be empty!",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                    allValuesCorrect = false;
                }
                else if(!dateOfBirth.is15Years()){
                    JOptionPane.showMessageDialog(null, "Employee has to be at least 15 years old!",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                    allValuesCorrect = false;
                }
                else if(!dateOfBirth.isValidDate()){
                    JOptionPane.showMessageDialog(null, "Entered date is not valid!",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                    allValuesCorrect = false;
                }
                if(allValuesCorrect == true){
                    EmployeeList employeeList = employeeAdapter.getAllEmployees();
                    employeeList.addEmployee(new Employee(nameField.getText(), lastNameField.getText(), dateOfBirth));
                    employeeAdapter.saveEmployees(employeeList);
                    JOptionPane.showMessageDialog(null, "New employee was successfully added!",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }



}