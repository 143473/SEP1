import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;

/**
 * The 2nd part of the project creation user interface, that allows for
 * setting the status of the project and choosing the team members.
 * @author Claudiu Cordunianu
 * @version 1.0
 */
public class CreateProjectGUI2
{

  private EmployeeAdapter employeeAdapter;
  private MyActionListener listener;
  private MyListListener listListener;

  private VBox mainPane;
  private HBox hBoxPaneButton;
  private VBox newWindowPane;
  private HBox statusPane;
  private HBox searchPane;
  private HBox topButtonsPane;

  private Label title;
  private Label status;
  private Label searchByName;
  private Label tableTitle;

  private TextField searchField;

  private ComboBox statusBox;

  private ListView<Employee> employeeListView;
  private ListView teamMembersTable;


  private Button continueButton;
  private Button goBackButton;
  private Button searchButton;
  private Button addTeamMember;
  private Button add;
  private Button removeButton;

  public CreateProjectGUI2(EmployeeAdapter employeeAdapter)
  {
    this.employeeAdapter = employeeAdapter;
    listener = new MyActionListener();
    listListener = new MyListListener();

    title = new Label("Create a new project");
    Font titleFont = new Font(30);
    title.setFont(titleFont);

    status = new Label("Project's status: ");
    statusBox = new ComboBox();
    tableTitle = new Label("List of Team Members");

    searchByName = new Label("Search by name: ");
    searchField = new TextField();
    searchButton = new Button("Search");

    statusPane = new HBox(5);
    statusPane.getChildren().addAll(status, statusBox);

    searchPane = new HBox(5);
    searchPane.getChildren().addAll(searchByName, searchField, searchButton);

    /*gridPane = new GridPane();
    gridPane.addRow(0, status, statusBox);
    gridPane.addRow(1, searchByName, searchField, searchButton);*/

    employeeListView = new ListView<Employee>();
    employeeListView.setPrefHeight(120);
    employeeListView.getSelectionModel().selectedItemProperty().addListener((listListener));

    teamMembersTable = new ListView<AssignedEmployee>();

    continueButton = new Button("Continue");
    goBackButton = new Button("Go back");
    addTeamMember = new Button("Add Team Member");
    removeButton = new Button("Remove Member");

    topButtonsPane = new HBox(5);
    topButtonsPane.getChildren().addAll(addTeamMember, removeButton);

    add = new Button("Add");

    hBoxPaneButton = new HBox(5);
    hBoxPaneButton.getChildren().addAll(continueButton, goBackButton);

    mainPane = new VBox(5);
    mainPane.getChildren().addAll(title, statusPane, topButtonsPane, tableTitle, teamMembersTable, hBoxPaneButton);

    newWindowPane = new VBox(searchPane, employeeListView, add);
  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      Employee temp = employeeListView.getSelectionModel().getSelectedItem();

      if(e.getSource() == searchButton){
        String searchingFor = searchField.getText();
        employeeListView.getItems().clear();
        EmployeeList chosenEmployees = employeeAdapter.getEmployeesByName(searchingFor);
        for (int i = 0; i < chosenEmployees.size(); i++) {
          employeeListView.getItems().add(chosenEmployees.get(i));
        }
      }
    }
  }

  public void initializeListView()
  {
    employeeListView.getItems().clear();
    EmployeeList employees = employeeAdapter.getAllEmployees();
    employeeListView.getItems().add(null);
    for (int i = 0; i < employees.size(); i++)
    {
      employeeListView.getItems().add(employees.get(i));
    }

    for (int i = 0; i < employees.size(); i++) {
      employeesTable.getItems().add(employees.get(i));
    }
  }

  public VBox getMainPane()
  {
    return mainPane;
  }

  public Button getGoBackButton()
  {
    return goBackButton;
  }

  public Button getContinueButton()
  {
    return continueButton;
  }

  public Button getAddTeamMember()
  {
    return addTeamMember;
  }

  public VBox getNewWindowPane()
  {
    initializeListView();
    return newWindowPane;
  }

  private class MyListListener implements ChangeListener<Employee>
  {
    public void changed(ObservableValue<? extends Employee> employee, Employee oldEmployee, Employee newEmployee)
    {



    }
  }

  public Button getAdd()
  {
    return add;
  }

}
  /*private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      if (e.getSource() == addTeamMember)
      {
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Choose Team Member");
        scene = new Scene(newWindowPane);
        newWindow.setScene(scene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
      }
    }
  }
}

*/