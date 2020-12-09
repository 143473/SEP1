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

public class ChangeTeamMembersGUI
{

  private Label title;
  private Button save;
  private Button cancel;
  private TableView teammembers;
  private VBox mainPane;

  public ChangeTeamMembersGUI(){

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

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,teammembers,hbox);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }
}
