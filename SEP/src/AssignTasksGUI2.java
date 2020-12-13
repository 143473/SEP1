import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of requirements.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI2 {
    private SepGUI sepGUI;
    private ProjectsAdapter projectsAdapter;
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label projectName;
    private Label tableLabel;

    private TableView<Requirement> requirementTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn requirementNameColumn;
    private TableColumn requirementDescriptionColumn;

    private Button continueButton;
    private Button goBackButton;


    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI2(ProjectsAdapter projectsAdapter,SepGUI sepGUI){

        this.sepGUI = sepGUI;
        this.projectsAdapter = projectsAdapter;
        projectName = new Label();
        projectName.getStyleClass().add("heading");

        topPane = new HBox(8);
        topPane.getChildren().addAll(projectName);

        tableLabel = new Label("Choose a requirement from the list");

        requirementTable = new TableView();
        requirementTable.setPrefHeight(290);
        requirementTable.setTableMenuButtonVisible(true);

        requirementNameColumn = new TableColumn("Name");
        requirementNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        requirementNameColumn.setPrefWidth(460);
        requirementDescriptionColumn = new TableColumn("User Story");
        requirementDescriptionColumn.setCellValueFactory(new PropertyValueFactory("userStory"));
        requirementDescriptionColumn.setPrefWidth(500);

        requirementTable.getColumns().add(requirementNameColumn);
        requirementTable.getColumns().add(requirementDescriptionColumn);

        continueButton = new Button("Continue");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(8);
        bottomButtons.getChildren().addAll(continueButton, goBackButton);

        mainPane = new VBox(8);
        mainPane.getChildren().addAll(topPane, tableLabel, requirementTable, bottomButtons);
    }

    public TableView getRequirementTable()
    {
        return requirementTable;
    }
    private void initializeTable()
    {
        requirementTable.getItems().clear();
        Project selectedProject = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedProject.getRequirements().size(); i++)
        {
            requirementTable.getItems().add(selectedProject.getRequirements().get(i));
        }
    }

    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    public Label getProjectName()
    {
        return projectName;
    }

    public Button getContinueButton()
    {
        return continueButton;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
    public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      String projectNameString = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem().getName();
      projectName.setText(projectNameString+"\\");
      gogo =true;
    }
    return gogo;
  }
}