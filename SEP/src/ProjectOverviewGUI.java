import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for managing the requirement information
 * @author Marketa Lapcikova, Claudiu Emanuel Cordunianu
 * @version 1.0
 */
public class ProjectOverviewGUI
{
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private MyActionListener listener;

  private Button add;
  private Button manage;
  private Button continueButton;
  private Button searchButton;

  private TextField search;

  private Label title;
  private Label tableTitle;

  private TableView<Project> projectsTable;
  private TableColumn nameCol;
  private TableColumn descriptionCol;
  private TableColumn statusCol;
  private TableColumn teamMemberCol;

  private VBox mainPane;
  private HBox topPane;
  private HBox searchPane;
  private HBox topButtons;
  private HBox bottomButtons;

  /**
   * 2-argument constructor initializing the GUI components
   * @param projectsAdapter adapter of the projects, requirements and tasks
   * @param sepGUI the main GUI where all the other GUIs are connected
   */
  public ProjectOverviewGUI(ProjectsAdapter projectsAdapter,SepGUI sepGUI){
    this.projectsAdapter = projectsAdapter;

    listener = new MyActionListener();

    add = new Button("Add");
    manage = new Button("Manage");
    continueButton = new Button("Continue");

    title = new Label("Project Overview");
    title.getStyleClass().add("heading");

    search = new TextField();
    search.setPromptText("Search by name");
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);

    tableTitle = new Label("Choose a project from the list");

    projectsTable = new TableView();

    searchPane = new HBox(8);
    searchPane.getChildren().addAll(search,searchButton);

    topPane = new HBox(543);
    topPane.getChildren().addAll(title,searchPane);

    topButtons = new HBox(8);
    topButtons.getChildren().addAll(add, manage);

    bottomButtons = new HBox(800);
    bottomButtons.getChildren().addAll(topButtons, continueButton);

    nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    nameCol.setPrefWidth(200);

    descriptionCol = new TableColumn("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
    descriptionCol.setPrefWidth(300);

    statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    statusCol.setPrefWidth(70);

    teamMemberCol = new TableColumn("Team Members");
    teamMemberCol.setCellValueFactory(new PropertyValueFactory("assignedEmployeeList"));
    teamMemberCol.setPrefWidth(375);


    projectsTable.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    projectsTable.setPrefHeight(290);

    mainPane = new VBox(8);
    mainPane.getChildren().addAll(topPane,tableTitle, projectsTable,bottomButtons);
  }

  /**
   * Initializes the projectsTable and updates the values
   */
  private void initializeTable(){
    projectsTable.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();
    for (int i = 0; i < projects.size(); i++) {
      projectsTable.getItems().add(projects.get(i));
    }
  }

  /**
   * Initializes the projectsTable with a new ProjectList
   * @param newProjects ProjectList of new projects to initialize the projectsTable with
   */
  private void initializeTable(ProjectList newProjects){
    projectsTable.getItems().clear();
    for (int i = 0; i < newProjects.size(); i++) {
      projectsTable.getItems().add(newProjects.get(i));
    }
  }

  /**
   * Initializes the projectsTable and gets the mainPane
   * @return VBox mainPane
   */
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
  }

  /**
   * Gets the projectsTable
   * @return TableView<Project> projectsTable
   */
  public TableView<Project> getProjectsTable()
  {
    return projectsTable;
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
   * Gets the add Button
   * @return Button add
   */
  public Button getAdd()
  {
    return add;
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
        ProjectList projects = projectsAdapter.getAllProjects();
        ProjectList chosenProjects = projectsAdapter.getProjectsByName(searchingFor, projects);
        initializeTable(chosenProjects);
      }
    }
  }
}
