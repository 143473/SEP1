
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

/**
 *  A class handling changing the team members
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
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

  private ProjectsAdapter projectsAdapter;
  private SepGUI sepGUI;

  private Project currentProject;

  /**
   *  2-argument constructor initializing all the parts of the GUI
   *
   */
  public ChangeTeamMembersGUI(ProjectsAdapter projectsAdapter, SepGUI sepGUI)
  {
    this.projectsAdapter = projectsAdapter;
    this.sepGUI = sepGUI;

    title = new Label();
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

  /**
   * Gets the main pane of the class
   * @return VBox Main pane
   */
  public VBox getMainPane()
  {
    return mainPane;
  }

  /**
   * Gets the button Cancel of type Button
   * @return Button cancelButton
   */
  public Button getCancel()
  {
    return cancelButton;
  }

  /**
   * Gets the button Add of type Button
   * @return Button addButtons
   */
  public Button getAddButton()
  {
    return addButton;
  }

  public void initializeCurrentProject(){
    currentProject = sepGUI.getManageProjectGUI().getSelectedProject();

    title.setText("Team Members of "+currentProject.getName());
  }
}