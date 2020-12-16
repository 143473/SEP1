import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Class for the overview and managing the requirements of selected project
 * @author Claudiu Emanuel Cordunianu, Timothy Johan Engkar, Bartosz Juliusz Ochedzan, Marketa Lapcikova
 * @version 1.0
 */
public class ReqOfSelectedPrjGUI
{
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private VBox mainPane;
  private HBox searchPane;
  private HBox topButtons;
  private HBox bottomButtons;
  private HBox topPane;
  private HBox bottomTopButtons;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button continueButton;
  private Button goBackButton;

  private TextField search;

  private Label projectName;
  private Label tableTitle;

  private MyActionListener listener;

  private TableView<Requirement> requirementsTable;
  private TableColumn idColumn;
  private TableColumn nameColumn;
  private TableColumn userStoryColumn;
  private TableColumn estimationColumn;
  private TableColumn importanceColumn;
  private TableColumn deadlineColumn;
  private TableColumn statusColumn;
  private TableColumn totalHoursColumn;
  private TableColumn responsibleColumn;

  private Project selectedProject;

  /**
   * 2-argument constructor initializing the GUI components
   * @param sepGUI the main GUI where all the other GUIs are connected
   * @param projectsAdapter adapter of the projects, requirements and tasks
   */
  public ReqOfSelectedPrjGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listener = new MyActionListener();
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    add = new Button("Add");
    manage = new Button("Manage");
    search = new TextField();
    projectName = new Label();
    projectName.getStyleClass().add("heading");

    requirementsTable = new TableView<Requirement>();

    continueButton = new Button("Continue");
    goBackButton = new Button("Go Back");

    bottomButtons = new HBox(8);
    bottomButtons.getChildren().addAll(goBackButton, continueButton);

    tableTitle = new Label("Choose a requirement from the list");

    search = new TextField();
    search.setPromptText("Search by name");
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
    searchPane = new HBox(8);
    searchPane.getChildren().addAll(search,searchButton);

    topButtons = new HBox(8);
    topButtons.getChildren().addAll(add,manage);

    topPane = new HBox(650);
    topPane.getChildren().addAll(projectName,searchPane);

    idColumn = new TableColumn<Requirement, Integer>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory("id"));
    idColumn.setPrefWidth(50);
    nameColumn = new TableColumn<Requirement, String>("Name");
    nameColumn.setPrefWidth(100);
    nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    userStoryColumn = new TableColumn<Requirement, String>("User Story");
    userStoryColumn.setCellValueFactory(new PropertyValueFactory("userStory"));
    userStoryColumn.setPrefWidth(100);
    estimationColumn = new TableColumn<Requirement, Integer>("Estimated Time");
    estimationColumn.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
    estimationColumn.setPrefWidth(100);
    importanceColumn = new TableColumn<Requirement, Integer>("Importance");
    importanceColumn.setCellValueFactory(new PropertyValueFactory("importance"));
    importanceColumn.setPrefWidth(100);
    deadlineColumn = new TableColumn<Requirement, MyDate>("Deadline");
    deadlineColumn.setCellValueFactory(new PropertyValueFactory("deadline"));
    deadlineColumn.setPrefWidth(100);
    statusColumn = new TableColumn<Requirement, String>("Status");
    statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
    statusColumn.setPrefWidth(70);
    totalHoursColumn = new TableColumn("Spent time");
    totalHoursColumn.setCellValueFactory(new PropertyValueFactory("spentTime"));
    totalHoursColumn.setPrefWidth(100);
    responsibleColumn = new TableColumn<Requirement, AssignedEmployee>("Responsible Employee");
    responsibleColumn.setCellValueFactory(new PropertyValueFactory("responsibleEmployee"));

    requirementsTable.getColumns().setAll(idColumn, nameColumn, userStoryColumn, statusColumn, estimationColumn, importanceColumn, deadlineColumn, totalHoursColumn, responsibleColumn);
    requirementsTable.setPrefHeight(290);

    bottomTopButtons = new HBox(740);
    bottomTopButtons.getChildren().addAll(topButtons, bottomButtons);

    mainPane = new VBox(8);
    mainPane.getChildren().addAll(topPane, tableTitle, requirementsTable, bottomTopButtons);


  }

  /**
   * Gets the requirementsTable
   * @return TableView<Requirement> requirementsTable
   */
  public TableView<Requirement> getRequirementsTable()
  {
    return requirementsTable;
  }

  /**
   * Initializes the requirementsTable
   */
  public void initializeTable()
  {
    requirementsTable.getItems().clear();
    if(selectedProject != null){
      ArrayList<Requirement> requirements = selectedProject.getRequirements();
      for (int i = 0; i < requirements.size(); i++)
      {
        requirementsTable.getItems().add(requirements.get(i));
      }
    }
  }

  /**
   * Checks the validity of entered data after the continue Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem()== null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    if(gogo){
      if(projectsAdapter.getSelectedProject(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName()).getAssignedEmployeeList().size() == 0){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText("Chosen project does not have any assigned employees!\nYou have to assign employees before adding requirements!");
        alert.showAndWait();
        gogo = false;
      }
    }

    if(gogo)
    {
      projectName.setText(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName());
      selectedProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
    }
    return gogo;
  }

  /**
   * Gets the mainPane and initializes the requirementsTable
   * @return VBox mainPane
   */
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
  }

  /**
   * Initializes the requirementsTable with newRequirements data
   * @param newRequirements new requirements to be put into the requirementsTable
   */
  private void initializeTable(ArrayList<Requirement> newRequirements){
    requirementsTable.getItems().clear();
    for (int i = 0; i < newRequirements.size(); i++) {
      requirementsTable.getItems().add(newRequirements.get(i));
    }
  }

  /**
   * Gets the TableView<Requirement> requirementsTable
   * @return requirementsTable TableView<Requirement>
   */
  public TableView<Requirement> getTable()
  {
    return requirementsTable;
  }

  /**
   * Gets the projectName the name of the project
   * @return projectName
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
   * Gets the add Button
   * @return Button add
   */
  public Button getAdd()
  {
    return add;
  }

  /**
   * Gets the manage Button
   * @return Button manage
   */
  public Button getManage()
  {
    return manage;
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
   * Handles the actions in this class
   */
  private class MyActionListener implements EventHandler<ActionEvent> {
    /**
     * Handles the actions of this class
     * @param e event that happens
     */
    public void handle(ActionEvent e) {
      if (e.getSource() == searchButton)
      {
        String searchingFor = search.getText();
        Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
        ArrayList<Requirement> requirements = project.getRequirements();
        ArrayList<Requirement> chosenRequirements = projectsAdapter.getRequirementsByName(searchingFor, requirements);
        initializeTable(chosenRequirements);
      }
    }
  }
}
