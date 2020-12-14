import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
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

    private ComboBox statusBox;
    private FlowPane comboPane;

    private TableView<AssignedTasks> allAssignedTasksTable;
    private AssignedTasksAdapter assignedTasksAdapter;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;;
    private TableColumn dateColumn;

    private Button goBack;

    public ViewAssignedTasksGUI2(SepGUI sepGUI,AssignedTasksAdapter assignedTasksAdapter){

        this.assignedTasksAdapter = assignedTasksAdapter;
        this.sepGUI = sepGUI;
        employeeName = new Label();
        employeeName.getStyleClass().add("heading");
        employeeName.setPrefWidth(700);

        tableLabel = new Label("List of Assigned Tasks");

        statusBox = new ComboBox<String>();
        statusBox.getItems().addAll(
                "All upcoming",
                "All"
        );

        comboPane = new FlowPane();
        comboPane.setAlignment(Pos.BASELINE_RIGHT);
        comboPane.setAlignment(Pos.BOTTOM_CENTER);
        comboPane.setPrefWidth(200);
        comboPane.getChildren().add(statusBox);

        topPane = new HBox(300);
        topPane.getChildren().addAll(employeeName, comboPane);

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn<AssignedTasks, String>("Project Name");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        projectNameColumn.setPrefWidth(500);

        requirementIDColumn = new TableColumn<AssignedTasks, Integer>("Requirement ID");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory("id"));
        requirementIDColumn.setPrefWidth(150);

        taskIDColumn = new TableColumn<AssignedTasks, Integer>("Task ID");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory("id"));
        taskIDColumn.setPrefWidth(150);

        dateColumn = new TableColumn<AssignedTasks, MyDate>("Date");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory("date"));
        dateColumn.setPrefWidth(200);

        projectNameColumn.setReorderable(false);
        requirementIDColumn.setReorderable(false);
        taskIDColumn.setReorderable(false);
        dateColumn.setReorderable(false);

        allAssignedTasksTable.getColumns().add(projectNameColumn);
        allAssignedTasksTable.getColumns().add(requirementIDColumn);
        allAssignedTasksTable.getColumns().add(taskIDColumn);
        allAssignedTasksTable.getColumns().add(dateColumn);

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
            System.out.println(assignedTasksList.get(i));
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