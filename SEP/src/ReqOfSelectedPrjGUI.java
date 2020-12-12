import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReqOfSelectedPrjGUI
{
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private VBox mainPane;
  private HBox searchPane;
  private HBox topButtons;
  private HBox projectNamePane;
  private HBox bottomButtons;

  private Button add;
  private Button manage;
  private Button searchButton;
  private Button continueButton;
  private Button goBackButton;

  private TextField search;

  private Label projectLabel;
  private Text projectName;
  private Label tableTitle;
  private Label searchLabel;

  private TableView table;

  public ReqOfSelectedPrjGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    add = new Button("Add");
    manage = new Button("Manage");
    search = new TextField();
    projectLabel = new Label("Project: ");
    projectLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projectName = new Text();
    projectName.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 20));

    table = new TableView();

    projectNamePane = new HBox(5);
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
  private void initializeTable()
  {
    table.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();
    Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
    for (int i = 0; i < project.getRequirements().size(); i++)
    {
      table.getItems().add(project.getRequirements().get(i));
    }
  }
  public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem()== null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      projectName.setText(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName());
      gogo =true;
    }
    return gogo;
  }
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
  }

  public Text getProjectName()
  {
    return projectName;
  }

  public TableView getTable()
  {
    return table;
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
