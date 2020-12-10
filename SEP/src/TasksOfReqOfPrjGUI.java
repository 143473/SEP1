import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TasksOfReqOfPrjGUI
{

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


  public TasksOfReqOfPrjGUI(){

    add = new Button("ADD");
    manage = new Button("MANAGE");

    projectLabel = new Label("Project:");
    projectLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projectName = new Label();
    requirementLabel = new Label("Requirement:");
    requirementLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    requirementName = new Label();

    table = new TableView();

    goBackButton = new Button("Go Back");

    projectRequirementPane = new GridPane();
    projectRequirementPane.setHgap(5);
    projectRequirementPane.addRow(0,projectLabel, projectName);
    projectRequirementPane.addRow(1,requirementLabel,requirementName);

    tableTitle = new Label("List of tasks");

    searchLabel = new Label("Search for a project");
    search = new TextField();
    searchButton = new Button("Search");
    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(add,manage);

    TableColumn idCol = new TableColumn("ID");
    idCol.setCellValueFactory(new PropertyValueFactory("id"));
    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn estimationCol = new TableColumn("Estimation");
    estimationCol.setCellValueFactory(new PropertyValueFactory("estimation"));
    TableColumn deadlineCol = new TableColumn("Deadline");
    deadlineCol.setCellValueFactory(new PropertyValueFactory("deadline"));
    TableColumn statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    TableColumn totalhrsCol = new TableColumn("Total Hours");
    totalhrsCol.setCellValueFactory(new PropertyValueFactory("totalhours"));
    TableColumn teammembersCol = new TableColumn("Team Members");
    teammembersCol.setCellValueFactory(new PropertyValueFactory("teammembers"));
    TableColumn responsibleCol = new TableColumn("Responsible Team Member");
    responsibleCol.setCellValueFactory(new PropertyValueFactory("responsibleteammember"));

    table.getColumns().setAll(idCol, nameCol,estimationCol,deadlineCol,statusCol,totalhrsCol,teammembersCol,responsibleCol);
    table.setPrefWidth(450);
    table.setPrefHeight(300);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));
    mainPane.getChildren().addAll(projectRequirementPane,searchPane,topButtons,tableTitle, table,goBackButton);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getGoBackButton()
  {
    return goBackButton;
  }

  public Button getAdd()
  {
    return add;
  }

  public Button getManage()
  {
    return manage;
  }
}

