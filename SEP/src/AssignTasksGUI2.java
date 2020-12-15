import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A GUI tab containing components for displaying a list of requirements.
 * @author Timothy Engkar, Claudiu Emanuel Cordunianu
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
    private TableColumn requirementNameColumn;
    private TableColumn requirementDescriptionColumn;

    private Button continueButton;
    private Button goBackButton;


    /**
     * 2-argument constructor initializing the GUI components
     * @param projectsAdapter adapter of the projects, requirements and tasks
     * @param sepGUI the main GUI where all the other GUIs are connected
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

    /**
     * Gets the requirementTable
     * @return TableView requirementTable
     */
    public TableView getRequirementTable()
    {
        return requirementTable;
    }

    /**
     * Initializes the requirementTable with updated values
     */
    private void initializeTable()
    {
        requirementTable.getItems().clear();
        Project selectedProject = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedProject.getRequirements().size(); i++)
        {
                requirementTable.getItems().add(selectedProject.getRequirements().get(i));
        }
    }

    /**
     * Gets the mainPane and initializes the requirementTable
     * @return VBox mainPane
     */
    public VBox getMainPane()
    {
        initializeTable();
        return mainPane;
    }

    /**
     * Gets the projectName Label
     * @return Label projectName
     */
    public Label getProjectName()
    {
        return projectName;
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