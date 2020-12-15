import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The 2nd part of the project creation user interface, that allows
 * setting the status of the project and choosing the team members.
 * @author Claudiu Emanuel Cordunianu, Marketa Lapcikova
 * @version 1.0
 */
public class CreateProjectGUI2
{
  private EmployeeList employeeList;

  private SepGUI sepGUI;

  private EmployeeAdapter employeeAdapter;
  private ProjectsAdapter projectsAdapter;
  private MyActionListener listener;

  private VBox mainPane;
  private HBox hBoxPaneButton;
  private VBox newWindowPane;
  private HBox statusPane;
  private HBox searchPane;
  private GridPane searchStatusPane;

  private ProjectList projectList;

  private Label title;
  private Label status;
  private Label searchByName;
  private Label tableTitle;

  private TextField searchField;

  private ComboBox statusBox;

  private ListView<Employee> employeeListView;
  private ListView<Employee> teamMembersTable;

  private Button continueButton;
  private Button goBackButton;
  private Button searchButton;
  private Button addTeamMember;
  private Button add;
  private Button removeButton;

  /**
   * 3-argument constructor initializing the GUI components
   * @param projectsAdapter adapter of the projects, requirements and tasks
   * @param employeeAdapter adapter of the employees
   * @param sepGUI the main GUI where all the other GUIs are connected
   */
  public CreateProjectGUI2(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI)
  {
    employeeList = new EmployeeList();
    this.sepGUI = sepGUI;

    this.employeeAdapter = employeeAdapter;
    this.projectsAdapter = projectsAdapter;
    listener = new MyActionListener();

    title = new Label("Create a new project");
    title.getStyleClass().add("heading");

    status = new Label("Project's status: ");
    statusBox = new ComboBox();
    ProgressStatus progressStatus = new ProgressStatus();
    String[] statuses = progressStatus.getStatuses();
    for (int i = 0; i < statuses.length; i++)
    {
      statusBox.getItems().add(statuses[i]);
    }
    statusBox.setValue(statuses[progressStatus.getDefaultIndex()]);


    tableTitle = new Label("List of Team Members");

    searchByName = new Label("Search by name: ");
    searchField = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);

    statusPane = new HBox(8);
    statusPane.getChildren().addAll(status, statusBox);

    searchPane = new HBox(8);
    searchPane.getChildren().addAll(searchByName, searchField, searchButton);

    employeeListView = new ListView<Employee>();
    employeeListView.setPrefHeight(120);

    teamMembersTable = new ListView<Employee>();
    teamMembersTable.getItems().clear();

    continueButton = new Button("Continue");
    goBackButton = new Button("Go back");
    addTeamMember = new Button("Add Team Member");
    removeButton = new Button("Remove Member");
    removeButton.setOnAction(listener);

    searchStatusPane = new GridPane();
    searchStatusPane.addRow(0, status, statusBox);
    searchStatusPane.addRow(1, addTeamMember, removeButton);
    searchStatusPane.setVgap(8);
    searchStatusPane.setHgap(8);


    add = new Button("Add");
    add.setOnAction(listener);
    add.getStyleClass().add("newWindow");

    hBoxPaneButton = new HBox(8);
    hBoxPaneButton.getChildren().addAll(continueButton, goBackButton);

    mainPane = new VBox(8);
    mainPane.getChildren()
        .addAll(title, searchStatusPane, tableTitle, teamMembersTable,
            hBoxPaneButton);

    newWindowPane = new VBox(8);
    newWindowPane.getChildren().addAll(searchPane, employeeListView, add);
    newWindowPane.getStyleClass().add("window");
  }

  /**
   * Handles the actions in this class
   */
  private class MyActionListener implements EventHandler<ActionEvent>
  {
    /**
     * Handles the actions of this class
     * @param e event that happens
     */
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

        for (int i = 0; i < addedEmployees.size(); i++)
        {
          employeeListOfRemainingEmployees.removeEmployee(addedEmployees.get(i));
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

          if (alert.getResult() == ButtonType.YES)
          {
            employeeList.removeEmployee(teamMembersTable.getSelectionModel().getSelectedItem());
            initializeTeamMembersTable();
            initializeListView();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("Editing successful");
            alert2.setContentText("Changes were saved successfully!");
            alert2.showAndWait();
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("No employee was chosen!");
          alert.showAndWait();
        }
      }
    }
  }
    /**
     * Checks the validity of entered data after the add Button is called
     * @return boolean true if all the input data is correct, otherwise false
     */
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
        employeeList.addEmployee(employeeAdded);
        initializeTeamMembersTable();
        initializeListView();
      }

      return OK;
    }

  /**
   * Sets the projectList to all projects
   */
  public void setProjectList()
    {
      this.projectList = projectsAdapter.getAllProjects();
    }

  /**
   * Initializes the employeeListView
   */
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

  /**
   * Initializes the teamMembersTable
   */
  public void initializeTeamMembersTable(){
      teamMembersTable.getItems().clear();
      for (int i = 0; i < employeeList.size(); i++) {
        teamMembersTable.getItems().add(employeeList.get(i));
      }
    }

    /**
     * Method for getting the second part of Create Project GUI, to be used in mainGUI
     * @return the VBox mainPane
     */
    public VBox getMainPane()
    {
      return mainPane;
    }

    /**
     * Method for getting the goBackButton Button of this class
     * @return the goBackButton Button
     */
    public Button getGoBackButton()
    {
      return goBackButton;
    }

    /**
     * Method for getting the continueButton Button of this class
     * @return  the continueButton Button
     */
    public Button getContinueButton()
    {
      return continueButton;
    }

  /**
   * Gets the addTeamMember Button
   * @return addTeamMember Button
   */
  public Button getAddTeamMember()
    {
      return addTeamMember;
    }

  /**
   * Gets the newWindowPane for the pop-up window of employee members
   * @return VBox newWindowPane
   */
  public VBox getNewWindowPane()
    {
      initializeListView();
      return newWindowPane;
    }

  /**
   * Gets the add Button
   * @return add Button
   */
  public Button getAdd()
    {
      return add;
    }

  /**
   * Checks the validity of entered data after the continue Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callContinueButton()
    {
      boolean allValuesCorrect = true;
      if (teamMembersTable.getItems().isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText("No employees added to the project");
        alert.showAndWait();
        allValuesCorrect = false;
      }
      if(allValuesCorrect){
        Project project = projectList.get(projectList.size()-1);
        project.setStatus(statusBox.getSelectionModel().getSelectedIndex());
        for (int i = 0; i < employeeList.size(); i++)
        {
          AssignedEmployee assignedEmployee = new AssignedEmployee(
                  employeeList.get(i).getFirstName(),
                  employeeList.get(i).getLastName(),
                  employeeList.get(i).getDateOfBirth());
          project.addTeamMember(assignedEmployee);
        }
        projectsAdapter.saveProjects(projectList);
        getTeamMembersToBox(employeeList);
      }


      return allValuesCorrect;
    }

  /**
   * Gets the team members from the employeeList to the box
   * @param employeeList list of employees of EmployeeList that go to the selection box
   */
  public void getTeamMembersToBox(EmployeeList employeeList)
    {
      sepGUI.getCreateProjectGUI3().getEmployeesBox1().getItems().clear();
      sepGUI.getCreateProjectGUI3().getEmployeesBox2().getItems().clear();
      sepGUI.getCreateProjectGUI3().getEmployeesBox3().getItems().clear();
      for (int i = 0; i < employeeList.size(); i++)
      {
        if (!(sepGUI.getCreateProjectGUI3().getEmployeesBox1().getItems().contains(employeeList.get(i))))
        {
          sepGUI.getCreateProjectGUI3().getEmployeesBox1().getItems().add(employeeList.get(i));
        }
        if (!(sepGUI.getCreateProjectGUI3().getEmployeesBox2().getItems().contains(employeeList.get(i))))
        {
          sepGUI.getCreateProjectGUI3().getEmployeesBox2().getItems().add(employeeList.get(i));
        }
        if (!(sepGUI.getCreateProjectGUI3().getEmployeesBox3().getItems().contains(employeeList.get(i))))
        {
          sepGUI.getCreateProjectGUI3().getEmployeesBox3().getItems().add(employeeList.get(i));
        }
      }
      sepGUI.getCreateProjectGUI3().getEmployeesBox1().getSelectionModel().select(0);
      sepGUI.getCreateProjectGUI3().getEmployeesBox2().getSelectionModel().select(0);
      sepGUI.getCreateProjectGUI3().getEmployeesBox3().getSelectionModel().select(0);
    }

  /**
   * Gets the employeeList EmployeeList
   * @return employeeList EmployeeList
   */
  public EmployeeList getEmployeeList()
  {
    return employeeList;
  }

  /**
   * Clears all the fields, sets the textFields to empty and clears the choice in choiceBoxes
   */
  public void clearFields(){
      statusBox.getSelectionModel().select(0);
      initializeTeamMembersTable();
      initializeListView();
      teamMembersTable.getItems().clear();
      employeeList = new EmployeeList();
  }
}
