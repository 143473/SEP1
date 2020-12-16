import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
  private HBox projectNamePane;
  private HBox bottomButtons;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button continueButton;
  private Button goBackButton;

  private TextField search;

  private Label projectLabel;
  private Text projectName;
  private Label tableTitle;
  private Label searchLabel;

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
    projectLabel = new Label("Project: ");
    projectLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projectName = new Text();
    projectName.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 20));

    requirementsTable = new TableView<Requirement>();

    projectNamePane = new HBox(5);
    projectNamePane.getChildren().addAll(projectLabel, projectName);

    continueButton = new Button("Continue");
    goBackButton = new Button("Go Back");

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(continueButton, goBackButton);

    tableTitle = new Label("Choose a requirement from the list");

    searchLabel = new Label("Search for a requirement");
    search = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(add,manage);

    idColumn = new TableColumn<Requirement, Integer>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory("id"));
    nameColumn = new TableColumn<Requirement, String>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    userStoryColumn = new TableColumn<Requirement, String>("User Story");
    userStoryColumn.setCellValueFactory(new PropertyValueFactory("userStory"));
    estimationColumn = new TableColumn<Requirement, Integer>("Estimated Time");
    estimationColumn.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
    importanceColumn = new TableColumn<Requirement, Integer>("Importance");
    importanceColumn.setCellValueFactory(new PropertyValueFactory("importance"));
    deadlineColumn = new TableColumn<Requirement, MyDate>("Deadline");
    deadlineColumn.setCellValueFactory(new PropertyValueFactory("deadline"));
    statusColumn = new TableColumn<Requirement, String>("Status");
    statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
    totalHoursColumn = new TableColumn("Spent time");
    totalHoursColumn.setCellValueFactory(new PropertyValueFactory("spentTime"));
    responsibleColumn = new TableColumn<Requirement, AssignedEmployee>("Responsible Employee");
    responsibleColumn.setCellValueFactory(new PropertyValueFactory("responsibleEmployee"));

    requirementsTable.getColumns().setAll(idColumn, nameColumn, userStoryColumn, statusColumn, estimationColumn, importanceColumn, deadlineColumn, totalHoursColumn, responsibleColumn);
    requirementsTable.setPrefWidth(450);
    requirementsTable.setPrefHeight(300);
    requirementsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));
    mainPane.getChildren().addAll(projectNamePane, searchPane,topButtons, tableTitle, requirementsTable, bottomButtons);


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
  public Text getProjectName()
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
