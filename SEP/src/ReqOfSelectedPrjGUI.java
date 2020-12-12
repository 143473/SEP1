import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
  private Label projectName;
  private Label tableTitle;
  private Label searchLabel;

  private MyActionListener listener;

  private TableView<Requirement> table;

  public ReqOfSelectedPrjGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listener = new MyActionListener();
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    add = new Button("Add");
    manage = new Button("Manage");
    search = new TextField();
    projectName = new Label();
    projectName.getStyleClass().add("heading");

    table = new TableView<Requirement>();

    projectNamePane = new HBox(5);
    projectNamePane.getChildren().addAll(projectName);

    continueButton = new Button("Continue");
    goBackButton = new Button("Go Back");

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(continueButton, goBackButton);

    tableTitle = new Label("Choose a requirement from the list");

    searchLabel = new Label("Search for a requirement");
    search = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchLabel,search,searchButton);

    topButtons = new HBox(5);
    topButtons.getChildren().addAll(add,manage);

   /* TableColumn idCol = new TableColumn<Requirement, Integer>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory("id"));*/
    TableColumn nameCol = new TableColumn<Requirement, String>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn userStoryCol = new TableColumn<Requirement, String>("User Story");
    userStoryCol.setCellValueFactory(new PropertyValueFactory("userStory"));
    TableColumn estimationCol = new TableColumn<Requirement, Integer>("Estimated Time");
    estimationCol.setCellValueFactory(new PropertyValueFactory("estimatedTime"));
    TableColumn deadlineCol = new TableColumn<Requirement, MyDate>("Deadline");
    deadlineCol.setCellValueFactory(new PropertyValueFactory("deadline"));
    TableColumn statusCol = new TableColumn<Requirement, ProgressStatus>("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    /*TableColumn totalHoursCol = new TableColumn("Total Hours");
    totalHoursCol.setCellValueFactory(new PropertyValueFactory("totalHours"));
    TableColumn teammembersCol = new TableColumn("Team Members");
    teammembersCol.setCellValueFactory(new PropertyValueFactory("teammembers"));*/
    TableColumn responsibleCol = new TableColumn<Requirement, AssignedEmployee>("Responsible Team Member");
    responsibleCol.setCellValueFactory(new PropertyValueFactory("responsibleEmployee"));

    table.getColumns().setAll(nameCol,userStoryCol,estimationCol,deadlineCol,statusCol);
    table.setPrefWidth(450);
    table.setPrefHeight(300);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(5);

    mainPane.getChildren().addAll(projectNamePane, searchPane,topButtons, tableTitle, table,bottomButtons);


  }
  public void initializeTable()
  {
    table.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();
    int index = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedIndex();
    Project selectedProject = projectsAdapter.getSelectedProject(index);
    for (int i = 0; i < selectedProject.getRequirements().size(); i++)
    {
      table.getItems().add(selectedProject.getRequirements().get(i));
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

  private void initializeTable(ArrayList<Requirement> newRequirements){
    table.getItems().clear();
    for (int i = 0; i < newRequirements.size(); i++) {
      table.getItems().add(newRequirements.get(i));
    }
  }

  public Label getProjectName()
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

  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      if (e.getSource() == searchButton)
      {
        String searchingFor = search.getText();
        Project project = sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem();
        ArrayList<Requirement> requirements = project.getRequirements();
        ArrayList<Requirement> chosenRequirements = projectsAdapter.getRequirementsByName(searchingFor, requirements);
        initializeTable(chosenRequirements);
      }
    }
  }
}
