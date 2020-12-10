import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReqOfSelectedPrjGUI
{

  private VBox mainPane;
  private HBox searchPane;
  private HBox topButtons;
  private GridPane projectNamePane;
  private HBox bottomButtons;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button continueButton;
  private Button goBackButton;

  private TextField search;

  private Label projectLabel;
  private Label projectName;
  private Label spacer;
  private Label tableTitle;
  private Label searchLabel;

  private TableView table;

  public ReqOfSelectedPrjGUI(){

    add = new Button("Add");
    manage = new Button("Manage");
    search = new TextField();
    projectLabel = new Label("Project:");
    projectLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    spacer = new Label("-");
    projectName = new Label();
    spacer.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    table = new TableView();

    projectNamePane = new GridPane();
    projectNamePane.setHgap(5);
    projectNamePane.getChildren().addAll(projectLabel, projectName);

    continueButton = new Button("Continue");
    goBackButton = new Button("Go Back");

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(continueButton, goBackButton);

    tableTitle = new Label("Choose a requirement from the list");

    searchLabel = new Label("Search for a requirement");
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
    TableColumn userStoryCol = new TableColumn("User Story");
    userStoryCol.setCellValueFactory(new PropertyValueFactory("userstory"));
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

    table.getColumns().setAll(idCol, nameCol,userStoryCol,estimationCol,deadlineCol,statusCol,totalhrsCol,teammembersCol,responsibleCol);
    table.setPrefWidth(450);
    table.setPrefHeight(300);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));
    mainPane.getChildren().addAll(projectNamePane, searchPane,topButtons, tableTitle, table,bottomButtons);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getContinueButton()
  {
    return continueButton;
  }

  public Button getAdd()
  {
    return add;
  }

  public Button getManage()
  {
    return manage;
  }

  public Button getGoBackButton()
  {
    return goBackButton;
  }
}
