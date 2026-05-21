package edu.miracosta.cs112.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button hiButton;

    @FXML
    protected void onHelloButtonClicked(ActionEvent actionEvent) {
        System.out.println("Hello World!");
    }

    @FXML
    private void openBuildMuscleScreen(ActionEvent event) throws IOException {
        openWorkoutScreen(event, "Build Muscle");
    }

    @FXML
    private void openLoseWeightScreen(ActionEvent event) throws IOException {
        openWorkoutScreen(event, "Lose Weight");
    }

    @FXML
    private void openStayActiveScreen(ActionEvent event) throws IOException {
        openWorkoutScreen(event, "Stay Active");
    }

    private void openWorkoutScreen(ActionEvent event, String goal) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu.miracosta.cs112/workout-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}