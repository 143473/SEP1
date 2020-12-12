import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

  private MyActionListener listener;
  private MyListListener listListener;

  public ManageProjectGUI(ProjectsAdapter projectsAdapter){
    this.projectsAdapter = projectsAdapter;

    listListener = new MyListListener();
    listener = new MyActionListener();

    title = new Label("Manage Project");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
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
    vbox2.getChildren().addAll(projectsTable, saveButton,cancelButton,removeButton);

    hbox = new HBox(10);
    hbox.getChildren().addAll(vboxlabels,vbox, vbox2);

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
  private class MyListListener implements ChangeListener<Project> {
    /**
     * Method what happens when any changes in the ListView occure
     * @param project project object as the observable value
     * @param oldProject Project type previous project who was being clicked at
     * @param newProject Project type new project who was being clicked at
     */
    public void changed(ObservableValue<? extends Project> project, Project oldProject, Project newProject)
    {

      Project temp = projectsTable.getSelectionModel().getSelectedItem();
      int index = projectsTable.getSelectionModel().getSelectedIndex();
      if (temp != null)
      {
        Project selectedProject = projectsAdapter.getSelectedProject(index);
        nameField.setText(selectedProject.getName());
        descriptionField.setText(selectedProject.getDescription());
        statusBox.setValue(selectedProject.getStatus());
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
        scrumMasterBox.getSelectionModel().select(selectedProject.getScrumMaster());
        productOwnerBox.getSelectionModel().select(selectedProject.getProductOwner());
        projectCreatorBox.getSelectionModel().select(selectedProject.getProjectCreator());
      }
    }
  }

  public Project getSelectedProject(){
    return projectsTable.getSelectionModel().getSelectedItem();
  }

  public int getSelectedIndex(){
    return projectsTable.getSelectionModel().getFocusedIndex();
  }

  private class MyActionListener implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
      if(e.getSource() == saveButton){
        int index = projectsTable.getSelectionModel().getSelectedIndex();
        Project selectedProject = projectsAdapter.getSelectedProject(index);

        boolean OK = true;

        if(!nameField.getText().isEmpty()){
          selectedProject.setName(nameField.getText());
        }
        else{
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Name of project cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        if(!descriptionField.getText().isEmpty()){
          selectedProject.setDescription(descriptionField.getText());
        }
        else{
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Description of project cannot be empty!");
          alert.showAndWait();
          OK = false;
        }

      }
      if(e.getSource() == removeButton){
        if (!(projectsTable.getSelectionModel().getSelectedItem() == null))
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "Do you really want to delete project "+projectsTable.getSelectionModel().getSelectedItem().getName()+"?", ButtonType.YES, ButtonType.NO);
          alert.setTitle("Delete project");
          alert.setHeaderText(null);

          alert.showAndWait();
          int index = projectsTable.getSelectionModel().getSelectedIndex();
          if (alert.getResult() == ButtonType.YES)
          {
            /*
            ProjectList allProjects = projectsAdapter.getAllProjects();
            allProjects.removeProject(getSelectedProject());
            projectsAdapter.saveProjects(allProjects);*/

            projectsAdapter.deleteProject(index);

            initializeTable();

            nameField.setText("");
            descriptionField.setText("");
            statusBox.getSelectionModel().clearSelection();
            scrumMasterBox.getSelectionModel().clearSelection();
            projectCreatorBox.getSelectionModel().clearSelection();
            productOwnerBox.getSelectionModel().clearSelection();

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
      else if(e.getSource() == saveButton){
        boolean OK = true;
        if(nameField.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Project name cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        if(descriptionField.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("Project description cannot be empty!");
          alert.showAndWait();
          OK = false;
        }
        ProjectList allProjects = projectsAdapter.getAllProjects();
        for (int i = 0; i < allProjects.size(); i++) {
          if(allProjects.get(i).getName().equals(nameField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning");
            alert.setContentText("Project named "+nameField.getText()+" already exists!");
            alert.showAndWait();
            OK = false;
          }
        }

        if(scrumMasterBox.getSelectionModel().getSelectedItem().equals(productOwnerBox.getSelectionModel().getSelectedItem())
        || scrumMasterBox.getSelectionModel().getSelectedItem().equals(projectCreatorBox.getSelectionModel().getSelectedItem())
        || productOwnerBox.getSelectionModel().getSelectedItem().equals(projectCreatorBox.getSelectionModel().getSelectedItem())){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Warning");
          alert.setContentText("One person can be assigned only one status!");
          alert.showAndWait();
          OK = false;
        }
        if(OK){
          int selectedIndex = projectsTable.getSelectionModel().getSelectedIndex();;
          Project selectedProject = projectsTable.getSelectionModel().getSelectedItem();
          projectsAdapter.deleteProject(selectedIndex);

          selectedProject.setName(nameField.getText());
          selectedProject.setDescription(descriptionField.getText());
          selectedProject.setStatus(statusBox.getSelectionModel().getSelectedIndex());
          selectedProject.setScrumMaster(scrumMasterBox.getSelectionModel().getSelectedItem());
          selectedProject.setProductOwner(productOwnerBox.getSelectionModel().getSelectedItem());
          selectedProject.setProjectCreator(projectCreatorBox.getSelectionModel().getSelectedItem());

          //setting the status of to "team member"
          AssignedEmployeeList assignedEmployeeList = selectedProject.getAssignedEmployeeList();
          for (int i = 0; i < assignedEmployeeList.size(); i++) {
            assignedEmployeeList.get(i).setStatus(3);
          }

          //setting the status of 3 chosen people
          selectedProject.getAssignedEmployeeList().get(scrumMasterBox.getSelectionModel().getSelectedIndex()).setStatus(0);
          selectedProject.getAssignedEmployeeList().get(projectCreatorBox.getSelectionModel().getSelectedIndex()).setStatus(2);
          selectedProject.getAssignedEmployeeList().get(productOwnerBox.getSelectionModel().getSelectedIndex()).setStatus(1);

          allProjects.addProject(selectedProject);
          projectsAdapter.saveProjects(allProjects);

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText("Editing successful");
          alert.setContentText("Changes were saved successfully!");
          alert.showAndWait();

          initializeTable();
        }
      }
    }
  }

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

  public void clearFields(){
    nameField.setText("");
    descriptionField.setText("");
    statusBox.getSelectionModel().select(0);
    scrumMasterBox.getSelectionModel().clearSelection();
    productOwnerBox.getSelectionModel().clearSelection();
    projectCreatorBox.getSelectionModel().clearSelection();
  }
}
