import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProjectOverviewGUI
{
  private Button add;
  private Button manage;
  private Button continueButton;
  private Button searchButton;

  private TextField search;

  private Label title;
  private Label tableTitle;
  private Label searchLabel;

  private TableView projects;

  private VBox mainPane;
  private HBox topPane;
  private HBox searchPane;
  private HBox topButtons;

  public ProjectOverviewGUI(){

    add = new Button("Add");
    manage = new Button("Manage");
    continueButton = new Button("Continue");

    searchLabel = new Label("Search for a project");
    search = new TextField();
    searchButton = new Button("Search");

    title = new Label("Project Overview");
    tableTitle = new Label("Choose a project from the list");

    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projects = new TableView();

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topPane = new HBox();
    topPane.getChildren().addAll(title,searchPane);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(manage, add);

    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn descriptionCol = new TableColumn("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
    TableColumn statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    TableColumn teamMemberCol = new TableColumn("Team Member");
    teamMemberCol.setCellValueFactory(new PropertyValueFactory("teammember"));

    projects.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    projects.setPrefWidth(450);
    projects.setPrefHeight(300);
    projects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));
    mainPane.getChildren().addAll(title,topPane,topButtons,tableTitle, projects,continueButton);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getManage()
  {
    return manage;
  }

  public Button getAdd()
  {
    return add;
  }

  public Button getContinueButton()
  {
    return continueButton;
  }
}
