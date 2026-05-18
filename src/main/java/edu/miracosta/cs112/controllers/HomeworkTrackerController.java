package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.models.HWTracker;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Optional;

public class HomeworkTrackerController {

    @FXML private TableView<HWTracker>            assignmentTable;
    @FXML private TableColumn<HWTracker, String>  dateColumn;
    @FXML private TableColumn<HWTracker, String>  classColumn;
    @FXML private TableColumn<HWTracker, String>  assignmentColumn;
    @FXML private TableColumn<HWTracker, String>  dueDateColumn;
    @FXML private TableColumn<HWTracker, Boolean> doneColumn;
    @FXML private TableColumn<HWTracker, String>  gradeColumn;
    @FXML private TableColumn<HWTracker, String>  notesColumn;


    private final ObservableList<HWTracker> assignments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        dateColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDate()));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(event ->
                event.getRowValue().setDate(event.getNewValue()));

        classColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCourse()));
        classColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        classColumn.setOnEditCommit(event ->
                event.getRowValue().setCourse(event.getNewValue()));

        assignmentColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTitle()));
        assignmentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        assignmentColumn.setOnEditCommit(event -> {
            event.getRowValue().setTitle(event.getNewValue());

        });

        dueDateColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDueDate()));
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setOnEditCommit(event ->
                event.getRowValue().setDueDate(event.getNewValue()));

        doneColumn.setCellValueFactory(data ->
                new SimpleBooleanProperty(data.getValue().isSubmitted()));
        doneColumn.setCellFactory(CheckBoxTableCell.forTableColumn(doneColumn));
        doneColumn.setOnEditCommit(event -> {
            event.getRowValue().setSubmitted(event.getNewValue());

        });

        gradeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getGrade()));
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gradeColumn.setOnEditCommit(event ->
                event.getRowValue().setGrade(event.getNewValue()));

        // Note is a non-static inner class so we use a lambda instead of method reference
        notesColumn.setCellValueFactory(data -> {
            String joined = data.getValue().getNotes().stream()
                    .map(note -> note.getContent())
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("");
            return new SimpleStringProperty(joined);
        });

        assignmentTable.setItems(assignments);
        assignmentTable.setEditable(true);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), assignmentTable);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();


    }

    @FXML
    private void handleAddClass() {
        Dialog<HWTracker> dialog = new Dialog<>();
        dialog.setTitle("Add Homework");
        dialog.setHeaderText("New Homework");

        ButtonType addType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addType, ButtonType.CANCEL);

        TextField dateField    = new TextField();
        TextField courseField  = new TextField();
        TextField titleField   = new TextField();
        TextField dueDateField = new TextField();
        TextField gradeField   = new TextField();
        TextField noteField    = new TextField();

        dateField.setPromptText("e.g. 2025-05-10");
        courseField.setPromptText("e.g. CS 112");
        titleField.setPromptText("e.g. Lab 3");
        dueDateField.setPromptText("YYYY-MM-DD or TBD");
        gradeField.setPromptText("e.g. A, 95%, TBD");
        noteField.setPromptText("Optional note...");

        Label courseValid = new Label();
        Label titleValid  = new Label();
        Label dateValid   = new Label();

        Button defaultBtn = new Button("Use Default  (CS 112 / UD02)");
        defaultBtn.setOnAction(e -> {
            dateField.setText("TBD");
            courseField.setText("CS 112");
            titleField.setText("UD02");
            dueDateField.setText("TBD");
            gradeField.setText("");
            noteField.setText("");
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(16));

        grid.add(defaultBtn,               0, 0, 3, 1);
        grid.add(new Label("Date:"),       0, 1);
        grid.add(dateField,                1, 1);
        grid.add(new Label("Class:"),      0, 2);
        grid.add(courseField,              1, 2);
        grid.add(courseValid,              2, 2);
        grid.add(new Label("Homework:"),   0, 3);
        grid.add(titleField,               1, 3);
        grid.add(titleValid,               2, 3);
        grid.add(new Label("Due Date:"),   0, 4);
        grid.add(dueDateField,             1, 4);
        grid.add(dateValid,                2, 4);
        grid.add(new Label("Grade:"),      0, 5);
        grid.add(gradeField,               1, 5);
        grid.add(new Label("Note:"),       0, 6);
        grid.add(noteField,                1, 6);

        dialog.getDialogPane().setContent(grid);

        Button addBtn = (Button) dialog.getDialogPane().lookupButton(addType);
        addBtn.setDisable(true);

        courseField.textProperty().addListener((obs, o, n) -> {
            setValid(courseValid, validateNotEmpty(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField));
        });
        titleField.textProperty().addListener((obs, o, n) -> {
            setValid(titleValid, validateNotEmpty(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField));
        });
        dueDateField.textProperty().addListener((obs, o, n) -> {
            setValid(dateValid, validateDate(n));
            addBtn.setDisable(!formIsValid(courseField, titleField, dueDateField));
        });

        dialog.setResultConverter(btn -> {
            if (btn == addType) {
                String due = dueDateField.getText().trim().isEmpty()
                        ? "TBD" : dueDateField.getText().trim();
                HWTracker hw = new HWTracker(
                        courseField.getText().trim(),
                        titleField.getText().trim(),
                        due, false);
                hw.setDate(dateField.getText().trim());
                hw.setGrade(gradeField.getText().trim());
                if (!noteField.getText().trim().isEmpty()) {
                    hw.addNote(noteField.getText().trim());
                }
                return hw;
            }
            return null;
        });

        Optional<HWTracker> result = dialog.showAndWait();
        result.ifPresent(hw -> {
            assignments.add(hw);

        });
    }

    @FXML
    private void handleDeleteClass() {
        HWTracker selected = assignmentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            assignments.remove(selected);

        } else {
            showAlert("Select a row to delete.");
        }
    }

    @FXML
    private void handleMarkAllDone() {
        for (HWTracker hw : assignments) {
            hw.setSubmitted(true);
        }
        assignmentTable.refresh();

    }

    private boolean formIsValid(TextField course, TextField title, TextField dueDate) {
        return validateNotEmpty(course.getText()) == null
                && validateNotEmpty(title.getText()) == null
                && validateDate(dueDate.getText()) == null;
    }

    private String validateNotEmpty(String val) {
        if (val == null || val.trim().isEmpty()) return "Required";
        if (val.trim().length() < 2)            return "Too short";
        return null;
    }

    private String validateDate(String val) {
        if (val == null || val.trim().isEmpty() || val.trim().equalsIgnoreCase("TBD")) return null;
        if (!val.trim().matches("\\d{4}-\\d{2}-\\d{2}")) return "Use YYYY-MM-DD";
        return null;
    }

    private void setValid(Label lbl, String errorMsg) {
        if (errorMsg == null) {
            lbl.setText("ok");
            lbl.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 11px;");
        } else {
            lbl.setText(errorMsg);
            lbl.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 11px;");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}