import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of tasks.
 * @author Timothy Engkar, Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class AssignTasksGUI5 {

    private AssignedTasksAdapter assignedTasksAdapter;
    private ProjectsAdapter projectsAdapter;
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
     * 3-argument constructor initializing the GUI components
     * @param projectsAdapter adapter of the projects, requirements and tasks
     * @param assignedTasksAdapter adapter of the assigned tasks
     * @param sepGUI the main GUI where all the other GUIs are connected
     */
    public AssignTasksGUI5(SepGUI sepGUI,AssignedTasksAdapter assignedTasksAdapter, ProjectsAdapter projectsAdapter){

        this.sepGUI = sepGUI;
        this.projectsAdapter = projectsAdapter;
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
        informationPane.setVgap(8);

        assignButton = new Button("Assign");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(assignButton, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(titleLabel, taskLabel, employeeLabel, informationPane, bottomButtons);
    }

    /**
     * Gets the mainPane
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        return mainPane;
    }

    /**
     * Gets the goBackButton Button
     * @return Button goBackButton
     */
    public Button getGoBackButton()
    {
        return goBackButton;
    }

    /**
     * Checks the validity of entered data after the continue Button is called
     * @return boolean true if all the input data is correct, otherwise false
     */
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
            String employeeName = sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getFirstName() + " "+
                sepGUI.getAssignTasksGUI4().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getLastName();
            employeeLabel.setText(employeeName);

            gogo =true;
        }

        return gogo;
    }

    /**
     * Gets the assignButton Button
     * @return Button assignButton
     */
    public Button getAssignButton()
    {
        return assignButton;
    }

    /**
     * Checks the validity of entered data after the assign Button is called
     * @return boolean true if all the input data is correct, otherwise false
     */
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
                Project selectedProject = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem();
                Requirement selectedRequirement = (Requirement) sepGUI.getAssignTasksGUI2().getRequirementTable().getSelectionModel().getSelectedItem();
                Task selectedTask = sepGUI.getAssignTasksGUI3().getTasksTable().getSelectionModel().getSelectedItem();
                AssignedTasksList assignedTaskList = assignedTasksAdapter.getAllAssignedTasks();
                for (int i = 0; i < assignedTaskList.size(); i++)
                {
                    if(assignedTaskList.get(i).getAssignedEmployee().equals(assignedEmployee) &&
                    assignedTaskList.get(i).getDate().equals(dateForTheTask))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Invalid selection");
                        alert.setContentText("Employee is already assigned to this task on " + dateForTheTask);
                        alert.showAndWait();
                        allValuesCorrect = false;
                        break;
                    }
                }
                if(allValuesCorrect ){
                    Project project = projectsAdapter.getSelectedProject(selectedProject.getName());
                    Requirement requirement = projectsAdapter.getSelectedRequirement(
                        project.getName(), selectedRequirement.getId());
                    Task task = projectsAdapter.getSelectedTask(
                        project.getName(), requirement.getId(), selectedTask.getId());
                AssignedTasks assignedTask = new AssignedTasks(task.getName(),
                    task.getDescription(), task.getDeadline(), task.getEstimatedTime(),
                    task.getResponsibleEmployee(), assignedEmployee,
                    dateForTheTask, task.getId(), task.getStatus());
                assignedTask.setRequirement(requirement);
                assignedTask.setProject(project);

                assignedTaskList.addAssignedTask(assignedTask);
                assignedTasksAdapter.saveAssignedTasks(assignedTaskList);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setHeaderText("Success");
                    alert2.setContentText("The task was successfully added!");
                    alert2.showAndWait();

                allValuesCorrect = true;
            }
        }

       }
        return allValuesCorrect;
    }

    /**
     * Clears all the fields, sets the textFields to empty
     */
    public void clearFields()
    {
        dayField.setText("");
        monthField.setText("");
        yearField.setText("");
    }
}