import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class ReportTasksGUI3 {
    private ProjectList projectList;
    private AssignedTasksAdapter assignedTasksAdapter;
    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;
    private GridPane informationPane;

    private Label titleLabel;
    private Label timeSpentLabel;

    private TextField timeSpentField;
    private CheckBox finishedField;

    private Button reportButton;
    private Button goBackButton;

    public ReportTasksGUI3(SepGUI sepGUI, AssignedTasksAdapter assignedTasksAdapter){

        this.sepGUI = sepGUI;
        this.assignedTasksAdapter = assignedTasksAdapter;
        titleLabel = new Label("Report a Tasks");
        titleLabel.getStyleClass().add("heading");

        topPane = new HBox(500);
        topPane.getChildren().addAll(titleLabel);

        timeSpentLabel = new Label("Number of hours:");
        timeSpentField = new TextField();
        timeSpentField.setPromptText("hh:hh");
        timeSpentField.setMaxWidth(60);

        finishedField = new CheckBox("finished");

        informationPane = new GridPane();
        informationPane.add(timeSpentLabel, 0, 0);
        informationPane.add(timeSpentField, 1, 0);
        informationPane.add(finishedField, 0, 1);
        informationPane.setVgap(10);

        reportButton = new Button("Report");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(5);
        bottomButtons.getChildren().addAll(reportButton,goBackButton);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(titleLabel, informationPane, bottomButtons);
    }

    public void setProjectList(ProjectList projectList){
        this.projectList = projectList;
    }
    public boolean callReportButton(){
        boolean allValuesCorrect = true;
    if(timeSpentField.getText().equals("") ||timeSpentField.getText().trim().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Invalid input");
        alert.setContentText("Time spent cannot be empty!");
        alert.showAndWait();
        allValuesCorrect = false;
    }
    else
    {
        try
        {
            double timeSpentTemporary = Double.parseDouble(timeSpentField.getText());
        }
        catch (NumberFormatException nfe)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Value in time spent has to be a number!");
            alert.showAndWait();
            allValuesCorrect = false;
        }
    }
    if(allValuesCorrect)
    {
        AssignedTasks assignedTasks = sepGUI.getReportTasksGUI2().getAllAssignedTasksTable().getSelectionModel().getSelectedItem();
        AssignedTasksList assignedTasksList = assignedTasksAdapter.getAllAssignedTasks();
        assignedTasksList.removeAssignedTask(assignedTasks);
        double spentTime = Double.parseDouble(timeSpentField.getText().replaceFirst("^0+(?!$)", ""));
        assignedTasks.setSpentTime(spentTime);
        if(finishedField.isSelected())
        {
            ProgressStatus progressStatus = new ProgressStatus();
            assignedTasks.setStatus(progressStatus.chooseStatus(2));
        }
        assignedTasksList.addAssignedTask(assignedTasks);
        allValuesCorrect = true;
    }
    return allValuesCorrect;
    }
    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
    public Button getReportButton(){
        return reportButton;
    }
}