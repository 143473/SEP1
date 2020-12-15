import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Class for the overview and managing the tasks of the selected requirements of selected project
 * @author Claudiu Emanuel Cordunianu, Timothy Johan Engkar, Bartosz Juliusz Ochedzan, Marketa Lapcikova
 * @version 1.0
 */
public class TasksOfReqOfPrjGUI
{
  private MyActionListener listener;

  private VBox mainPane;
  private HBox searchPane;
  private HBox topButtons;
  private GridPane projectRequirementPane;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button goBackButton;

  private TextField search;

  private Label projectName;
  private Label requirementName;
  private Label projectLabel;
  private Label requirementLabel;
  private Label tableTitle;
  private Label searchLabel;

  private TableView table;

  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private Project selectedProject;
  private Requirement selectedRequirement;

  /**
   * 2-argument constructor initializing the GUI components
   * @param sepGUI the main GUI where all the other GUIs are connected
   * @param projectsAdapter adapter of the projects, requirements and tasks
   */
  public TasksOfReqOfPrjGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listener = new MyActionListener();
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    add = new Button("Add");
    manage = new Button("Manage");

    projectLabel = new Label("Project:");
    projectLabel.getStyleClass().add("heading");
    projectName = new Label();
    requirementLabel = new Label("Requirement:");
    requirementLabel.getStyleClass().add("heading");
    requirementName = new Label();

    table = new TableView();

    goBackButton = new Button("Go Back");

    projectRequirementPane = new GridPane();
    projectRequirementPane.setHgap(5);
    projectRequirementPane.addRow(0,projectLabel, projectName);
    projectRequirementPane.addRow(1,requirementLabel,requirementName);

    tableTitle = new Label("List of tasks");

    searchLabel = new Label("Search for a task: ");
    search = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(add,manage);

    TableColumn idCol = new TableColumn<Task, Integer>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory("id"));
    TableColumn nameCol = new TableColumn<Task, String>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn estimationCol = new TableColumn<Task, Integer>("Estimation");
    estimationCol.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
    TableColumn deadlineCol = new TableColumn<Task, Integer>("Deadline");
    deadlineCol.setCellValueFactory(new PropertyValueFactory("deadline"));
    TableColumn statusCol = new TableColumn<Task, String>("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    TableColumn totalhrsCol = new TableColumn<Task, String>("Spent Time");
    totalhrsCol.setCellValueFactory(new PropertyValueFactory("spentTime"));
    TableColumn responsibleCol = new TableColumn<Task, AssignedEmployee>("Responsible Employee");
    responsibleCol.setCellValueFactory(new PropertyValueFactory("responsibleEmployee"));

    table.getColumns().setAll(idCol, nameCol,estimationCol,deadlineCol,statusCol,totalhrsCol,responsibleCol);
    table.setPrefWidth(450);
    table.setPrefHeight(300);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.getChildren().addAll(projectRequirementPane,searchPane,topButtons,tableTitle, table,goBackButton);
  }

  /**
   * Gets the mainPane and initializes the table
   * @return
   */
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
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
   * Checks the validity of entered data after the continue Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem()== null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No requirement was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      projectName.setText(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName());
      requirementName.setText(sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem().toString());
      selectedProject = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
      selectedRequirement= sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
      gogo =true;
    }
    return gogo;
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
        Requirement requirement = sepGUI.getReqOfSelectedPrjGUI().getRequirementsTable().getSelectionModel().getSelectedItem();
        ArrayList<Task> tasks = requirement.getTasks();
        ArrayList<Task> chosenTasks = projectsAdapter.getTasksByName(searchingFor, tasks);
        initializeTable(chosenTasks);
      }
    }
  }

  /**
   * Initializes the table
   */
  private void initializeTable()
  {
    table.getItems().clear();
    if(selectedProject != null && selectedRequirement != null){
      ArrayList<Task> tasks = projectsAdapter.getAllTasks(selectedProject.getName(), selectedRequirement.getId());
      for (int i = 0; i < tasks.size(); i++) {
        table.getItems().add(tasks.get(i));
      }
    }
  }

  /**
   * Initializes the table with new tasks
   * @param newTasks new tasks for the table to be initialized with
   */
  private void initializeTable(ArrayList<Task> newTasks){
    table.getItems().clear();
    for (int i = 0; i < newTasks.size(); i++) {
      table.getItems().add(newTasks.get(i));
    }
  }
}

