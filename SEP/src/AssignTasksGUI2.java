import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * A GUI tab containing components for displaying a list of requirements.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI2 {
    private SepGUI sepGUI;
    private VBox mainPane;
    private HBox topPane;
    private HBox bottomButtons;

    private Label projectName;

    private TableView requirementTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn requirementNameColumn;
    private TableColumn requirementDescriptionColumn;

    private Button continueButton;
    private Button goBackButton;


    /**
     * Constructor initializing the GUI components
     */
    public AssignTasksGUI2(SepGUI sepGUI){

        this.sepGUI = sepGUI;
        projectName = new Label();
        projectName.setFont(new Font("Cambria", 32));

        topPane = new HBox(5);
        topPane.getChildren().addAll(projectName);

        requirementTable = new TableView();
        requirementTable.setPrefHeight(290);
        requirementTable.setTableMenuButtonVisible(true);

        requirementNameColumn = new TableColumn("Requirement Name");
        requirementNameColumn.setPrefWidth(500);

        requirementDescriptionColumn = new TableColumn("Requirement Description");
        requirementDescriptionColumn.setPrefWidth(500);

        requirementNameColumn.setReorderable(false);
        requirementDescriptionColumn.setReorderable(false);

        requirementTable.getColumns().add(requirementNameColumn);
        requirementTable.getColumns().add(requirementDescriptionColumn);

        continueButton = new Button("Continue");
        goBackButton = new Button("Go back");

        bottomButtons = new HBox(5);
        bottomButtons.getChildren().addAll(continueButton, goBackButton);

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, requirementTable, bottomButtons);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }

    public Button getContinueButton()
    {
        return continueButton;
    }

    public Button getGoBackButton()
    {
        return goBackButton;
    }
    public boolean callContinueButton(){
    boolean gogo = true;
    if(sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      gogo = false;
    }
    else
    {
      String projectNameString = sepGUI.getAssignTasksGUI1().getAssignTasksTable().getSelectionModel().getSelectedItem().getName();
      projectName.setText(projectNameString);
      gogo =true;
    }
    return gogo;
  }
}