import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddTaskGUI
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
  private HBox bottomButtons;

  public AddTaskGUI(){

    title = new Label("Add Task");
    title.getStyleClass().add("heading");
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

    VBox vboxforlabels = new VBox();
    vboxforlabels.setSpacing(20);
    vboxforlabels.getChildren().addAll(taskname,estimatedT,deadline,statustxt,responsibleEmp);

    HBox hboxfordate = new HBox();
    hboxfordate.setSpacing(5);
    hboxfordate.getChildren().addAll(day,month,year);

    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(name,estimation,hboxfordate,status,responsibleEmployee);

    bottomButtons = new HBox(5);
    bottomButtons.getChildren().addAll(save,cancel);

    HBox hbox = new HBox();
    hbox.setSpacing(20);
    hbox.getChildren().addAll(vboxforlabels,vbox);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
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
}
