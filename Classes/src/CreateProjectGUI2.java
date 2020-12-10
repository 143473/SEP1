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

  private VBox mainPane;
  private HBox hBoxPaneButton;
  private VBox newWindowPane;
  private HBox statusPane;
  private HBox searchPane;
  private HBox topButtonsPane;

  private Label title;
  private Label status;
  private Label searchByName;
  private Label tableTitle;

  private TextField searchField;

  private ComboBox statusBox;

  private TableView employeesTable;
  private TableView teamMembersTable;
  private TableColumn firstNameColumn;
  private TableColumn lastNameColumn;
  private TableColumn birthdayColumn;
  private TableColumn firstColumn;
  private TableColumn secondColumn;
  private TableColumn thirdColumn;

  private Button continueButton;
  private Button goBackButton;
  private Button searchButton;
  private Button addTeamMember;
  private Button add;
  private Button removeButton;

  public CreateProjectGUI2()
  {

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

    employeesTable = new TableView();
    employeesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    employeesTable.getSelectionModel().setCellSelectionEnabled(true);
    employeesTable.setPrefHeight(290);
    employeesTable.setTableMenuButtonVisible(false);

    firstColumn = new TableColumn("First Name");
    firstColumn.setCellFactory(new PropertyValueFactory<String, String>("First name"));

    secondColumn = new TableColumn("Last Name");
    secondColumn.setCellFactory(new PropertyValueFactory<String, String>("Last name"));

    thirdColumn = new TableColumn("Birthday");
    thirdColumn.setCellFactory(new PropertyValueFactory<String, String>("Birthday"));

    employeesTable.getColumns().addAll(firstColumn, secondColumn, thirdColumn);


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
    mainPane.getChildren().addAll(title, statusPane,topButtonsPane,tableTitle, employeesTable, hBoxPaneButton);



    newWindowPane = new VBox(searchPane, teamMembersTable, add);
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