import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ProjectOverviewGUI
{
  private ProjectsAdapter projectsAdapter;

  private MyActionListener listener;

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

    listener = new MyActionListener();

    add = new Button("Add");
    manage = new Button("Manage");
    continueButton = new Button("Continue");

    searchLabel = new Label("Search for a project");
    search = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);

    title = new Label("Project Overview");
    tableTitle = new Label("Choose a project from the list");

    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

    projectsTable = new TableView();

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topPane = new HBox();
    topPane.getChildren().addAll(title,searchPane);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(add, manage);

    nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    nameCol.setPrefWidth(200);

    descriptionCol = new TableColumn("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
    descriptionCol.setPrefWidth(300);

    statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    statusCol.setPrefWidth(70);

    teamMemberCol = new TableColumn("Team Members");
    teamMemberCol.setCellValueFactory(new PropertyValueFactory("assignedEmployeeList"));
    teamMemberCol.setPrefWidth(375);
    setCellFactory();

    projectsTable.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    projectsTable.setPrefHeight(350);

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
  private void initializeTable(ProjectList newProjects){
    projectsTable.getItems().clear();
    for (int i = 0; i < newProjects.size(); i++) {
      projectsTable.getItems().add(newProjects.get(i));
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
  private void setCellFactory() {

    Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
      @Override
      public TableCell call(TableColumn param) {
        final TableCell cell = new TableCell() {
          private Text text;

          @Override
          public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
              text = new Text(item.toString());
              text.setWrappingWidth(330);
              setGraphic(text);
            }
          }
        };
        return cell;
      }
    };

    teamMemberCol.setCellFactory(cellFactory);
    descriptionCol.setCellFactory(cellFactory);
  }
  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      if (e.getSource() == searchButton)
      {
        String searchingFor = search.getText();
        ProjectList projects = projectsAdapter.getAllProjects();
        ProjectList chosenProjects = projectsAdapter.getProjectsByName(searchingFor, projects);
        initializeTable(chosenProjects);
      }
    }
  }
}
