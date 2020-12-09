import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignTasksGUI2 {
    private VBox mainPane;
    private HBox topPane;

    private Label titleLabel;

    private TableView requirementTable;
    private TableView.TableViewSelectionModel defaultSelectionModel;
    private TableColumn requirementNameColumn;
    private TableColumn requirementDescriptionColumn;


    public void start(Stage window){
        window.setTitle("Assign Tasks");

        titleLabel = new Label("Requirement Table");
        titleLabel.setFont(new Font("Cambria", 32));

        topPane = new HBox(400);
        topPane.getChildren().addAll(titleLabel);

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

        mainPane = new VBox(10);
        mainPane.getChildren().addAll(topPane, requirementTable);
    }

    public VBox getMainPane()
    {
        return mainPane;
    }
}