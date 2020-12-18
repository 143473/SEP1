import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for managing the project information
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class ManageProjectGUI
{
  private ProjectsAdapter projectsAdapter;

  private Label title;
  private TextField nameField;
  private TextField descriptionField;

  private ChoiceBox statusBox;
  private ChoiceBox<AssignedEmployee> scrumMasterBox;
  private ChoiceBox<AssignedEmployee> productOwnerBox;
  private ChoiceBox<AssignedEmployee> projectCreatorBox;

  private Button saveButton;
  private Button cancelButton;
  private Button removeButton;
  private Button manageTeamMembersButton;

  private TableView<Project> projectsTable;
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
  private HBox bottomButtons;

  private MyActionListener listener;
  private MyListListener listListener;

  /**
   * 1-argument constructor initializing the GUI components
   * @param projectsAdapter adapter of the projects, requirements and tasks
   */
  public ManageProjectGUI(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    listListener = new MyListListener();
    listener = new MyActionListener();

    title = new Label("Manage Projects");
    title.getStyleClass().add("heading");
    projectNameLabel = new Label("Project name");
    projectDescriptionLabel = new Label("Project description");
    statusLabel = new Label("Status");
    scrumMasterLabel = new Label("Scrum Master");
    productOwnerLabel = new Label("Product Owner");
    projectCreatorLabel = new Label("Project Creator");
    nameField = new TextField();
    descriptionField = new TextField();

    statusBox = new ChoiceBox();
    ProgressStatus progressStatus = new ProgressStatus();
    String[] statuses = progressStatus.getStatuses();
    for (int i = 0; i < statuses.length; i++)
    {
      statusBox.getItems().add(statuses[i]);
    }

    scrumMasterBox = new ChoiceBox<AssignedEmployee>();
    productOwnerBox = new ChoiceBox<AssignedEmployee>();
    projectCreatorBox = new ChoiceBox<AssignedEmployee>();
    saveButton = new Button("Save");
    saveButton.setOnAction(listener);
    cancelButton = new Button("Cancel");
    removeButton = new Button("Remove");
    removeButton.setOnAction(listener);
    manageTeamMembersButton = new Button("Change Team Members");

    projectsTable = new TableView<Project>();

    projectCol = new TableColumn("Project name");
    projectCol.setCellValueFactory(new PropertyValueFactory("name"));

    vboxlabels = new VBox();
    vboxlabels.setSpacing(20);
    vboxlabels.getChildren().addAll(projectNameLabel,projectDescriptionLabel,statusLabel, scrumMasterLabel,productOwnerLabel,projectCreatorLabel);

    vbox = new VBox();
    vbox.setSpacing(10);
    vbox.getChildren().addAll(nameField, descriptionField,statusBox, scrumMasterBox,productOwnerBox,projectCreatorBox,manageTeamMembersButton);

    projectsTable.getColumns().setAll(projectCol);
    projectsTable.setPrefWidth(450);
    projectsTable.setPrefHeight(300);
    projectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    projectsTable.getSelectionModel().selectedItemProperty().addListener((listListener));

    vbox2 = new VBox();
    vbox2.setSpacing(10);
    vbox2.setAlignment(Pos.BOTTOM_RIGHT);
    vbox2.getChildren().addAll(projectsTable);

    bottomButtons = new HBox(8);
    bottomButtons.getChildren().addAll(saveButton,cancelButton,removeButton);

    hbox = new HBox(10);
    hbox.getChildren().addAll(vboxlabels,vbox, vbox2);

    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.getChildren().addAll(title,hbox,bottomButtons);
  }

  /**
   * Initializes the projectsTable
   */
  private void initializeTable(){
    projectsTable.getItems().clear();
    ProjectList projects = projectsAdapter.getAllProjects();

    for (int i = 0; i < projects.size(); i++) {
      projectsTable.getItems().add(projects.get(i));
    }
  }

  /**
   * Initializes the projectsTable and gets the mainPane
   * @return VBox mainPane
   */
  public VBox getMainPane()
  {
    initializeTable();
    return mainPane;
  }

  /**
   * Gets the cancelButton Button
   * @return Button cancelButton
   */
  public Button getCancel()
  {
    return cancelButton;
  }

  /**
   * Gets the saveButton Button
   * @return Button saveButton
   */
  public Button getSave()
  {
    return saveButton;
  }

  /**
   * Gets the manageTeamMembersButton Button
   * @return Button manageTeamMembersButton
   */
  public Button getManageTeamMembers()
  {
    return manageTeamMembersButton;
  }

  /**
   * List Listener to the changes
   */
  private class MyListListener implements ChangeListener<Project> {
    /**
     * Method what happens when any changes in the ListView occure
     * @param project project object as the observable value
     * @param oldProject Project type previous project who was being clicked at
     * @param newProject Project type new project who was being clicked at
     */
    public void changed(ObservableValue<? extends Project> project, Project oldProject, Project newProject)
    {
      Project myChosenProject = projectsTable.getSelectionModel().getSelectedItem();
      if (myChosenProject != null)
      {
        Project selectedProject = projectsAdapter.getSelectedProject(myChosenProject.getName());
        nameField.setText(selectedProject.getName());
        descriptionField.setText(selectedProject.getDescription());
        statusBox.getSelectionModel().select(selectedProject.getStatus());
        AssignedEmployeeList assignedEmployees = selectedProject.getAssignedEmployeeList();
        scrumMasterBox.getItems().clear();
        productOwnerBox.getItems().clear();
        projectCreatorBox.getItems().clear();

        for (int i = 0; i < assignedEmployees.size(); i++)
        {
          if(!scrumMasterBox.getItems().contains(assignedEmployees.get(i))){
            scrumMasterBox.getItems().add(assignedEmployees.get(i));
            productOwnerBox.getItems().add(assignedEmployees.get(i));
            projectCreatorBox.getItems().add(assignedEmployees.get(i));
          }
        }
        for (int i = 0; i < assignedEmployees.size(); i++) {
          if(assignedEmployees.get(i).getStatus().equals("Scrum Master")){
            scrumMasterBox.getSelectionModel().select(assignedEmployees.get(i));
          }
          if(assignedEmployees.get(i).getStatus().equals("Product Owner")){
            productOwnerBox.getSelectionModel().select(assignedEmployees.get(i));
          }
          if(assignedEmployees.get(i).getStatus().equals("Project Creator")){
            projectCreatorBox.getSelectionModel().select(assignedEmployees.get(i));
          }
        }
      }
    }
  }

  /**
   * Gets the selected project from the projectsTable
   * @return Project selected project
   */
  public Project getSelectedProject(){
    return projectsTable.getSelectionModel().getSelectedItem();
  }

  /**
   * Gets the selected project name from the projectsTable
   * @return String selected project name
   */
  public String getSelectedProjectName(){
    return projectsTable.getSelectionModel().getSelectedItem().getName();
  }

  /**
   * Handles the actions in this class
   */
  private class MyActionListener implements EventHandler<ActionEvent> {
    /**
     * Handles the actions of this class
     * @param e event that happens
     */
    public void handle(ActionEvent e) {
      if(e.getSource() == removeButton){
        if (!(projectsTable.getSelectionModel().getSelectedItem() == null))
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "Do you really want to delete project "+
                          projectsTable.getSelectionModel().getSelectedItem().getName()+"?",
                  ButtonType.YES, ButtonType.NO);
          alert.setTitle("Delete project");
          alert.setHeaderText(null);
          alert.showAndWait();

          Project project = projectsTable.getSelectionModel().getSelectedItem();
          if (alert.getResult() == ButtonType.YES)
          {
            projectsAdapter.deleteProject(project.getName());

            initializeTable();

            clearFields();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("Deleting successful");
            alert2.setContentText("Changes were saved successfully!");
            alert2.showAndWait();
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("No project was chosen!");
          alert.showAndWait();
        }

      }
      else if(e.getSource() == saveButton) {
        if (projectsTable.getSelectionModel().getSelectedItem() != null) {
          Project project = projectsTable.getSelectionModel().getSelectedItem();
          Project selectedProject = projectsAdapter.getSelectedProject(project.getName());
          boolean OK = true;
          if (nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("Project name cannot be empty!");
            alert.showAndWait();
            OK = false;
          }
          if (descriptionField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("Project description cannot be empty!");
            alert.showAndWait();
            OK = false;
          }
          ProjectList allProjects = projectsAdapter.getAllProjects();
          for (int i = 0; i < allProjects.size(); i++) {
            if (allProjects.get(i).getName().equals(nameField.getText()) &&
                    i !=(projectsTable.getSelectionModel().getSelectedIndex())) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setHeaderText("Warning");
              alert.setContentText("Project named " + nameField.getText() + " already exists!");
              alert.showAndWait();
              OK = false;
            }
          }

          if (scrumMasterBox.getSelectionModel().getSelectedItem().equals(productOwnerBox.getSelectionModel().getSelectedItem())
                  || scrumMasterBox.getSelectionModel().getSelectedItem().equals(projectCreatorBox.getSelectionModel().getSelectedItem())
                  || productOwnerBox.getSelectionModel().getSelectedItem().equals(projectCreatorBox.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("One person can be assigned only one status!");
            alert.showAndWait();
            OK = false;
          }

          if (scrumMasterBox.getSelectionModel().isEmpty() || projectCreatorBox.getSelectionModel().isEmpty() || productOwnerBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("All statuses have to be assigned!");
            alert.showAndWait();
            OK = false;
          }
          if (OK) {
            ProjectList projectList = projectsAdapter.getAllProjects();
            Project chosenProject = projectList.getProjectByName(projectsTable.getSelectionModel().getSelectedItem().getName());

            //setting the status of to "team member"
            AssignedEmployeeList assignedEmployeeList = chosenProject.getAssignedEmployeeList();
            for (int i = 0; i < assignedEmployeeList.size(); i++) {
              assignedEmployeeList.get(i).setStatus(3);
            }

            //setting the status of 3 chosen people
            chosenProject.getAssignedEmployeeList().get(scrumMasterBox.getSelectionModel().getSelectedIndex()).setStatus(0);
            chosenProject.getAssignedEmployeeList().get(projectCreatorBox.getSelectionModel().getSelectedIndex()).setStatus(2);
            chosenProject.getAssignedEmployeeList().get(productOwnerBox.getSelectionModel().getSelectedIndex()).setStatus(1);

            chosenProject.setName(nameField.getText());
            chosenProject.setDescription(descriptionField.getText());
            chosenProject.setStatus(statusBox.getSelectionModel().getSelectedIndex());
            chosenProject.setScrumMaster(new AssignedEmployee(scrumMasterBox.getSelectionModel().getSelectedItem().getFirstName(), scrumMasterBox.getSelectionModel().getSelectedItem().getLastName(), scrumMasterBox.getSelectionModel().getSelectedItem().getDateOfBirth(),0));
            chosenProject.setProductOwner(new AssignedEmployee(productOwnerBox.getSelectionModel().getSelectedItem().getFirstName(), productOwnerBox.getSelectionModel().getSelectedItem().getLastName(), productOwnerBox.getSelectionModel().getSelectedItem().getDateOfBirth(),0));
            chosenProject.setProjectCreator(new AssignedEmployee(projectCreatorBox.getSelectionModel().getSelectedItem().getFirstName(), projectCreatorBox.getSelectionModel().getSelectedItem().getLastName(), projectCreatorBox.getSelectionModel().getSelectedItem().getDateOfBirth(),0));

            projectsAdapter.saveProjects(projectList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Editing successful");
            alert.setContentText("Changes were saved successfully!");
            alert.showAndWait();

            initializeTable();

            clearFields();
          }
        }
        else{
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("No project was chosen!");
          alert.showAndWait();
        }
      }
    }
  }
  /**
   * Checks the validity of entered data after the change team members Button is called
   * @return boolean true if all the input data is correct, otherwise false
   */
  public boolean callChangeTeamMembers(){
    boolean OK = true;
    if(projectsTable.getSelectionModel().getSelectedItem() == null){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Warning");
      alert.setContentText("No project was chosen!");
      alert.showAndWait();
      OK = false;
    }
    return OK;
  }

  /**
   * Clears all the fields, sets the textFields to empty and clears the choice in choiceBoxes
   */
  public void clearFields(){
    nameField.setText("");
    descriptionField.setText("");
    statusBox.getSelectionModel().select(0);
    scrumMasterBox.getSelectionModel().clearSelection();
    productOwnerBox.getSelectionModel().clearSelection();
    projectCreatorBox.getSelectionModel().clearSelection();
  }
}
