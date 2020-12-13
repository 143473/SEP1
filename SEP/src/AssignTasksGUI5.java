import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of tasks.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI5 {

    private AssignedTasksAdapter assignedTasksAdapter;
    private SepGUI sepGUI;
    private VBox mainPane;

    private Label titleLabel;
    private Label taskLabel;
    private Label employeeLabel;

    private Label dayLabel;
    private TextField dayField;
    private TextField monthField;
    private TextField yearField;
    private HBox dayPane;

    private HBox bottomButtons;

    private GridPane informationPane;

    private Button assignButton;
    private Button goBackButton;


    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI5(SepGUI sepGUI,AssignedTasksAdapter assignedTasksAdapter){

        this.sepGUI = sepGUI;
        this.assignedTasksAdapter = assignedTasksAdapter;
        titleLabel = new Label("Assign a task");
        titleLabel.getStyleClass().add("heading");
        employeeLabel = new Label();
        employeeLabel.getStyleClass().add("heading");
        taskLabel = new Label();
        taskLabel.getStyleClass().add("heading");


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

        assignButton = new Button("Assign");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(assignButton, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(titleLabel, taskLabel, employeeLabel, informationPane, bottomButtons);
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
            String projectName = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem().getName();
            String requirementName = sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem().toString();
            String taskName = sepGUI.getAssignTasksGUI3().getTasksTable().getSelectionModel().getSelectedItem().getName();
            taskLabel.setText(projectName + "\\" + requirementName + "\\" + taskName);
            String employeeName = sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getFirstName() +
                sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getLastName();
            employeeLabel.setText(employeeName);
            gogo =true;
        }

        return gogo;
    }

    public Button getAssignButton()
    {
        return assignButton;
    }

    public boolean callAssignButton()
    {
        boolean allValuesCorrect = true;
        MyDate dateForTheTask;
        if (dayField.getText().isEmpty() || monthField.getText().isEmpty() || yearField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Date for the task cannot be empty!");
            alert.showAndWait();
            allValuesCorrect = false;
        }
        else
        {
            try
            {
                int temporary = Integer.parseInt(dayField.getText());
                temporary = Integer.parseInt(monthField.getText());
                temporary = Integer.parseInt(yearField.getText());
            }
            catch (NumberFormatException nfe)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid input");
                alert.setContentText(
                    "Values in date for the task have to be numbers!");
                alert.showAndWait();
                allValuesCorrect = false;
            }
        }
        if (allValuesCorrect)
        {
            dateForTheTask = new MyDate(Integer
                .parseInt(dayField.getText().replaceFirst("^0+(?!$)", "")),
                Integer.parseInt(
                    monthField.getText().replaceFirst("^0+(?!$)", "")), Integer
                .parseInt(yearField.getText().replaceFirst("^0+(?!$)", "")));
            if (!dateForTheTask.isValidDate())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid input");
                alert.setContentText("Entered date is not valid!");
                alert.showAndWait();
                allValuesCorrect = false;
            }
            else if (allValuesCorrect)
            {
                AssignedEmployee assignedEmployee = sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel()
                    .getSelectedItem();
                Task task = sepGUI.getAssignTasksGUI3().getTasksTable().getSelectionModel().getSelectedItem();
                AssignedTasksList assignedTaskList = assignedTasksAdapter.getAllAssignedTasks();
                AssignedTasks assignedTask = new AssignedTasks(task.getName(),
                    task.getDescription(), task.getDeadline(), task.getEstimatedTime(),
                    task.getResponsibleEmployee(), assignedEmployee,
                    dateForTheTask, task.getId(), task.getStatus());
                assignedTaskList.addAssignedTask(assignedTask);
                assignedTasksAdapter.saveAssignedTasks(assignedTaskList);
                        /*if(!employeeList.contains(newEmployee)){
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
                        }*/
                allValuesCorrect = true;
            }
        }
        return allValuesCorrect;
    }
}