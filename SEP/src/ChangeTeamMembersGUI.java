
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *  A class handling changing the team members
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
public class ChangeTeamMembersGUI {

  private VBox mainPane;
  private HBox buttonsPane;

  private HBox searchPane;
  private VBox newWindowPane;

  private Label title;
  private Label searchByName;

  private Button addButton;
  private Button removeButton;
  private Button saveButton;
  private Button add;
  private Button searchButton;

  private TextField searchField;

  private ListView<Employee> employeeListView;
  private ListView<AssignedEmployee> teamMembersTable;

  private ProjectsAdapter projectsAdapter;
  private EmployeeAdapter employeeAdapter;
  private SepGUI sepGUI;

  private Project currentProject;
  private AssignedEmployeeList employeeList;

  private MyActionListener listener;

  /**
   *  2-argument constructor initializing all the parts of the GUI
   *
   */
  public ChangeTeamMembersGUI(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI)
  {

    this.projectsAdapter = projectsAdapter;
    this.employeeAdapter = employeeAdapter;
    this.sepGUI = sepGUI;
    listener = new MyActionListener();

    title = new Label();
    title.getStyleClass().add("heading");
    removeButton = new Button("Remove");
    removeButton.setOnAction(listener);
    saveButton = new Button("Save");
    addButton = new Button("Add Team Member");
    addButton.setOnAction(listener);

    employeeListView = new ListView<Employee>();
    employeeListView.setPrefHeight(120);

    searchByName = new Label("Search by name: ");
    searchField = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchByName, searchField, searchButton);

    add = new Button("Add");
    add.setOnAction(listener);

    teamMembersTable = new ListView<AssignedEmployee>();

    buttonsPane = new HBox();
    buttonsPane.setSpacing(50);
    buttonsPane.getChildren().addAll(addButton, removeButton, saveButton);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.getChildren().addAll(title,teamMembersTable,buttonsPane);

    newWindowPane = new VBox(searchPane, employeeListView, add);
  }

  /**
   * Gets the main pane of the class
   * @return VBox Main pane
   */
  public VBox getMainPane()
  {

    return mainPane;
  }

  /**
   * Gets the button Cancel of type Button
   * @return Button saveButton
   */
  public Button getSave()
  {
    return saveButton;
  }

  /**
   * Gets the button Add of type Button
   * @return Button addButtons
   */
  public Button getAddButton()
  {
    return addButton;
  }

  public void initializeCurrentProject(){
    currentProject = projectsAdapter.getSelectedProject(sepGUI.getManageProjectGUI().getSelectedProjectName());
    employeeList = currentProject.getAssignedEmployeeList();

    title.setText("Team Members of "+currentProject.getName());
    initializeTeamMembersTable();
    initializeListView();
  }

  public void initializeTeamMembersTable(){
    teamMembersTable.getItems().clear();
    for (int i = 0; i < employeeList.size(); i++) {
      teamMembersTable.getItems().add(employeeList.get(i));
    }

    /*
    teamMembersTable.getItems().clear();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      teamMembersTable.getItems().add(chosenAssignedEmployees.get(i));
    }*/
  }

  public void initializeListView()
  {
    employeeListView.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();
      EmployeeList chosenEmployees = new EmployeeList();
      for (int i = 0; i < teamMembersTable.getItems().size(); i++) {
        chosenEmployees.addEmployee(teamMembersTable.getItems().get(i));
      }
      for (int i = 0; i < chosenEmployees.size(); i++)
      {
        employees.removeEmployee(chosenEmployees.get(i));
      }
      for (int i = 0; i < employees.size(); i++)
      {
        employeeListView.getItems().add(employees.get(i));
      }
  }

  public boolean callAdd()
  {
    boolean OK = true;
    Employee employeeAdded = employeeListView.getSelectionModel().getSelectedItem();
    if (employeeAdded == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No employee was chosen!");
      alert.showAndWait();
      OK = false;
    }
    if (OK)
    {
      currentProject.addTeamMember(new AssignedEmployee(employeeAdded.getFirstName(), employeeAdded.getLastName(), employeeAdded.getDateOfBirth()));
      initializeTeamMembersTable();
      initializeListView();
    }

    return OK;
  }

  public VBox getNewWindowPane()
  {
    initializeListView();
    return newWindowPane;
  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      Employee temp = employeeListView.getSelectionModel().getSelectedItem();

      if (e.getSource() == searchButton)
      {
        String searchingFor = searchField.getText();
        EmployeeList employeeListOfRemainingEmployees = new EmployeeList();
        EmployeeList addedEmployees = new EmployeeList();
        for (int i = 0; i < teamMembersTable.getItems().size(); i++) {
          if(teamMembersTable.getItems().get(i) != null){
            addedEmployees.addEmployee(teamMembersTable.getItems().get(i));
          }
        }
        employeeListOfRemainingEmployees = employeeAdapter.getAllEmployees();
        for (int i = 0; i < employeeListOfRemainingEmployees.size(); i++) {
          if(addedEmployees.containsEmployee(employeeListOfRemainingEmployees.get(i))){
            employeeListOfRemainingEmployees.removeEmployee(employeeListOfRemainingEmployees.get(i));
          }
        }
        EmployeeList chosenEmployees = employeeAdapter.getEmployeesByName(searchingFor, employeeListOfRemainingEmployees);
        employeeListView.getItems().clear();
        for (int i = 0; i < chosenEmployees.size(); i++)
        {
          employeeListView.getItems().add(chosenEmployees.get(i));
        }
      }
      if (e.getSource() == removeButton)
      {
        if (!(teamMembersTable.getSelectionModel().getSelectedItem() == null))
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "Do you really want to delete team member " + teamMembersTable.getSelectionModel().getSelectedItem().toString()
                          + ")?", ButtonType.YES, ButtonType.NO);
          alert.setTitle("Delete employee");
          alert.setHeaderText(null);

          alert.showAndWait();

          if (alert.getResult() == ButtonType.YES) {
            System.out.println(projectsAdapter.getAllProjects().getProject(currentProject));
            if(projectsAdapter.getAllProjects().getProject(currentProject) != null){
              if (teamMembersTable.getSelectionModel().getSelectedItem().equals(projectsAdapter.getAllProjects().getProject(currentProject).getProductOwner())
                      || teamMembersTable.getSelectionModel().getSelectedItem().equals(projectsAdapter.getAllProjects().getProject(currentProject).getProjectCreator())
                      || teamMembersTable.getSelectionModel().getSelectedItem().equals(projectsAdapter.getAllProjects().getProject(currentProject).getScrumMaster())) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setHeaderText("Warning");
                alert2.setContentText("You cannot delete an employee with a status!");
                alert2.showAndWait();
              }
              else {
                ProjectList allProjects = projectsAdapter.getAllProjects();
                Project changedProject = allProjects.getProject(currentProject);
                changedProject.removeTeamMember(teamMembersTable.getSelectionModel().getSelectedItem());
                projectsAdapter.saveProjects(allProjects);
                currentProject.removeTeamMember(teamMembersTable.getSelectionModel().getSelectedItem());

                initializeTeamMembersTable();
                initializeListView();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Editing successful");
                alert2.setContentText("Changes were saved successfully!");
                alert2.showAndWait();
              }
            }
            else {
              ProjectList allProjects = projectsAdapter.getAllProjects();
              Project changedProject = allProjects.getProject(currentProject);
              if(changedProject != null){
                changedProject.removeTeamMember(teamMembersTable.getSelectionModel().getSelectedItem());
              }

              projectsAdapter.saveProjects(allProjects);
              currentProject.removeTeamMember(teamMembersTable.getSelectionModel().getSelectedItem());

              initializeTeamMembersTable();
              initializeListView();

              Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
              alert2.setHeaderText("Editing successful");
              alert2.setContentText("Changes were saved successfully!");
              alert2.showAndWait();
            }
          }
        }
        else
        {
          Alert alert2 = new Alert(Alert.AlertType.WARNING);
          alert2.setHeaderText("Warning");
          alert2.setContentText("No employee was chosen!");
          alert2.showAndWait();
        }
      }
    }
  }

  public Button getAdd()
  {
    return add;
  }

  public boolean callSaveButton(){
    boolean OK = true;
    System.out.println("asdadad");
    if(teamMembersTable.getItems().isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("There are no team members!");
      alert.showAndWait();
      OK = false;
    }
    if(teamMembersTable.getItems().size() < 3){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("There have to be at least 3 team members!");
      alert.showAndWait();
      OK = false;
    }
    if(OK){
      ProjectList allProjects = projectsAdapter.getAllProjects();
      /*Project changedProject = allProjects.getProject(currentProject);*/
      System.out.println(currentProject.getName());
      allProjects.removeProject(currentProject.getName());
      allProjects.addProject(currentProject);
      /*System.out.println(currentProject);
      for (int i = 0; i < teamMembersTable.getItems().size(); i++) {
        if(!currentProject.getAssignedEmployeeList().containsEmployee(teamMembersTable.getItems().get(i))){
          currentProject.addTeamMember(teamMembersTable.getItems().get(i));
        }
      }*/
      projectsAdapter.saveProjects(allProjects);

      initializeTeamMembersTable();
      initializeListView();
      Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
      alert2.setHeaderText("Editing successful");
      alert2.setContentText("Changes were saved successfully!");
      alert2.showAndWait();
    }
    return OK;
  }

}