
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class ChangeTeamMembersGUI {
  class BooleanCell extends TableCell<TableData, Boolean> {
    private CheckBox checkBox;

    public BooleanCell() {
      checkBox = new CheckBox();
      checkBox.setDisable(true);
      checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
          if (isEditing())
            commitEdit(newValue == null ? false : newValue);
        }
      });
      this.setGraphic(checkBox);
      this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      this.setEditable(true);
    }

    @Override
    public void cancelEdit() {
      super.cancelEdit();
      checkBox.setDisable(true);
    }

    public void commitEdit(Boolean value) {
      super.commitEdit(value);

      checkBox.setDisable(true);
    }

    @Override
    public void startEdit() {
      super.startEdit();
      if (isEmpty()) {
        return;
      }
      checkBox.setDisable(false);
      checkBox.requestFocus();
    }

    @Override
    public void updateItem(Boolean item, boolean empty) {
      super.updateItem(item, empty);
      if (!isEmpty()) {
        checkBox.setSelected(item);
      }
    }
  }

  // Pojo class. A Javabean
  public class TableData {
    SimpleBooleanProperty include;

    SimpleStringProperty firstname;
    SimpleStringProperty lastname;

    // A javabean typically has a zero arg constructor
    // https://docs.oracle.com/javase/tutorial/javabeans/
    public TableData() {
    }

    // but can have others also
    public TableData(String firstNameIn,String lastnameIn, Boolean includeIn) {
      firstname = new SimpleStringProperty(firstNameIn);
      include = new SimpleBooleanProperty(includeIn);
      lastname = new SimpleStringProperty(lastnameIn);
    }

    /**
     * @return the firstname
     */
    public String getFirstName() {
      return firstname.get();
    }

    public String getLastName() {
      return lastname.get();
    }

    /**
     * @return the favorite
     */
    public Boolean isIncluded() {
      return include.get();
    }

    /**
     * @param include
     *            the favorite to set
     */
    public void setInclude(Boolean include) {
      this.include.setValue(include);
    }

    /**
     * @param firstName
     *            the stooge to set
     */
    public void setFirstName(String firstName) {
      this.firstname.setValue(firstName);
    }
    public void setLastName(String lastName) {
      this.lastname.setValue(lastName);
    }
  }

  // Model class - The model in mvc
  // Typically a representation of a database or nosql source
  public class TableModel {
    ObservableList<TableData> firstname = FXCollections.observableArrayList();
    ObservableList<TableData> lastname = FXCollections.observableArrayList();
    public TableModel() {
      firstname.add(new TableData("Larry","Larrowsky", false));
      firstname.add(new TableData("Moe","Moewsky", false));
      firstname.add(new TableData("Curly","Curlowsky", false));
    }

    public String displayModel() {
      StringBuilder sb=new StringBuilder();
      for (TableData firstname : firstname) {
        sb.append(firstname.getFirstName() + "=" + firstname.isIncluded() + "|");
      }
      return sb.toString();
    }

    /**
     * @return the firstnames
     */
    public ObservableList<TableData> getFirstname() {
      return firstname;
    }

    public void updatefirstname(TableData dataIn) {
      int index=firstname.indexOf(dataIn);
      firstname.set(index, dataIn);
    }
  }

  private TableModel model;

  private TableModel getModel() {
    if (model == null) {
      model = new TableModel();
    }
    return model;

  }
  private VBox mainPane;
  private Label title;
  private Button save;
  private Button cancel;

  public ChangeTeamMembersGUI() throws Exception {
    title = new Label("Team Members");
    title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
    save = new Button("Save");
    cancel = new Button("Cancel");
    final TableView<TableData> table = new TableView<>();
    final ObservableList<TableColumn<TableData, ?>> columns = table.getColumns();
    final TableModel model = getModel();

    TableColumn<TableData, String> firstNameCol = new TableColumn<>("First Name");
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    columns.add(firstNameCol);

    TableColumn lastNameCol = new TableColumn("Last Name");
    lastNameCol.setCellValueFactory(new PropertyValueFactory("last_name"));
    columns.add(lastNameCol);

    TableColumn birthdayCol = new TableColumn("Birthday");
    birthdayCol.setCellValueFactory(new PropertyValueFactory("birthday"));
    columns.add(birthdayCol);



    final TableColumn<TableData, CheckBox> includeColumn = new TableColumn<TableData, CheckBox>("Include");
    includeColumn.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<TableData, CheckBox>, ObservableValue<CheckBox>>() {

          @Override
          public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<TableData, CheckBox> arg0) {
            TableData data = arg0.getValue();
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().setValue(data.isIncluded());
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
              public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val,
                  Boolean new_val) {
                data.setInclude(new_val);
                checkBox.setSelected(new_val);
                model.updatefirstname(data);
              }
            });

            return new SimpleObjectProperty<CheckBox>(checkBox);
          }

        });
    HBox hbox = new HBox();
    hbox.setSpacing(50);
    hbox.getChildren().addAll(save,cancel);
    columns.add(includeColumn);
    table.setItems(model.getFirstname());
    mainPane = new VBox(20);
    mainPane.setSpacing(10);
    mainPane.setPadding(new Insets(25, 25, 25, 25));;
    mainPane.getChildren().addAll(title,table,hbox);


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