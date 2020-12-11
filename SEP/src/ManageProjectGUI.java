import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  private TextField nameField;
  private TextField descriptionField;

  private ChoiceBox statusBox;
  private ChoiceBox scrumMasterBox;
  private ChoiceBox projectOwnerBox;
  private ChoiceBox projectCreatorBox;

  private Button saveButton;
  private Button cancelButton;
  private Button removeButton;
  private Button manageTeamMembersButton;

  private TableView projectsTable;
  private TableColumn projectCol;

  private Label projectNameLabel;
  private Label projectDescriptionLabel;
  private Label statusLabel;
  private Label scrumMasterLabel;
  private Label productOwnerLabel;
  private Label projectCreatorLabel;

  private VBox mainPane;
  private VBox vboxlabels;
  private VBox vbox;
  private VBox vbox2;
  private HBox hbox;


  private MyListListener listListener;

  public ManageProjectGUI(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    listListener = new MyListListener();

    title = new Label("Manage Project");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    projectNameLabel = new Label("Project name");
    projectDescriptionLabel = new Label("Project description");
    statusLabel = new Label("Status");
    scrumMasterLabel = new Label("Scrum Master");
    productOwnerLabel = new Label("Project Owner");
    projectCreatorLabel = new Label("Product Creator");
    nameField = new TextField();
    descriptionField = new TextField();

    statusBox = new ChoiceBox();
    ProgressStatus progressStatus = new ProgressStatus();
    String[] statuses = progressStatus.getStatuses();
    for (int i = 0; i < statuses.length; i++)
    {
      statusBox.getItems().add(statuses[i]);
    }

    scrumMasterBox = new ChoiceBox();
    projectOwnerBox = new ChoiceBox();
    projectCreatorBox = new ChoiceBox();
    saveButton = new Button("Save");
    cancelButton = new Button("Cancel");
    removeButton = new Button("Remove");
    manageTeamMembersButton = new Button("Change Team Members");

    projectsTable = new TableView();

    projectCol = new TableColumn("Project name");
    projectCol.setCellValueFactory(new PropertyValueFactory("name"));

    vboxlabels = new VBox();
    vboxlabels.setSpacing(20);
    vboxlabels.getChildren().addAll(projectNameLabel,projectDescriptionLabel,statusLabel, scrumMasterLabel,productOwnerLabel,projectCreatorLabel);

    vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(nameField, descriptionField,statusBox, scrumMasterBox,projectOwnerBox,projectCreatorBox,manageTeamMembersButton);

    projectsTable.getColumns().setAll(projectCol);
    projectsTable.setPrefWidth(450);
    projectsTable.setPrefHeight(300);
    projectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    projectsTable.getSelectionModel().selectedItemProperty().addListener((listListener));

    vbox2 = new VBox();
    vbox2.setSpacing(10);
    vbox2.setAlignment(Pos.BOTTOM_RIGHT);
    vbox2.getChildren().addAll(projectsTable, saveButton,cancelButton,removeButton);

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
    initializeTable();
    return mainPane;
  }

  public Button getCancel()
  {
    return cancelButton;
  }

  public Button getSave()
  {
    return saveButton;
  }

  public Button getManageTeamMembers()
  {
    return manageTeamMembersButton;
  }

  /**
   * List Listener to the changes
   */
  private class MyListListener implements ChangeListener<Project>
  {
    /**
     * Method what happens when any changes in the ListView occure
     * @param project project object as the observable value
     * @param oldProject Project type previous project who was being clicked at
     * @param newProject Project type new project who was being clicked at
     */
    public void changed(ObservableValue<? extends Project> project, Project oldProject, Project newProject)
    {

      Project temp = (Project)projectsTable.getSelectionModel().getSelectedItem();
      int index = projectsTable.getSelectionModel().getSelectedIndex();
      if (temp != null)
      {
        Project selectedProject = projectsAdapter.getSelectedProject(index);
        nameField.setText(selectedProject.getName());
        descriptionField.setText(selectedProject.getDescription());
        statusBox.setValue(selectedProject.getStatus());
      }

    }
  }
}
