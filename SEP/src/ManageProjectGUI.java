import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManageProjectGUI
{

  private Label title;
  private TextField name;
  private TextField description;
  private ChoiceBox status;
  private ChoiceBox scrum;
  private ChoiceBox prjowner;
  private ChoiceBox prjcreator;
  private Button save;
  private Button cancel;
  private Button remove;
  private Button manageteammembers;
  private TableView projects;
  private Label projectname;
  private Label projectdescription;
  private Label statustxt;
  private Label scrummaster;
  private Label projectowner;
  private Label projectcreator;
  private VBox mainPane;
  private HBox bottomButtons;
  public ManageProjectGUI(){

    title = new Label("Manage Projects");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projectname = new Label("Project name");
    projectdescription = new Label("Project description");
    statustxt = new Label("Status");
    scrummaster = new Label("Scrum Master");
    projectowner = new Label("Project Owner");
    projectcreator = new Label("Project Creator");
    name = new TextField();
    description = new TextField();
    status = new ChoiceBox();
    scrum = new ChoiceBox();
    prjowner = new ChoiceBox();
    prjcreator = new ChoiceBox();
    save = new Button("Save");
    cancel = new Button("Cancel");
    remove = new Button("Remove");
    manageteammembers = new Button("Change Team Members");
    projects = new TableView();
    TableColumn projectCol = new TableColumn("Project name");
    projectCol.setCellValueFactory(new PropertyValueFactory("projectname"));

    VBox vboxlabels = new VBox(5);
    vboxlabels.setSpacing(20);
    vboxlabels.getChildren().addAll(projectname,projectdescription,statustxt, scrummaster,projectowner,projectcreator);

    VBox vbox = new VBox(5);
    vbox.setSpacing(10);
    vbox.getChildren().addAll(name, description,status, scrum,prjowner,prjcreator,manageteammembers);
    projects.getColumns().setAll(projectCol);

    projects.setPrefWidth(450);
    projects.setPrefHeight(300);
    projects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,remove, cancel);
    VBox vbox2 = new VBox(5);
    vbox2.setSpacing(10);
    vbox2.setAlignment(Pos.BOTTOM_LEFT);
    vbox2.getChildren().addAll(projects);
    HBox hbox = new HBox(vboxlabels,vbox, vbox2);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,hbox,bottomButtons);



  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getCancel()
  {
    return cancel;
  }

  public Button getSave()
  {
    return save;
  }

  public Button getManageTeamMembers()
  {
    return manageteammembers;
  }
}
