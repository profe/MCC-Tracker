package edu.miracosta.cs112.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    Button LoginEnabler;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void LoginEnabler(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        PlaceboLoginImpl login = new PlaceboLoginImpl();
        //This block of code checks for the valid user
        try {
            login.checkCredentials(username, password);

            // Load main screen
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/miracosta/cs112/main-view.fxml")
            );
            Parent root = loader.load();

            // Get the SAME controller that will be shown
            MainController mainController = loader.getController();
            mainController.enableTabs();  // This should change the tabs to be enabled

            // Swap to the main screen
            Stage stage = (Stage) LoginEnabler.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (InvalidCredentialsException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Authentication Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void homeController(){
        System.out.println("Welcome");
    }
}
