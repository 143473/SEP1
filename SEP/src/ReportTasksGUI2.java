import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for reporting the assigned tasks
 * @author Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class ReportTasksGUI2 {

    private SepGUI sepGUI;
    private AssignedTasksAdapter assignedTasksAdapter;
    private VBox mainPane;
    private HBox hBoxPaneButton;

    private Label employeeName;
    private Label tableLabel;

    private TableView<AssignedTasks> allAssignedTasksTable;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;;
    private TableColumn dateColumn;

    private Button goBack;
    private Button continueButton;

    /**
     * 2-argument constructor initializing the GUI components
     * @param sepGUI the main GUI where all the other GUIs are connected
     * @param assignedTasksAdapter adapter of the assigned tasks
     */
    public ReportTasksGUI2(SepGUI sepGUI, AssignedTasksAdapter assignedTasksAdapter){

        this.assignedTasksAdapter = assignedTasksAdapter;
        this.sepGUI = sepGUI;
        employeeName = new Label();
        employeeName.getStyleClass().add("heading");

        tableLabel = new Label("List of Assigned Tasks");

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn<>("Project Name");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectNameColumn.setPrefWidth(500);

        requirementIDColumn = new TableColumn<>("Requirement ID");
        requirementIDColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        requirementIDColumn.setPrefWidth(150);

        taskIDColumn = new TableColumn<>("Task ID");
        taskIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskIDColumn.setPrefWidth(150);

        dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(200);

        allAssignedTasksTable.getColumns().add(projectNameColumn);
        allAssignedTasksTable.getColumns().add(requirementIDColumn);
        allAssignedTasksTable.getColumns().add(taskIDColumn);
        allAssignedTasksTable.getColumns().add(dateColumn);

        goBack = new Button("Go Back");
        continueButton = new Button("Continue");

        hBoxPaneButton = new HBox();
        hBoxPaneButton.getChildren().addAll(continueButton, goBack);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(employeeName, tableLabel, allAssignedTasksTable, hBoxPaneButton);
    }

    /**
     * Initializes the allAssignedTasksTable with the updated values
     */
    private void initializeTable()
    {
        allAssignedTasksTable.getItems().clear();
        Employee employee = sepGUI.getReportTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem();
        AssignedTasksList assignedTasksList = assignedTasksAdapter.getAllTasksOnEmployee(employee);

        for (int i = 0; i < assignedTasksList.size(); i++)
        {
            if(!(assignedTasksList.get(i).getStatus().equals("Ended"))
                && !assignedTasksList.get(i).getReported())
            {
                allAssignedTasksTable.getItems().add(assignedTasksList.get(i));
            }
        }
    }

    /**
     * Gets the mainPane and initializes the allAssignedTasksTable
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    /**
     * Gets the continueButton Button
     * @return Button continueButton
     */
    public Button getContinueButton()
    {
        return continueButton;
    }

    /**
     * Gets the goBack Button
     * @return Button goBack
     */
    public Button getGoBack()
    {
        return goBack;
    }

    /**
     * Gets allAssignedTasksTable TableView<AssignedTasks>
     * @return TableView<AssignedTasks> allAssignedTasksTable
     */
    public TableView<AssignedTasks> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
    }


    /**
     * Checks the validity of entered data after the continueButton2 is called
     * @return boolean true if all the input data is correct, otherwise false
     */
    public boolean callContinueButton2()
    {
        boolean gogo = true;
        if (allAssignedTasksTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No assigned task was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else gogo = true;
        return gogo;
    }

    /**
     * Checks the validity of entered data after the continueButton is called
     * @return boolean true if all the input data is correct, otherwise false
     */
    public boolean callContinueButton(){
        boolean gogo = true;
        if(sepGUI.getReportTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No employee was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else
        {
            String name = sepGUI.getReportTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getFirstName() + " "+
                sepGUI.getReportTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getLastName();
            employeeName.setText(name);
            gogo =true;
        }
        return gogo;
    }
}