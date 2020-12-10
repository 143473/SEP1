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

    private ListView<Employee> studentListView;
    private FlowPane listPane;

    private Button saveButton;
    private Button removeButton;
    private HBox buttonsPane;
    private EmployeeAdapter employeeAdapter;
    private MyActionListener listener;
    private MyListListener listListener;

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
        dayField.setMaxWidth(140);
        monthField = new TextField();
        monthField.setPromptText("mm");
        monthField.setMaxWidth(40);
        yearField = new TextField();
        yearField.setPromptText("yyyy");
        yearField.setMaxWidth(60);

        birthdayPane = new HBox(5);
        birthdayPane.getChildren().addAll(dayField);

        firstNameField = new TextField();
        firstNameField.setEditable(false);
        lastNameField = new TextField();
        lastNameField.setEditable(false);

        informationPane = new GridPane();
        informationPane.setHgap(5);
        informationPane.setVgap(5);
        informationPane.addRow(0, firstNameLabel, firstNameField);
        informationPane.addRow(1, lastNameLabel, lastNameField);
        informationPane.addRow(2, birthdayLabel, birthdayPane);

        studentListView = new ListView<Employee>();
        studentListView.setPrefHeight(120);
        studentListView.getSelectionModel().selectedItemProperty().addListener((listListener));

        listPane = new FlowPane();
        listPane.setAlignment(Pos.BASELINE_RIGHT);
        listPane.setPrefWidth(200);
        listPane.getChildren().add(studentListView);

        employeePane = new HBox();
        employeePane.getChildren().addAll(informationPane, listPane);

        saveButton = new Button("Save");
        removeButton = new Button("Remove");

        buttonsPane = new HBox(15);
        buttonsPane.getChildren().addAll(saveButton, removeButton);

        mainPane = new VBox();
        mainPane.getChildren().addAll(titleLabel, employeePane, buttonsPane);
    }
    public void updateStudentListView()
    {
        int currentIndex = studentListView.getSelectionModel().getSelectedIndex();

        studentListView.getItems().clear();

        EmployeeList students = employeeAdapter.getAllEmployees();
        for (int i = 0; i < students.size(); i++)
        {
            studentListView.getItems().add(students.get(i));
        }

        if (currentIndex == -1 && studentListView.getItems().size() > 0)
        {
            studentListView.getSelectionModel().select(0);
        }
        else
        {
            studentListView.getSelectionModel().select(currentIndex);
        }
    }
    private class MyActionListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {
            if (e.getSource() == saveButton)
            {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                MyDate day = new MyDate(Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()));
                //employee's index in the EmployeeList
                int index = studentListView.getSelectionModel().getSelectedIndex();

                employeeAdapter.saveChangedEmployee(firstName, lastName, day, index);
                updateStudentListView();
                dayField.setText("");
                JOptionPane.showMessageDialog(null, "Changes were saved successfully!",
                        "Editing successful", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }
    private class MyListListener implements ChangeListener<Employee>
    {
        public void changed(ObservableValue<? extends Employee> student, Employee oldStudent, Employee newStudent)
        {
            Employee temp = studentListView.getSelectionModel().getSelectedItem();

            if (temp != null)
            {
                firstNameField.setText(temp.getFirstName());
                lastNameField.setText(temp.getLastName());
                dayField.setPromptText(temp.toString());
            }
        }
    }
    public VBox getMainPane(){
        return mainPane;
    }

}