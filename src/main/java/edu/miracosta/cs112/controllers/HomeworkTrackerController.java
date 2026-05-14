package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.models.HWTracker;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Optional;

/**
 * HomeworkTrackerController.java
 * Controller for the Homework Tracker tab.
 * Features: search, filter, editable priority, click-to-toggle status,
 * form validation with Use Default button, live stats.
 *
 * @author Samuel Carter
 * @version 5.0
 */
public class HomeworkTrackerController {

    @FXML private TableView<HWTracker>            assignmentTable;
    @FXML private TableColumn<HWTracker, String>  assignmentColumn;
    @FXML private TableColumn<HWTracker, String>  classColumn;
    @FXML private TableColumn<HWTracker, String>  dueDateColumn;
    @FXML private TableColumn<HWTracker, String>  priorityColumn;
    @FXML private TableColumn<HWTracker, String>  statusColumn;
    @FXML private Label                           totalLabel;
    @FXML private Label                           pendingLabel;
    @FXML private Label                           doneLabel;
    @FXML private TextField                       searchField;
    @FXML private ChoiceBox<String>               filterBox;

    private final ObservableList<HWTracker> assignments = FXCollections.observableArrayList();
    private FilteredList<HWTracker> filteredAssignments;

    // -------------------------------------------------------------------------
    // Initialize
    // -------------------------------------------------------------------------

    /**
     * Sets up columns, filtering, stats, and fade-in animation.
     */
    @FXML
    public void initialize() {

        // Assignment column — editable inline
        assignmentColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTitle()));
        assignmentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        assignmentColumn.setOnEditCommit(event -> {
            event.getRowValue().setTitle(event.getNewValue());
            updateStats();
        });

        // Class column — editable inline
        classColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCourse()));
        classColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        classColumn.setOnEditCommit(event -> {
            event.getRowValue().setCourse(event.getNewValue());
        });

        // Due Date column — editable inline
        dueDateColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDueDate()));
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setOnEditCommit(event -> {
            event.getRowValue().setDueDate(event.getNewValue());
        });

        // Priority column — dropdown to change (High / Medium / Low)
        priorityColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPriority()));
        priorityColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList("High", "Medium", "Low")));
        priorityColumn.setOnEditCommit(event -> {
            event.getRowValue().setPriority(event.getNewValue());
            assignmentTable.refresh();
        });

        // Status column — click to toggle Pending / Done
        statusColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().isSubmitted() ? "Done" : "Pending"));
        statusColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else if (item.equals("Done")) {
                    setText("Done");
                    setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
                } else {
                    setText("Pending");
                    setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold;");
                }
                // Click to toggle
                setOnMouseClicked(event -> {
                    HWTracker hw = getTableRow().getItem();
                    if (hw != null) {
                        hw.setSubmitted(!hw.isSubmitted());
                        assignmentTable.refresh();
                        updateStats();
                        applyFilter();
                    }
                });
            }
        });

        // Set up filtering
        filteredAssignments = new FilteredList<>(assignments, p -> true);
        assignmentTable.setItems(filteredAssignments);
        assignmentTable.setEditable(true);

        // Search listener
        searchField.textProperty().addListener((obs, o, n) -> applyFilter());

        // Filter box options
        filterBox.setItems(FXCollections.observableArrayList(
                "All", "Pending", "Done", "High Priority"));
        filterBox.setValue("All");
        filterBox.valueProperty().addListener((obs, o, n) -> applyFilter());

        // Fade-in on load
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), assignmentTable);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        updateStats();
    }

    // -------------------------------------------------------------------------
    // Handlers
    // -------------------------------------------------------------------------

    /**
     * Opens the Add Assignment dialog with validation and Use Default button.
     */
    @FXML
    private void handleAddClass() {
        Dialog<HWTracker> dialog = new Dialog<>();
        dialog.setTitle("Add Assignment");
        dialog.setHeaderText("New Assignment");

        ButtonType addType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addType, ButtonType.CANCEL);

        // Fields
        TextField courseField  = new TextField();
        TextField titleField   = new TextField();
        TextField dueDateField = new TextField();
        TextField pointsField  = new TextField();
        ChoiceBox<String> priorityBox = new ChoiceBox<>(
                FXCollections.observableArrayList("High", "Medium", "Low"));
        priorityBox.setValue("Medium");

        courseField.setPromptText("e.g. CS 112");
        titleField.setPromptText("e.g. UD02");
        dueDateField.setPromptText("YYYY-MM-DD or TBD");
        pointsField.setPromptText("e.g. 100");

        // Validation labels
        Label courseValid  = new Label();
        Label titleValid   = new Label();
        Label dateValid    = new Label();
        Label pointsValid  = new Label();

        // Use Default button
        Button defaultBtn = new Button("Use Default  (CS 112 / UD02)");
        defaultBtn.setOnAction(e -> {
            courseField.setText("CS 112");
            titleField.setText("UD02");
            dueDateField.setText("TBD");
            pointsField.setText("1");
            priorityBox.setValue("Medium");
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(16));

        grid.add(defaultBtn,                     0, 0, 3, 1);
        grid.add(new Label("Class:"),            0, 1);
        grid.add(courseField,                    1, 1);
        grid.add(courseValid,                    2, 1);
        grid.add(new Label("Assignment:"),       0, 2);
        grid.add(titleField,                     1, 2);
        grid.add(titleValid,                     2, 2);
        grid.add(new Label("Due Date:"),         0, 3);
        grid.add(dueDateField,                   1, 3);
        grid.add(dateValid,                      2, 3);
        grid.add(new Label("Points:"),           0, 4);
        grid.add(pointsField,                    1, 4);
        grid.add(pointsValid,                    2, 4);
        grid.add(new Label("Priority:"),         0, 5);
        grid.add(priorityBox,                    1, 5);

        dialog.getDialogPane().setContent(grid);

        Button addBtn = (Button) dialog.getDialogPane().lookupButton(addType);
        addBtn.setDisable(true);

        // Validation on each keystroke
        courseField.textProperty().addListener((obs, o, n) -> {
            setValid(courseValid, validateNotEmpty(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField, pointsField));
        });
        titleField.textProperty().addListener((obs, o, n) -> {
            setValid(titleValid, validateNotEmpty(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField, pointsField));
        });
        dueDateField.textProperty().addListener((obs, o, n) -> {
            setValid(dateValid, validateDate(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField, pointsField));
        });
        pointsField.textProperty().addListener((obs, o, n) -> {
            setValid(pointsValid, validatePoints(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField, pointsField));
        });

        dialog.setResultConverter(btn -> {
            if (btn == addType) {
                int pts = 0;
                try {
                    pts = Integer.parseInt(pointsField.getText().trim());
                } catch (NumberFormatException e) {
                    pts = 0;
                }
                String due = dueDateField.getText().trim().isEmpty()
                        ? "TBD" : dueDateField.getText().trim();
                HWTracker hw = new HWTracker(
                        courseField.getText().trim(),
                        titleField.getText().trim(),
                        due, pts, false);
                hw.setPriority(priorityBox.getValue());
                return hw;
            }
            return null;
        });

        Optional<HWTracker> result = dialog.showAndWait();
        result.ifPresent(hw -> {
            assignments.add(hw);
            updateStats();
        });
    }

    /**
     * Deletes the selected row. Shows alert if nothing is selected.
     */
    @FXML
    private void handleDeleteClass() {
        HWTracker selected = assignmentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            assignments.remove(selected);
            updateStats();
        } else {
            showAlert("Select a row to delete.");
        }
    }

    /**
     * Marks all visible assignments as done.
     */
    @FXML
    private void handleMarkAllDone() {
        for (HWTracker hw : assignments) {
            hw.setSubmitted(true);
        }
        assignmentTable.refresh();
        updateStats();
    }

    // -------------------------------------------------------------------------
    // Filter
    // -------------------------------------------------------------------------

    /**
     * Applies search text and filter dropdown to the filtered list.
     */
    private void applyFilter() {
        String search  = searchField.getText().toLowerCase().trim();
        String filter  = filterBox.getValue();

        filteredAssignments.setPredicate(hw -> {
            boolean matchesSearch = search.isEmpty()
                    || hw.getTitle().toLowerCase().contains(search)
                    || hw.getCourse().toLowerCase().contains(search);

            boolean matchesFilter;
            if (filter == null || filter.equals("All")) {
                matchesFilter = true;
            } else if (filter.equals("Pending")) {
                matchesFilter = !hw.isSubmitted();
            } else if (filter.equals("Done")) {
                matchesFilter = hw.isSubmitted();
            } else if (filter.equals("High Priority")) {
                matchesFilter = hw.getPriority().equals("High");
            } else {
                matchesFilter = true;
            }

            return matchesSearch && matchesFilter;
        });
    }

    // -------------------------------------------------------------------------
    // Validation helpers
    // -------------------------------------------------------------------------

    /** Returns true if all required fields are valid. */
    private boolean formIsValid(TextField course, TextField title,
                                TextField dueDate, TextField points) {
        return validateNotEmpty(course.getText()) == null
                && validateNotEmpty(title.getText()) == null
                && validateDate(dueDate.getText()) == null
                && validatePoints(points.getText()) == null;
    }

    /** Returns error string if blank, null if valid. */
    private String validateNotEmpty(String val) {
        if (val == null || val.trim().isEmpty()) {
            return "Required";
        }
        if (val.trim().length() < 2) {
            return "Too short";
        }
        return null;
    }

    /** Returns error string if bad date format, null if valid. */
    private String validateDate(String val) {
        if (val == null || val.trim().isEmpty() || val.trim().equals("TBD")) {
            return null;
        }
        if (!val.trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return "Use YYYY-MM-DD";
        }
        return null;
    }

    /** Returns error string if not a non-negative integer, null if valid. */
    private String validatePoints(String val) {
        if (val == null || val.trim().isEmpty()) {
            return null;
        }
        if (!val.trim().matches("\\d+")) {
            return "Numbers only";
        }
        return null;
    }

    /** Updates a validation label: green check if no error, red message if error. */
    private void setValid(Label lbl, String errorMsg) {
        if (errorMsg == null) {
            lbl.setText("ok");
            lbl.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 11px;");
        } else {
            lbl.setText(errorMsg);
            lbl.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 11px;");
        }
    }

    // -------------------------------------------------------------------------
    // Stats + alert
    // -------------------------------------------------------------------------

    /** Updates the Total, Pending, and Done stat labels. */
    private void updateStats() {
        int total     = assignments.size();
        long done     = assignments.stream().filter(HWTracker::isSubmitted).count();
        long pending  = total - done;
        totalLabel.setText(total + "  Total");
        pendingLabel.setText(pending + "  Pending");
        doneLabel.setText(done + "  Done");
    }

    /** Shows a simple information alert. */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}