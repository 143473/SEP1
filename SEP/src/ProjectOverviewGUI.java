import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProjectOverviewGUI
{
  private ProjectsAdapter projectsAdapter;

  private Button add;
  private Button manage;
  private Button continueButton;
  private Button searchButton;

  private TextField search;

  private Label title;
  private Label tableTitle;
  private Label searchLabel;

  private TableView projectsTable;
  private TableColumn nameCol;
  private TableColumn descriptionCol;
  private TableColumn statusCol;
  private TableColumn teamMemberCol;

  private VBox mainPane;
  private HBox topPane;
  private HBox searchPane;
  private HBox topButtons;

  public ProjectOverviewGUI(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    add = new Button("Add");
    manage = new Button("Manage");
    continueButton = new Button("Continue");

    searchLabel = new Label("Search for a project");
    search = new TextField();
    searchButton = new Button("Search");

    title = new Label("Project Overview");
    tableTitle = new Label("Choose a project from the list");

    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

    projectsTable = new TableView();

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topPane = new HBox();
    topPane.getChildren().addAll(title,searchPane);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(manage, add);

    nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));

    descriptionCol = new TableColumn("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));

    statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));

    teamMemberCol = new TableColumn("Team Member");
    teamMemberCol.setCellValueFactory(new PropertyValueFactory("teammember"));

    projectsTable.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    projectsTable.setPrefWidth(450);
    projectsTable.setPrefHeight(300);
    projectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));
    mainPane.getChildren().addAll(title,topPane,topButtons,tableTitle, projectsTable,continueButton);


  }
  private void initializeTable(){
    projectsTable.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();

    for (int i = 0; i < projects.size(); i++) {
      projectsTable.getItems().add(projects.get(i));
    }
  }

  public VBox getMainPane()
  {
    initializeTable();
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
