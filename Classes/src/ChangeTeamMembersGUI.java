import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ChangeTeamMembersGUI extends Application
{
  private Scene scene;
  private Label title;
  private Button save;
  private Button cancel;
  private TableView teammembers;

  public void start(Stage window){
    window.setTitle("Change Team Members");

    title = new Label("Team Members");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    save = new Button("Save");
    cancel = new Button("Cancel");
    teammembers = new TableView();


    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    TableColumn birthdayCol = new TableColumn("Birthday");
    birthdayCol.setCellValueFactory(new PropertyValueFactory("birthday"));

    teammembers.getColumns().setAll(nameCol,birthdayCol);
    teammembers.setPrefWidth(450);
    teammembers.setPrefHeight(300);
    teammembers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    HBox hbox = new HBox();
    hbox.setSpacing(50);
    hbox.getChildren().addAll(save,cancel);

    VBox vbox3 = new VBox(20);
    vbox3.setSpacing(10);
    vbox3.setPadding(new Insets(25, 25, 25, 25));;
    vbox3.getChildren().addAll(title,teammembers,hbox);

    Scene scene = new Scene(vbox3, 500, 475); // w x h
    window.setScene(scene);
    window.show();
  }
}
