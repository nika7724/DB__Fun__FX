package com.example.db__fun__fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable { //interface there should be error, but just click ok

    @FXML
    private Button signupBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usrTxtFld;
    @FXML
    private PasswordField pswrdFld;
    @FXML
    private RadioButton douglasRB;
    @FXML
    private RadioButton andersRB;
    @FXML
    private RadioButton saifRB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup(); //choose one of the button
        douglasRB.setToggleGroup(toggleGroup);
        andersRB.setToggleGroup(toggleGroup);
        saifRB.setToggleGroup(toggleGroup);

        douglasRB.setSelected(true); //default RB

        signupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText(); //attach RB to String, whatever selected, get text
           if(!usrTxtFld.getText().trim().isEmpty() && !pswrdFld.getText().trim().isEmpty()) {
               Utils.signUpUser(event, usrTxtFld.getText(), pswrdFld.getText(), toggleName);
           } else {
               System.out.println("Please fill in all info"); // cunsor
               Alert alert = new Alert(Alert.AlertType.WARNING,"Please fill in all info"); //pop up window
          alert.show();
           }

            }
        });
        signupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeScene(event, "main.fxml", "Log in", null, null);
            }
        });

    }
}
