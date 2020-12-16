import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
  private HBox projectRequirementPane;
  private HBox allBottomButtons;
  private HBox topPane;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button goBackButton;

  private TextField search;

  private Label projectName;
  private Label requirementName;
  private Label tableTitle;

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

    projectName = new Label();
    projectName.getStyleClass().add("heading");
    projectName.prefWidth(300);
    requirementName = new Label();
    requirementName.getStyleClass().add("heading");
    requirementName.prefWidth(300);

    table = new TableView();

    goBackButton = new Button("Go Back");

    projectRequirementPane = new HBox(8);
    projectRequirementPane.getChildren().addAll(projectName, requirementName);

    tableTitle = new Label("List of tasks");

    search = new TextField();
    search.setPromptText("Search by name");
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
    searchPane = new HBox(8);
    searchPane.getChildren().addAll(search,searchButton);

    topPane = new HBox(430);
    topPane.getChildren().addAll(projectRequirementPane,searchPane);

    topButtons = new HBox(8);
    topButtons.getChildren().addAll(add,manage);

    allBottomButtons = new HBox(810);
    allBottomButtons.getChildren().addAll(topButtons, goBackButton);

    TableColumn idCol = new TableColumn<Task, Integer>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory("id"));
    idCol.setPrefWidth(50);
    TableColumn nameCol = new TableColumn<Task, String>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    nameCol.setPrefWidth(100);
    TableColumn estimationCol = new TableColumn<Task, Integer>("Estimation");
    estimationCol.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
    estimationCol.setPrefWidth(100);
    TableColumn deadlineCol = new TableColumn<Task, Integer>("Deadline");
    deadlineCol.setCellValueFactory(new PropertyValueFactory("deadline"));
    deadlineCol.setPrefWidth(100);
    TableColumn statusCol = new TableColumn<Task, String>("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    statusCol.setPrefWidth(70);
    TableColumn totalhrsCol = new TableColumn<Task, String>("Spent Time");
    totalhrsCol.setCellValueFactory(new PropertyValueFactory("spentTime"));
    totalhrsCol.setPrefWidth(100);
    TableColumn responsibleCol = new TableColumn<Task, AssignedEmployee>("Responsible Employee");
    responsibleCol.setCellValueFactory(new PropertyValueFactory("responsibleEmployee"));

    table.getColumns().setAll(idCol, nameCol,statusCol,estimationCol,deadlineCol,totalhrsCol,responsibleCol);
    table.setPrefHeight(290);


    mainPane = new VBox(8);
    mainPane.getChildren().addAll(topPane,tableTitle, table,allBottomButtons);
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
      alert.setContentText("No task was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      projectName.setText(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName()+"\\");
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

