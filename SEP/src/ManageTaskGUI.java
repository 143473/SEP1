import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ManageTaskGUI
{
  private Label title;
  private TextField name;
  private TextField estimation;
  private TextField day;
  private TextField month;
  private TextField year;
  private ChoiceBox status;
  private ChoiceBox responsibleEmployee;
  private Label taskname;
  private Label estimatedT;
  private Label deadline;
  private Label statustxt;
  private Label responsibleEmp;
  private Button save;
  private Button cancel;
  private Button remove;
  private VBox mainPane;

  public ManageTaskGUI(){

    title = new Label("Manage Task");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    name = new TextField();
    estimation = new TextField();
    day = new TextField();
    month = new TextField();
    year = new TextField();
    status = new ChoiceBox();
    responsibleEmployee = new ChoiceBox();
    taskname = new Label("Name");
    estimatedT = new Label("Estimation");
    deadline = new Label("Deadline");
    statustxt = new Label("Status");
    responsibleEmp = new Label("Responsible Employee");
    save = new Button("Save");
    cancel = new Button("Cancel");
    remove = new Button("Remove");

    VBox vboxforlabels = new VBox();
    vboxforlabels.setSpacing(20);
    vboxforlabels.getChildren().addAll(taskname,estimatedT,deadline,statustxt,responsibleEmp);

    HBox hboxfordate = new HBox();
    hboxfordate.setSpacing(5);
    hboxfordate.getChildren().addAll(day,month,year);

    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(name,estimation,hboxfordate,status,responsibleEmployee);
    VBox vboxforbuttons = new VBox();
    vboxforbuttons.getChildren().addAll(save,cancel,remove);

    HBox hbox = new HBox();
    hbox.setSpacing(20);
    hbox.getChildren().addAll(vboxforlabels,vbox,vboxforbuttons);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,hbox);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }
}
