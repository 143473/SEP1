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

public class ProjectOverviewGUI extends Application
{
  private Scene scene;
  private FlowPane mainPane;
  private Button add;
  private Button manage;
  private TextField search;
  private Label title;
  private TableView projects;

  public void start(Stage window){
    window.setTitle("Project Overview");

    add = new Button("ADD");
    manage = new Button("MANAGE");
    search = new TextField();
    title = new Label("Project Overview");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projects = new TableView();
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

    projects.getColumns().setAll(nameCol, descriptionCol,statusCol,teamMemberCol);
    projects.setPrefWidth(450);
    projects.setPrefHeight(300);
    projects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    VBox vbox = new VBox(20);
    vbox.setPadding(new Insets(25, 25, 25, 25));;
    vbox.getChildren().addAll(hb, hb2, projects);

    Scene scene = new Scene(vbox, 500, 475); // w x h
    window.setScene(scene);
    window.show();
  }
}
