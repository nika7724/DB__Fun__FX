package com.example.db__fun__fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.Objects;

public class Utils {

    private static PreparedStatement pstmt = null;
    private static PreparedStatement psInsert = null;
    private static PreparedStatement psCheckUserExists = null;
    private static ResultSet rs = null;
    private static Connection connect = null;
    private static String url = System.getenv("url");
    private static String usr = System.getenv("usr");
    private static String pass = System.getenv("pass");

    //change scene
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favTeacher) {//"ActionEvent is automaticcally" pop up, you need to choose FX
    Parent root = null; //statge

        if(username != null& favTeacher != null) {
    try{
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxmlFile));
        root = loader.load();
        LoggedInController loggedInController = loader.getController();
        loggedInController.setUserInfomation(username, favTeacher);
    } catch(IOException e) {
        e.printStackTrace();
    }
        } else {
            try {
                root = FXMLLoader.load(Utils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public static void signUpUser(ActionEvent event, String userName, String password, String favTeacher) {
        try {
            connection();
            psCheckUserExists = connect.prepareStatement("SELECT * FROM users WHERE userName = ?");
            psCheckUserExists.setString(1, userName);
            rs = psCheckUserExists.executeQuery();

            if(rs.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User already exists");
                alert.show();
                           } else {
                psInsert = connect.prepareStatement("insert into users(userName, Password, favTeacher) values (?, ?, ?)");
                psInsert.setString(1, userName);
                psInsert.setString(2, password);
                psInsert.setString(3, favTeacher);
                psInsert.executeUpdate(); //update

                changeScene(event, "logged-in.fxml", "Welcome!", userName, favTeacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //close all elements
          closeConnection();
    }
}

public static void loginUser(ActionEvent event, String userName, String password) {
        try {
          connection();
          pstmt = connect. prepareStatement("SELECT password, favTeacher FROM users WHERE userName =?"); //?=place holder
        pstmt.setString(1, userName);
        rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()) { //doing the following stuff
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            } else {
                while(rs.next()) {
                    String retrievedPassword = rs.getString("password");
                    String retrievedFavTeacher = rs.getString("favTeacher");

                    if(retrievedPassword.equals(password)) {
                        changeScene(event, "logged-in.fxml", "Welcome!", userName, retrievedFavTeacher);
                    } else {
                        System.out.println("Password didn't match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                    }
                     }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //close all elements
            closeConnection();
        }
        }

private static void connection() {
        try {
            connect = DriverManager.getConnection(url, usr, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

private static void closeConnection() {
   if (rs != null) {
       try {
          rs.close();
               }catch  (SQLException e) {
           e.printStackTrace();
       }
   }
    if (psCheckUserExists != null) {
        try {
            psCheckUserExists.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    if (psInsert != null) {
        try {
            psInsert.close();
        }catch  (SQLException e) {
            e.printStackTrace();
        }
    }

    if (pstmt != null) {
        try {
            pstmt.close();
        }catch  (SQLException e) {
            e.printStackTrace();
        }
    }

    if (connect != null) {
        try {
            connect.close();
        }catch  (SQLException e) {
            e.printStackTrace();
        }
    }

    }
}