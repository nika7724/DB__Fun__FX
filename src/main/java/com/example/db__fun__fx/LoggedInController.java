package com.example.db__fun__fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
@FXML
    private Label welcomLbl;
@FXML
    private Label favortLbl;
@FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeScene(event, "main.fxml", "Log in", null, null);
            }
        });
    }
    public void setUserInfomation(String username, String favTeacher) {
        welcomLbl.setText("Welcome " + username + "!");
        favortLbl.setText("Your favorite teacher is " + favTeacher + "!");
    }
}
