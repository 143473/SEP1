
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
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
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
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
    currentProject = sepGUI.getManageProjectGUI().getSelectedProject();
    employeeList = currentProject.getAssignedEmployeeList();

    title.setText("Team Members of "+currentProject.getName());
    initializeTeamMembersTable();
  }

  public void initializeTeamMembersTable(){
    teamMembersTable.getItems().clear();
    AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
    for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
      teamMembersTable.getItems().add(chosenAssignedEmployees.get(i));
    }
  }

  public void initializeListView()
  {
    employeeListView.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();
    if(currentProject != null){
      AssignedEmployeeList chosenAssignedEmployees = currentProject.getAssignedEmployeeList();
      EmployeeList chosenEmployees = new EmployeeList();
      for (int i = 0; i < chosenAssignedEmployees.size(); i++) {
        chosenEmployees.addEmployee(new Employee(chosenAssignedEmployees.get(i).getFirstName(), chosenAssignedEmployees.get(i).getLastName(),
                chosenAssignedEmployees.get(i).getDateOfBirth()));
      }
      for (int i = 0; i < employees.size(); i++) {
        if(chosenEmployees.containsEmployee(employees.get(i))){
          employees.removeEmployee(employees.get(i));
        }
      }
      for (int i = 0; i < employees.size(); i++)
      {
        employeeListView.getItems().add(employees.get(i));
      }
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

          if (alert.getResult() == ButtonType.YES)
          {

            ProjectList allProjects = projectsAdapter.getAllProjects();
            for (int i = 0; i < allProjects.size(); i++) {
              if(allProjects.get(i).equals(currentProject)){
                allProjects.get(i).removeTeamMember(teamMembersTable.getSelectionModel().getSelectedItem());
                }
              }

            projectsAdapter.saveProjects(allProjects);

            initializeTeamMembersTable();
            initializeListView();

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

  public Button getAdd()
  {
    return add;
  }

  public boolean callSaveButton(){
    boolean OK = true;
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
      for (int i = 0; i < allProjects.size(); i++) {
        if(allProjects.get(i).equals(currentProject)){
          allProjects.get(i).removeAllTeamMembers();
          for (int j = 0; j < teamMembersTable.getItems().size(); j++) {
            allProjects.get(i).addTeamMember(teamMembersTable.getItems().get(j));
          }
        }
      }
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