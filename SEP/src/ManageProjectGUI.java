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
  private ProjectsAdapter projectsAdapter;

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

  private TableView projectsTable;
  private TableColumn projectCol;

  private Label projectname;
  private Label projectdescription;
  private Label statustxt;
  private Label scrummaster;
  private Label projectowner;
  private Label projectcreator;

  private VBox mainPane;
  private VBox vboxlabels;
  private VBox vbox;
  private VBox vbox2;
  private HBox hbox;

  public ManageProjectGUI(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    title = new Label("Manage Project");
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

    projectsTable = new TableView();

    projectCol = new TableColumn("Project name");
    projectCol.setCellValueFactory(new PropertyValueFactory("projectname"));

    vboxlabels = new VBox();
    vboxlabels.setSpacing(20);
    vboxlabels.getChildren().addAll(projectname,projectdescription,statustxt, scrummaster,projectowner,projectcreator);

    vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(name, description,status, scrum,prjowner,prjcreator,manageteammembers);

    projectsTable.getColumns().setAll(projectCol);
    projectsTable.setPrefWidth(450);
    projectsTable.setPrefHeight(300);
    projectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    vbox2 = new VBox();
    vbox2.setSpacing(10);
    vbox2.setAlignment(Pos.BOTTOM_RIGHT);
    vbox2.getChildren().addAll(projectsTable, save,cancel,remove);

    hbox = new HBox(vboxlabels,vbox, vbox2);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,hbox);



  }
  private void initializeTable(){
    projectsTable.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();

    for (int i = 0; i < projects.size(); i++) {
      projectsTable.getItems().add(projects.get(i));
    }
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
