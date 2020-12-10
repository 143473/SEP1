import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * The 2nd part of the project creation user interface, that allows for
 * setting the status of the project and choosing the team members.
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI2
{
  private EmployeeAdapter employeeAdapter;
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

  private TableView<Employee> employeesTable;
  private TableView teamMembersTable;
  private TableColumn firstNameColumn;
  private TableColumn lastNameColumn;
  private TableColumn birthdayColumn;
  private TableColumn<Employee, String> firstColumn;
  private TableColumn<Employee, String> secondColumn;
  private TableColumn<Employee, MyDate> thirdColumn;

  private Button continueButton;
  private Button goBackButton;
  private Button searchButton;
  private Button addTeamMember;
  private Button add;
  private Button removeButton;

  public CreateProjectGUI2(EmployeeAdapter employeeAdapter)
  {
  this.employeeAdapter = employeeAdapter;
    title = new Label("Create a new project");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    status = new Label("Project's status: ");
    statusBox = new ComboBox();
    tableTitle = new Label("List of Team Members");

    searchByName = new Label("Search by name: ");
    searchField = new TextField();
    searchButton = new Button("Search");

    statusPane = new HBox(5);
    statusPane.getChildren().addAll(status,statusBox);

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchByName,searchField,searchButton);

    /*gridPane = new GridPane();
    gridPane.addRow(0, status, statusBox);
    gridPane.addRow(1, searchByName, searchField, searchButton);*/

    employeesTable = new TableView<Employee>();
    employeesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    employeesTable.getSelectionModel().setCellSelectionEnabled(true);
    employeesTable.setPrefHeight(290);
    employeesTable.setTableMenuButtonVisible(false);

    firstColumn = new TableColumn<Employee, String>("First Name");
    firstColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));

    secondColumn = new TableColumn<Employee, String>("Last Name");
    secondColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));

    thirdColumn = new TableColumn<Employee, MyDate>("Birthday");
    thirdColumn.setCellValueFactory(new PropertyValueFactory<Employee, MyDate>("dateOfBirth"));

    employeesTable.getColumns().add(firstColumn);
    employeesTable.getColumns().add(secondColumn);
    employeesTable.getColumns().add(thirdColumn);

    firstColumn.setReorderable(false);
    secondColumn.setReorderable(false);
    thirdColumn.setReorderable(false);
    thirdColumn.setSortable(false);


    firstNameColumn = new TableColumn("First Name");
    firstNameColumn.setCellFactory(new PropertyValueFactory<String, String>("First name"));


    lastNameColumn = new TableColumn("Last Name");
    lastNameColumn.setCellFactory(new PropertyValueFactory<String, String>("Last name"));


    birthdayColumn = new TableColumn("Birthday");
    birthdayColumn.setCellFactory(new PropertyValueFactory<String, String>("Birthday"));

    teamMembersTable = new TableView();
    teamMembersTable.getColumns().addAll(firstNameColumn, lastNameColumn, birthdayColumn);

    continueButton = new Button("Continue");
    goBackButton = new Button("Go back");
    addTeamMember = new Button("Add Team Member");
    removeButton = new Button("Remove Member");

    topButtonsPane = new HBox(5);
    topButtonsPane.getChildren().addAll(addTeamMember, removeButton);

    add = new Button("Add");

    hBoxPaneButton = new HBox(5);
    hBoxPaneButton.getChildren().addAll(continueButton, goBackButton);

    mainPane = new VBox(5);
    mainPane.getChildren().addAll(title, statusPane,topButtonsPane,tableTitle, teamMembersTable, hBoxPaneButton);



    newWindowPane = new VBox(searchPane, employeesTable, add);
  }

  public void setProjectList(ProjectList projectList) {
    this.projectList = projectList;
  }

  private void initializeTable(){
    employeesTable.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();

    for (int i = 0; i < employees.size(); i++) {
      employeesTable.getItems().add(employees.get(i));
    }
  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getGoBackButton()
  {
    return goBackButton;
  }

  public Button getContinueButton()
  {
    return continueButton;
  }

  public Button getAddTeamMember()
  {
    return addTeamMember;
  }

  public VBox getNewWindowPane()
  {
    initializeTable();
    return newWindowPane;
  }

  public Button getAdd()
  {
    return add;
  }
}
  /*private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      if (e.getSource() == addTeamMember)
      {
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Choose Team Member");
        scene = new Scene(newWindowPane);
        newWindow.setScene(scene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
      }
    }
  }
}

*/