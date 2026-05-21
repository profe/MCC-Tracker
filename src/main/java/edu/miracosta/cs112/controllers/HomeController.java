package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.models.DietTracker;
import edu.miracosta.cs112.models.UserProfile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField goalField;

    @FXML
    private Label profileLabel;

    private UserProfile userProfile;

    @FXML
    private void handleCreateProfile() {
        String name = nameField.getText();
        String goal = goalField.getText();

        int age;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            profileLabel.setText("Please enter a valid age.");
            return;
        }

        userProfile = new UserProfile(name, age, 0.0, 0.0, goal, new DietTracker());
        profileLabel.setText("Profile created: " + userProfile.getName() + ", Age: " + userProfile.getAge() + ", Goal: " + userProfile.getGoal());
    }
}
