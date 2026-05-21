package edu.miracosta.cs112.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class MainController {

    @FXML
    Tab dietTrackerTab;

    @FXML
    Tab weightTrackerTab;

    @FXML
    Tab homeworkTrackerTab;

    @FXML
    Tab pomodoroTimerTab;

    @FXML
    Tab breakTimerTab;

    @FXML   //Line used for previous testing
    public void initialize() {

    }
    //This is called on start disabling tabs on Scene startup
    public void disableTabs() {
       // System.out.println("Disabled tabs");
        if (dietTrackerTab != null) dietTrackerTab.setDisable(true);
        if (weightTrackerTab != null) weightTrackerTab.setDisable(true);
        if (homeworkTrackerTab != null) homeworkTrackerTab.setDisable(true);
        if (pomodoroTimerTab != null) pomodoroTimerTab.setDisable(true);
        if (breakTimerTab != null) breakTimerTab.setDisable(true);
    }

    //This line is called when login in pressed and username and password are valid
    public void enableTabs() {
        //System.out.println("Enabled tabs");
        if (dietTrackerTab != null) dietTrackerTab.setDisable(false);
        if (weightTrackerTab != null) weightTrackerTab.setDisable(false);
        if (homeworkTrackerTab != null) homeworkTrackerTab.setDisable(false);
        if (pomodoroTimerTab != null) pomodoroTimerTab.setDisable(false);
        if (breakTimerTab != null) breakTimerTab.setDisable(false);
    }
}
