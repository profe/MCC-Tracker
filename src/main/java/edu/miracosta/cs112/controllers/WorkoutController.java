package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.MainApplication;
import edu.miracosta.cs112.models.WorkoutTracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Controller class for the workout display page.
 * Handles workout plan information, images,
 * workout tips, and navigation back to the main menu.
 */
public class WorkoutController {

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Label workout1Label;

    @FXML
    private Label workout2Label;

    @FXML
    private Label workout3Label;

    @FXML
    private TextArea tipArea;

    /**
     * Updates the workout page with selected workout information.
     */
    public void setWorkoutPlan(String goal, WorkoutTracker workout1,
                               WorkoutTracker workout2, WorkoutTracker workout3) {

        if (goal == null || workout1 == null || workout2 == null || workout3 == null) {
            return;
        }

        titleLabel.setText(goal + " Plan");

        workout1Label.setText(workout1.getWorkoutName());
        workout2Label.setText(workout2.getWorkoutName());
        workout3Label.setText(workout3.getWorkoutName());

        setWorkoutImages(
                workout1.getWorkoutImageName(),
                workout2.getWorkoutImageName(),
                workout3.getWorkoutImageName()
        );

        if (goal.equals("Build Muscle")) {
            tipArea.setText("Tip: Focus on proper form, eat enough protein, and slowly increase the difficulty over time.");
        }
        else if (goal.equals("Lose Weight")) {
            tipArea.setText("Tip: Stay consistent, keep your heart rate up, and combine workouts with healthy eating habits.");
        }
        else if (goal.equals("Stay Active")) {
            tipArea.setText("Tip: Move your body every day, even with light exercise, to improve energy and overall health.");
        }
    }

    /**
     * Sets all workout images on the page.
     */
    private void setWorkoutImages(String firstImage, String secondImage, String thirdImage) {
        setImageSafely(image1, firstImage);
        setImageSafely(image2, secondImage);
        setImageSafely(image3, thirdImage);
    }

    /**
     * Safely loads an image into an ImageView.
     */
    private void setImageSafely(ImageView imageView, String imageName) {
        String imagePath = "/images/" + imageName;
        InputStream inputStream = getClass().getResourceAsStream(imagePath);

        if (inputStream != null) {
            imageView.setImage(new Image(inputStream));
        } else {
            System.out.println("Missing image: " + imagePath);
        }
    }

    /**
     * Returns the user back to the main dashboard page.
     */
    @FXML
    private void goBackToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("workout-main-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
