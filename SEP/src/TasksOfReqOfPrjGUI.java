import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TasksOfReqOfPrjGUI
{
  private MyActionListener listener;

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

  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  public TasksOfReqOfPrjGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI){

    listener = new MyActionListener();
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    add = new Button("Add");
    manage = new Button("Manage");

    projectLabel = new Label("Project:");
    projectLabel.getStyleClass().add("heading");
    projectName = new Label();
    requirementLabel = new Label("Requirement:");
    requirementLabel.getStyleClass().add("heading");
    requirementName = new Label();

    table = new TableView();

    goBackButton = new Button("Go Back");

    projectRequirementPane = new GridPane();
    projectRequirementPane.setHgap(5);
    projectRequirementPane.addRow(0,projectLabel, projectName);
    projectRequirementPane.addRow(1,requirementLabel,requirementName);

    tableTitle = new Label("List of tasks");

    searchLabel = new Label("Search for a task: ");
    search = new TextField();
    searchButton = new Button("Search");
    searchButton.setOnAction(listener);
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

  public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getReqOfSelectedPrjGUI().getTable().getSelectionModel().getSelectedItem()== null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No requirement was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      projectName.setText(sepGUI.getProjectOverviewGUI().getProjectsTable().getSelectionModel().getSelectedItem().getName());
      requirementName.setText(sepGUI.getReqOfSelectedPrjGUI().getTable().getSelectionModel().getSelectedItem().toString());
      gogo =true;
    }
    return gogo;
  }

    private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      if (e.getSource() == searchButton)
      {
        String searchingFor = search.getText();
        Requirement requirement = (Requirement)sepGUI.getReqOfSelectedPrjGUI().getTable().getSelectionModel().getSelectedItem();
        ArrayList<Task> tasks = requirement.getTasks();
        ArrayList<Task> chosenTasks = projectsAdapter.getTasksByName(searchingFor, tasks);
        initializeTable(chosenTasks);
      }
    }
  }

  private void initializeTable()
  {
    table.getItems().clear();
    Requirement requirement = (Requirement)sepGUI.getReqOfSelectedPrjGUI().getTable().getSelectionModel().getSelectedItem();
    for (int i = 0; i < requirement.getTasks().size(); i++)
    {
      table.getItems().add(requirement.getTasks().get(i));
    }
  }

  private void initializeTable(ArrayList<Task> newTasks){
    table.getItems().clear();
    for (int i = 0; i < newTasks.size(); i++) {
      table.getItems().add(newTasks.get(i));
    }
  }
}

