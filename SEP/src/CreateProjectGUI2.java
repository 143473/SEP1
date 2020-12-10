import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;

/**
 * The 2nd part of the project creation user interface, that allows for
 * setting the status of the project and choosing the team members.
 * @author Claudiu Cordunianu, Timothy Engkar, Marketa Lapcikova
 * @version 1.0
 */
public class CreateProjectGUI2
{
  private EmployeeList employeeList;

  private SepGUI sepGUI;

  private EmployeeAdapter employeeAdapter;
  private ProjectsAdapter projectsAdapter;
  private MyActionListener listener;
  private MyListListener listListener;

  private VBox mainPane;
  private HBox hBoxPaneButton;
  private VBox newWindowPane;
  private HBox statusPane;
  private HBox searchPane;
  private HBox topButtonsPane;

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
   * 3-argument constructor setting the values for the GUI
   * @param employeeAdapter the employee adaptor class
   * @param projectsAdapter the projects adaptor class
   * @param sepGUI the main GUI
   */
  public CreateProjectGUI2(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI) {
  public CreateProjectGUI2(EmployeeAdapter employeeAdapter, ProjectsAdapter projectsAdapter, SepGUI sepGUI)
  {
    employeeList = new EmployeeList();
    this.sepGUI = sepGUI;

    this.employeeAdapter = employeeAdapter;
    this.projectsAdapter = projectsAdapter;
    listener = new MyActionListener();
    listListener = new MyListListener();

    title = new Label("Create a new project");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

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

    statusPane = new HBox(5);
    statusPane.getChildren().addAll(status, statusBox);

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchByName, searchField, searchButton);

    /*gridPane = new GridPane();
    gridPane.addRow(0, status, statusBox);
    gridPane.addRow(1, searchByName, searchField, searchButton);*/

    employeeListView = new ListView<Employee>();
    employeeListView.setPrefHeight(120);

    teamMembersTable = new ListView<Employee>();

    continueButton = new Button("Continue");
    goBackButton = new Button("Go back");
    addTeamMember = new Button("Add Team Member");
    removeButton = new Button("Remove Member");
    removeButton.setOnAction(listener);

    topButtonsPane = new HBox(5);
    topButtonsPane.getChildren().addAll(addTeamMember, removeButton);

    add = new Button("Add");
    add.setOnAction(listener);

    hBoxPaneButton = new HBox(5);
    hBoxPaneButton.getChildren().addAll(continueButton, goBackButton);

    mainPane = new VBox(5);
    mainPane.getChildren()
        .addAll(title, statusPane, topButtonsPane, tableTitle, teamMembersTable,
            hBoxPaneButton);

    newWindowPane = new VBox(searchPane, employeeListView, add);
  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
  /**
   * Listener to the actions
   */
  private class MyActionListener implements EventHandler<ActionEvent> {
    /**
     * Handles the actions in this class
     * @param e the action that happened
     */
    public void handle(ActionEvent e) {
      Employee temp = employeeListView.getSelectionModel().getSelectedItem();

      if (e.getSource() == searchButton)
      {
        String searchingFor = searchField.getText();
        employeeListView.getItems().clear();
        EmployeeList chosenEmployees = employeeAdapter.getEmployeesByName(searchingFor);
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
            teamMembersTable.getItems().remove(teamMembersTable.getSelectionModel().getSelectedItem());
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
   * Checks all the fields necessary to continue for their values
   * @return boolean if all the values are correct, true if they are, false otherwise
   */
  public boolean callAdd() {
    boolean OK = true;
    Employee employeeAdded = employeeListView.getSelectionModel().getSelectedItem();
    if(employeeAdded == null){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No employee was chosen!");
      alert.showAndWait();
      OK = false;
    }
    if(OK){
      teamMembersTable.getItems().add(employeeAdded);
      employeeList.addEmployee(employeeAdded);
      employeeListView.getItems().remove(employeeAdded);
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
        teamMembersTable.getItems().add(employeeAdded);
        employeeList.addEmployee(employeeAdded);
        employeeListView.getItems().remove(employeeAdded);
      }

      return OK;
    }

    public void setProjectList(ProjectList projectList)
    {
      this.projectList = projectList;
    }
  /**
   *Sets the project list to the sent one
   * @param projectList projectList that needs to be set
   */
  public void setProjectList(ProjectList projectList) {
    this.projectList = projectList;
  }

    public void initializeListView()
    {
      employeeListView.getItems().clear();
      EmployeeList employees = employeeAdapter.getAllEmployees();
      employeeListView.getItems().add(null);
      for (int i = 0; i < employees.size(); i++)
      {
        employeeListView.getItems().add(employees.get(i));
      }
    }
  /**
   * Initializes list view to new values
   */
  public void initializeListView() {
    employeeListView.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();
    employeeListView.getItems().add(null);
    for (int i = 0; i < employees.size(); i++) {
      employeeListView.getItems().add(employees.get(i));
    }
  }

    /**
     * Method for getting the second part of Create Project GUI, to be used in mainGUI
     *
     * @return Returns the main pane
     */
    public VBox getMainPane()
    {
      return mainPane;
    }
  /**
   * Method for getting the second part of Create Project GUI, to be used in mainGUI
   * @return Returns the main pane
   */
  public VBox getMainPane() {
    return mainPane;
  }

    /**
     * Method for getting the go back button of this class
     *
     * @return Returns the go back button
     */
    public Button getGoBackButton()
    {
      return goBackButton;
    }
  /**
   * Method for getting the go back button of this class
   * @return Returns the go back button
   */
  public Button getGoBackButton() {
    return goBackButton;
  }

    /**
     * Method for getting the continue button of this class
     *
     * @return Returns the continue button
     */

    public Button getContinueButton()
    {
      return continueButton;
    }
  /**
   * Method for getting the continue button of this class
   * @return Returns the continue button
   */
  public Button getContinueButton() {
    return continueButton;
  }

  /**
   * Gets the Button addTeamMember
   * @return Button addTeamMember
   */
  public Button getAddTeamMember() {
    return addTeamMember;
  }
    public Button getAddTeamMember()
    {
      return addTeamMember;
    }

    public VBox getNewWindowPane()
    {
      initializeListView();
      return newWindowPane;
    }

  /**
   * Initializes list view returns newWindowPane
   * @return VBox newWindowPane
   */
  public VBox getNewWindowPane() {
    initializeListView();
    return newWindowPane;
  }

    private class MyListListener implements ChangeListener<Employee>
    {
      public void changed(ObservableValue<? extends Employee> employee, Employee oldEmployee, Employee newEmployee)
      {

      }
    }

    public Button getAdd()
    {
      return add;
    }
  /**
   * Gets the Button add
   * @return Button add
   */
  public Button getAdd() {
    return add;
  }

  /**
   * Checks all the fields necessary to continue for their values
   * @return boolean if all the values are correct, true if they are, false otherwise
   */
  public boolean callContinueButton() {
    boolean allValuesCorrect = true;
    if (teamMembersTable.getItems().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No employees added to the project");
      alert.showAndWait();
      allValuesCorrect = false;
    }
    Project project = projectList.get(0);
    project.setProgressStatus(statusBox.getSelectionModel().getSelectedIndex());
    for (int i = 0; i < employeeList.size(); i++) {
      AssignedEmployee assignedEmployee = new AssignedEmployee(employeeList.get(i).getFirstName(), employeeList.get(i).getLastName(), employeeList.get(i).getDateOfBirth());
      project.addTeamMember(assignedEmployee);
    }
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
      Project project = projectList.get(0);
      project.setProgressStatus(statusBox.getSelectionModel().getSelectedIndex());
      for (int i = 0; i < employeeList.size(); i++)
      {
        AssignedEmployee assignedEmployee = new AssignedEmployee(
            employeeList.get(i).getFirstName(),
            employeeList.get(i).getLastName(),
            employeeList.get(i).getDateOfBirth());
        project.addTeamMember(assignedEmployee);
      }

      return allValuesCorrect;
    }

  public EmployeeList getEmployeeList()
  {
    return employeeList;
  }
}

