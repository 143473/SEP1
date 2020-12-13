import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManageRequirementGUI
{

  private Label title;
  private TextField name;
  private TextField userstory;
  private TextField estimation;
  private TextField day;
  private TextField month;
  private TextField year;

  private ChoiceBox status;
  private ChoiceBox responsibleEmployee;

  private TableView<Project> projectsTable;
  private TableColumn projectCol;

  private Label reqname;
  private Label userstorytxt;
  private Label estimatedT;
  private Label deadline;
  private Label statustxt;
  private Label responsibleEmp;

  private Button save;
  private Button cancel;
  private Button remove;

  private VBox mainPane;
  private HBox bottomButtons;
  private GridPane requirementForm;
  private HBox datePane;

  public ManageRequirementGUI(){

  title = new Label("Manage Requirements");
  title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
  name = new TextField();
  userstory = new TextField();
  estimation = new TextField();

  day = new TextField();
  day.setPromptText("dd");
  day.setMaxWidth(40);
  month = new TextField();
  month.setPromptText("mm");
  month.setMaxWidth(40);
  year = new TextField();
  year.setPromptText("yyyy");
  year.setMaxWidth(60);

  datePane = new HBox(5);
  datePane.getChildren().addAll(day,month,year);

  status = new ChoiceBox();
  responsibleEmployee = new ChoiceBox();
  reqname = new Label("Name");
  userstorytxt = new Label("User Story");
  estimatedT = new Label("Estimation");
  deadline = new Label("Deadline");
  statustxt = new Label("Status");
  responsibleEmp = new Label("Responsible Employee");
  save = new Button("Save");
  cancel = new Button("Cancel");
  remove = new Button("Remove");

  requirementForm = new GridPane();
  requirementForm.setHgap(5);
  requirementForm.setVgap(5);
  requirementForm.addRow(0,reqname,name);
  requirementForm.addRow(1,userstorytxt, userstory);
  requirementForm.addRow(2,estimatedT,estimation);
  requirementForm.addRow(3,deadline,datePane);
  requirementForm.addRow(4,statustxt,status);
  requirementForm.addRow(5,responsibleEmp,responsibleEmployee);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,cancel,remove);


    mainPane = new VBox(5);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,requirementForm,bottomButtons);


}
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getCancel()
  {
    return cancel;
  }
}
