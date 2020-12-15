import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class ViewAssignedTasksGUI2 {

    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox hBoxPaneButton;


    private Label employeeName;
    private Label tableLabel;

    private TableView<AssignedTasks> allAssignedTasksTable;
    private AssignedTasksAdapter assignedTasksAdapter;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;
    private TableColumn dateColumn;
    private TableColumn statusColumn;
    private TableColumn spentTimeColumn;
    private TableColumn estimateTimeColumn;
    private TableColumn deadLineColumn;

    private Button goBack;

    public ViewAssignedTasksGUI2(SepGUI sepGUI,AssignedTasksAdapter assignedTasksAdapter){

        this.assignedTasksAdapter = assignedTasksAdapter;
        this.sepGUI = sepGUI;
        employeeName = new Label();
        employeeName.getStyleClass().add("heading");
        employeeName.setPrefWidth(700);

        tableLabel = new Label("List of Assigned Tasks");

        topPane = new HBox(300);
        topPane.getChildren().addAll(employeeName);

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn<>("Project Name");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectNameColumn.setPrefWidth(150);

        requirementIDColumn = new TableColumn<>("Requirement ID");
        requirementIDColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        requirementIDColumn.setPrefWidth(100);

        taskIDColumn = new TableColumn<>("Task ID");
        taskIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskIDColumn.setPrefWidth(50);

        dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(150);

        estimateTimeColumn = new TableColumn<>("Estimated Time");
        estimateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        estimateTimeColumn.setPrefWidth(100);

        spentTimeColumn = new TableColumn<>("Spent Time");
        spentTimeColumn.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        spentTimeColumn.setPrefWidth(100);

        deadLineColumn = new TableColumn<>("Deadline");
        deadLineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        deadLineColumn.setPrefWidth(100);

        statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setPrefWidth(100);



        allAssignedTasksTable.getColumns().add(projectNameColumn);
        allAssignedTasksTable.getColumns().add(requirementIDColumn);
        allAssignedTasksTable.getColumns().add(taskIDColumn);
        allAssignedTasksTable.getColumns().add(dateColumn);
        allAssignedTasksTable.getColumns().add(estimateTimeColumn);
        allAssignedTasksTable.getColumns().add(spentTimeColumn);
        allAssignedTasksTable.getColumns().add(deadLineColumn);
        allAssignedTasksTable.getColumns().add(statusColumn);


        goBack = new Button("Go Back");

        hBoxPaneButton = new HBox();
        hBoxPaneButton.getChildren().addAll(goBack);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, tableLabel,allAssignedTasksTable, hBoxPaneButton);
    }

    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }
    private void initializeTable()
    {
        allAssignedTasksTable.getItems().clear();
        Employee employee = sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable()
            .getSelectionModel().getSelectedItem();
        AssignedTasksList assignedTasksList = assignedTasksAdapter.getAllTasksOnEmployee(employee);

        for (int i = 0; i < assignedTasksList.size(); i++)
        {
            allAssignedTasksTable.getItems().add(assignedTasksList.get(i));
        }
    }
    public Button getGoBack()
    {
        return goBack;
    }


    public boolean callContinueButton(){
        boolean gogo = true;
        if(sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem()== null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("No employee was chosen!");
            alert.showAndWait();
            gogo = false;
        }
        else
        {
            String name = sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getFirstName() + " "+
                sepGUI.getViewAssignedTasksGUI1().getAllAssignedTasksTable().getSelectionModel().getSelectedItem().getLastName();
            employeeName.setText(name);
            gogo =true;
        }
        return gogo;
    }
}