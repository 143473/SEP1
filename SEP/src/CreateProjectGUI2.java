import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
  private Scene scene;

  private GridPane gridPane;
  private VBox mainPane;
  private HBox hBoxPaneButton;


  private Label title;
  private Label status;
  private Label searchByName;

  private TextField searchField;

  private ComboBox statusBox;

  private TableView employeesTable;
  private TableColumn firstNameColumn;
  private TableColumn lastNameColumn;
  private TableColumn birthdayColumn;

  private Button continueButton;
  private Button goBack;
  private Button search;


  public CreateProjectGUI2()

  {
    title = new Label("Create a new project");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    status = new Label("Project's status: ");
    statusBox = new ComboBox();

    searchByName = new Label("Search by name: ");
    searchField = new TextField("name");
    search = new Button("Search");

    gridPane = new GridPane();
    gridPane.addRow(0, status,statusBox);
    gridPane.addRow(1, searchByName,searchField,search);

    employeesTable = new TableView();
    employeesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    employeesTable.getSelectionModel().setCellSelectionEnabled(true);
    employeesTable.setPrefHeight(290);
    employeesTable.setTableMenuButtonVisible(false);


    firstNameColumn = new TableColumn("First Name");
    firstNameColumn.setCellFactory(new PropertyValueFactory<String, String>("First name"));
    firstNameColumn.setPrefWidth(300);

    lastNameColumn = new TableColumn("Last Name");
    lastNameColumn.setCellFactory(new PropertyValueFactory<String, String>("Last name"));
    lastNameColumn.setPrefWidth(300);

    birthdayColumn = new TableColumn("Birthday");
    birthdayColumn.setCellFactory(new PropertyValueFactory<String, String>("Birthday"));
    birthdayColumn.setPrefWidth(300);

    employeesTable.getColumns().addAll(firstNameColumn, lastNameColumn, birthdayColumn);

    continueButton = new Button("Continue");
    goBack = new Button("Go back");

    hBoxPaneButton = new HBox();
    hBoxPaneButton.getChildren().addAll(continueButton,goBack);

    mainPane = new VBox();
    mainPane.getChildren().addAll(title, gridPane, employeesTable, hBoxPaneButton);

  }

  public VBox getMainPane()
  {
    return mainPane;
  }
}

