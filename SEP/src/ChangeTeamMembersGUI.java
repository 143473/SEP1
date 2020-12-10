
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChangeTeamMembersGUI {

  private VBox mainPane;
  private HBox buttonsPane;

  private Label title;
  private Button addButton;
  private Button removeButton;
  private Button cancelButton;

  private TableView teamMembersTable;
  private TableColumn firstNameColumn;
  private TableColumn lastNameColumn;
  private TableColumn birthdayColumn;

  public ChangeTeamMembersGUI()
  {
    title = new Label("Team Members");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    removeButton = new Button("Remove");
    cancelButton = new Button("Cancel");
    addButton = new Button("Add Team Member");

    firstNameColumn = new TableColumn("First Name");
    firstNameColumn.setCellFactory(new PropertyValueFactory<String, String>("First name"));


    lastNameColumn = new TableColumn("Last Name");
    lastNameColumn.setCellFactory(new PropertyValueFactory<String, String>("Last name"));


    birthdayColumn = new TableColumn("Birthday");
    birthdayColumn.setCellFactory(new PropertyValueFactory<String, String>("Birthday"));

    teamMembersTable = new TableView();
    teamMembersTable.getColumns().addAll(firstNameColumn, lastNameColumn, birthdayColumn);

    buttonsPane = new HBox();
    buttonsPane.setSpacing(50);
    buttonsPane.getChildren().addAll(addButton, removeButton,cancelButton);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,teamMembersTable,buttonsPane);


  }
  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getCancel()
  {
    return cancelButton;
  }

  public Button getAddButton()
  {
    return addButton;
  }
}