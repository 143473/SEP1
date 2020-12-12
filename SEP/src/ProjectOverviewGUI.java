import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ProjectOverviewGUI
{
  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private MyActionListener listener;

  private Button add;
  private Button manage;
  private Button continueButton;
  private Button searchButton;

  private TextField search;

  private Label title;
  private Label tableTitle;
  private Label searchLabel;

  private TableView<Project> projectsTable;
  private TableColumn nameCol;
  private TableColumn descriptionCol;
  private TableColumn statusCol;
  private TableColumn teamMemberCol;

  private VBox mainPane;
  private HBox topPane;
  private HBox searchPane;
  private HBox topButtons;
  private HBox bottomButtons;

  public ProjectOverviewGUI(ProjectsAdapter projectsAdapter,SepGUI sepGUI){
    this.projectsAdapter = projectsAdapter;

    listener = new MyActionListener();

    add = new Button("Add");
    manage = new Button("Manage");
    continueButton = new Button("Continue");

    title = new Label("Project Overview");
    title.getStyleClass().add("heading");

    search = new TextField();
    search.setPromptText("Search by name");
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);

    tableTitle = new Label("Choose a project from the list");

    projectsTable = new TableView();

    searchPane = new HBox(8);
    searchPane.getChildren().addAll(search,searchButton);

    topPane = new HBox(530);
    topPane.getChildren().addAll(title,searchPane);

    topButtons = new HBox(8);
    topButtons.getChildren().addAll(add, manage);

    bottomButtons = new HBox(800);
    bottomButtons.getChildren().addAll(topButtons, continueButton);

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
    projectsTable.setPrefHeight(290);

    mainPane = new VBox(8);
    mainPane.getChildren().addAll(topPane,tableTitle, projectsTable,bottomButtons);
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

  public TableView<Project> getProjectsTable()
  {
    return projectsTable;
  }

  public Button getManage()
  {
    return manage;
  }

  /*public boolean callContinueButton(){
    boolean gogo = true;
    if(projectsTable.getSelectionModel().getSelectedItem()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      String projectName = projectsTable.getSelectionModel().getSelectedItem().getName();
      System.out.println(projectsTable.getSelectionModel().getSelectedItem().getName());
      sepGUI.getReqOfSelectedPrjGUI().getProjectName().setText(projectName);
      gogo =true;
    }
    return gogo;
  }*/

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
