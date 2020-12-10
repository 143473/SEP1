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
  private TextField search;
  private Label title;
  private TableView allProjectsTable;
  private VBox mainPane;

  public ProjectOverviewGUI(){

    add = new Button("ADD");
    manage = new Button("MANAGE");
    search = new TextField();
    title = new Label("Project Overview");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    allProjectsTable = new TableView();
    HBox hb = new HBox();
    hb.setSpacing(100);
    hb.getChildren().addAll(title, search);
    HBox hb2 = new HBox();
    hb2.setSpacing(100);
    hb2.getChildren().addAll(manage, add);
    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn descriptionCol = new TableColumn("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
    TableColumn statusCol = new TableColumn("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    TableColumn teamMemberCol = new TableColumn("Team Member");
    teamMemberCol.setCellValueFactory(new PropertyValueFactory("teammember"));

    allProjectsTable.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    allProjectsTable.setPrefWidth(450);
    allProjectsTable.setPrefHeight(300);
    allProjectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    mainPane = new VBox(20);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(hb, hb2, allProjectsTable);


  }
  private void initializeTable(){
    allProjectsTable.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();

    for (int i = 0; i < projects.size(); i++) {
      allProjectsTable.getItems().add(projects.get(i));
    }
  }

  public VBox getMainPane() {
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
}
