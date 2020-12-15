import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    private void initializeTable()
    {
        allAssignedTasksTable.getItems().clear();
        Employee employee = sepGUI.getReportTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem();
        AssignedTasksList assignedTasksList = assignedTasksAdapter.getAllTasksOnEmployee(employee);

        for (int i = 0; i < assignedTasksList.size(); i++)
        {
            if(!(assignedTasksList.get(i).getStatus().equals("Ended")))
            {
                allAssignedTasksTable.getItems().add(assignedTasksList.get(i));
            }
        }
    }
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    public Button getContinueButton()
    {
        return continueButton;
    }

    public Button getGoBack()
    {
        return goBack;
    }

    public TableView<AssignedTasks> getAllAssignedTasksTable()
    {
        return allAssignedTasksTable;
    }

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