package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.models.UserProfile;
import edu.miracosta.cs112.models.WorkoutTracker;
import edu.miracosta.cs112.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the main workout dashboard page.
 * Handles user input, workout goal selection,
 * progress tracking, BMI calculation, and page navigation.
 */
public class WorkoutMainController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField ageField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    @FXML
    private Label bmiResultLabel;

    @FXML
    private Label recommendationLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private ListView<String> historyListView;

    private UserProfile userProfile;
    private String selectedGoal;
    private WorkoutTracker workout1;
    private WorkoutTracker workout2;
    private WorkoutTracker workout3;

    /**
     * Initializes the dashboard with default values.
     */
    @FXML
    private void initialize() {
        if (progressBar != null) {
            progressBar.setProgress(0.0);
        }

        if (progressLabel != null) {
            progressLabel.setText("0% Complete");
        }

        if (bmiResultLabel != null) {
            bmiResultLabel.setText("Enter height and weight");
        }

        if (recommendationLabel != null) {
            recommendationLabel.setText("Choose a goal to see recommendation");
        }

        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome! Save your information to begin.");
        }

        if (historyListView != null) {
            historyListView.getItems().add("No workouts saved yet");
        }
    }

    /**
     * Saves user information entered from the GUI.
     */
    @FXML
    private void saveUserInformation() {
        if (!isUserInputValid()) {
            showErrorMessage();
            return;
        }

        String name = nameField.getText().trim();
        double weight = Double.parseDouble(weightField.getText().trim());
        double height = Double.parseDouble(heightField.getText().trim());
        int age = Integer.parseInt(ageField.getText().trim());

        userProfile = new UserProfile(name, weight, height, age);

        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + name + "!");
        }

        updateBMI(weight, height);
        updateProgress();
        updateWorkoutHistory(name);
    }

    /**
     * Calculates and updates the BMI result label.
     */
    private void updateBMI(double weight, double height) {
        double heightInMeters;

        if (height > 3) {
            heightInMeters = height / 100.0;
        } else {
            heightInMeters = height;
        }

        double bmi = weight / (heightInMeters * heightInMeters);
        String category;

        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Normal";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        if (bmiResultLabel != null) {
            bmiResultLabel.setText("BMI: " + String.format("%.1f", bmi) + " (" + category + ")");
        }
    }

    /**
     * Updates the progress bar and progress label.
     */
    private void updateProgress() {
        double progress = 0.25;

        if (progressBar != null) {
            progressBar.setProgress(progress);
        }

        if (progressLabel != null) {
            progressLabel.setText("25% Complete");
        }
    }

    /**
     * Updates the workout history list view.
     */
    private void updateWorkoutHistory(String name) {
        if (historyListView != null) {
            historyListView.getItems().clear();
            historyListView.getItems().add(name + " saved profile information");
            historyListView.getItems().add("Ready to choose a fitness goal");
        }
    }

    /**
     * Opens the lose weight workout plan.
     */
    @FXML
    private void openLoseWeightScreen(ActionEvent event) throws IOException {
        selectedGoal = "Lose Weight";
        if (recommendationLabel != null) {
            recommendationLabel.setText("Recommended: walking, cycling, and cardio workouts");
        }
        workout1 = new WorkoutTracker("Walking", "weight1.png");
        workout2 = new WorkoutTracker("Jumping Jacks", "weight2.png");
        workout3 = new WorkoutTracker("Cycling", "weight3.png");
        openWorkoutScreen(event);
    }

    /**
     * Opens the stay active workout plan.
     */
    @FXML
    private void openStayActiveScreen(ActionEvent event) throws IOException {
        selectedGoal = "Stay Active";
        if (recommendationLabel != null) {
            recommendationLabel.setText("Recommended: stretching, jogging, and yoga workouts");
        }
        workout1 = new WorkoutTracker("Stretching", "active1.png");
        workout2 = new WorkoutTracker("Light Jogging", "active2.png");
        workout3 = new WorkoutTracker("Yoga", "active3.png");
        openWorkoutScreen(event);
    }

    /**
     * Opens the build muscle workout plan.
     */
    @FXML
    private void openBuildMuscleScreen(ActionEvent event) throws IOException {
        selectedGoal = "Build Muscle";
        if (recommendationLabel != null) {
            recommendationLabel.setText("Recommended: push ups, pull ups, and squats");
        }
        workout1 = new WorkoutTracker("Push Ups", "muscle1.png");
        workout2 = new WorkoutTracker("Pull Ups", "muscle2.png");
        workout3 = new WorkoutTracker("Squats", "muscle3.png");
        openWorkoutScreen(event);
    }

    /**
     * Opens the workout page and transfers workout data.
     */
    private void openWorkoutScreen(ActionEvent event) throws IOException {

        String name = "Guest";
        double weight = 70.0;
        double height = 1.75;
        int age = 18;

        if (isUserInputValid()) {
            name = nameField.getText().trim();
            weight = Double.parseDouble(weightField.getText().trim());
            height = Double.parseDouble(heightField.getText().trim());
            age = Integer.parseInt(ageField.getText().trim());
        }

        userProfile = new UserProfile(name, weight, height, age);

        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + name + "!");
        }

        updateBMI(weight, height);

        if (progressBar != null) {
            progressBar.setProgress(0.75);
        }

        if (progressLabel != null) {
            progressLabel.setText("75% Complete");
        }

        if (historyListView != null) {
            historyListView.getItems().clear();
            historyListView.getItems().add(name + " chose: " + selectedGoal);
            historyListView.getItems().add(workout1.getWorkoutName());
            historyListView.getItems().add(workout2.getWorkoutName());
            historyListView.getItems().add(workout3.getWorkoutName());
        }

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("workout-view.fxml"));
        Parent root = loader.load();

        WorkoutController controller = loader.getController();
        controller.setWorkoutPlan(selectedGoal, workout1, workout2, workout3);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Validates user input fields.
     */
    private boolean isUserInputValid() {

        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            return false;
        }

        try {
            double weight = Double.parseDouble(weightField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());

            return weight > 0 && height > 0 && age > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Displays an error alert for invalid input.
     */
    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a name, valid weight, valid height, and valid age before choosing a workout goal.");
        alert.showAndWait();
    }

    /**
     * Returns the current user profile.
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Returns the first workout.
     */
    public WorkoutTracker getWorkout1() {
        return workout1;
    }

    /**
     * Returns the second workout.
     */
    public WorkoutTracker getWorkout2() {
        return workout2;
    }

    /**
     * Returns the third workout.
     */
    public WorkoutTracker getWorkout3() {
        return workout3;
    }
}
