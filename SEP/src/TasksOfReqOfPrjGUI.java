import javafx.application.Application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TasksOfReqOfPrjGUI
{

  private VBox mainPane;
  private Button add;
  private Button manage;
  private TextField search;
  private Label projectname;
  private Label spacer;
  private Label spacer2;
  private Label reqname;
  private Label tasks;
  private TableView table;

  public TasksOfReqOfPrjGUI(){

    add = new Button("ADD");
    manage = new Button("MANAGE");
    search = new TextField();
    projectname = new Label("Projectnamehere");
    projectname.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    spacer = new Label("-");
    spacer.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    spacer2 = new Label("-");
    spacer2.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    reqname = new Label("Reqnamehere");
    reqname.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    tasks = new Label("Tasks");
    tasks.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    table = new TableView();
    HBox hb = new HBox();
    hb.setSpacing(5);
    hb.getChildren().addAll(projectname,spacer,reqname,spacer2,tasks);
    HBox hb11 = new HBox();
    hb11.setSpacing(500);
    hb11.getChildren().addAll(hb, search);
    HBox hb2 = new HBox();
    hb2.setSpacing(100);
    hb2.getChildren().addAll(manage, add);
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

    mainPane = new VBox(20);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(hb11, hb2, table);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }
}

