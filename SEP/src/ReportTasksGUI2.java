import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReportTasksGUI2 {

    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox hBoxPaneButton;

    private Label employeeName;

    private TableView allAssignedTasksTable;
    private TableColumn projectNameColumn;
    private TableColumn requirementIDColumn;
    private TableColumn taskIDColumn;;
    private TableColumn dateColumn;

    private Button goBack;
    private Button continueButton;


    public ReportTasksGUI2(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        employeeName = new Label();
        employeeName.getStyleClass().add("heading");

        topPane = new HBox(500);
        topPane.getChildren().addAll(employeeName);

        allAssignedTasksTable = new TableView();
        allAssignedTasksTable.setPrefHeight(290);
        allAssignedTasksTable.setTableMenuButtonVisible(true);

        projectNameColumn = new TableColumn("Project Name");
        projectNameColumn.setPrefWidth(500);

        requirementIDColumn = new TableColumn("Requirement ID");
        requirementIDColumn.setPrefWidth(150);

        taskIDColumn = new TableColumn("Task ID");
        taskIDColumn.setPrefWidth(150);

        dateColumn = new TableColumn("Date");
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
        continueButton = new Button("Continue");

        hBoxPaneButton = new HBox();
        hBoxPaneButton.getChildren().addAll(continueButton, goBack);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, allAssignedTasksTable, hBoxPaneButton);
    }

    public VBox getMainPane()
    {
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