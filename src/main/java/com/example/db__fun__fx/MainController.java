package com.example.db__fun__fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    //put our ID
    private Button cnctBtn;
    @FXML
    private Button signupBtn;
    @FXML
    private TextField usrNameTxt;
    @FXML
    private PasswordField pswrdFld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cnctBtn.setOnAction(new EventHandler<ActionEvent>() { //Eventhandler should be FX one
            @Override
            public void handle(ActionEvent event) {
                Utils.loginUser(event, usrNameTxt.getText(), pswrdFld.getText());
                usrNameTxt.setText(""); //this makes after login in was wrong, then it became empty
                pswrdFld.setText("");
                usrNameTxt.requestFocus();
            }
        });

        signupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Utils.changeScene(event, "sign-up.fxml", "Sign up!", null, null );
            }
        });
    }
}
