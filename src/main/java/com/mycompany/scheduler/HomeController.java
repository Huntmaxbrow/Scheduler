package com.mycompany.scheduler;

import com.mycompany.scheduler.Model.Users;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {
    
    @FXML
    Label nameLBL;
    @FXML
    Label titleLBL;
    @FXML
    Button viewCustomersBtn;
    @FXML
    Button viewAppointmentsBtn;
    
    @FXML
    String blankPassMessage = "Please Enter a password.";
    
    @FXML
    String blankUserMessage = "Please Enter a User.";
    
    @FXML
    String incorrectLoginMessage = "Inocrrect Login.";
    

    Alert alert = new Alert(AlertType.WARNING);
    
    Users currentUser = new Users();
    
    
    
    /**
    *Receives the user logged in
    *and sets current user to it.
    * 
    */
    @FXML
    public void receiveUser(Users user){
        
        
        currentUser = user;
        setName();
        
        
    }
    
    
    
    @FXML
    public void setName(){
    
        nameLBL.setText(currentUser.getUser_Name());
        
    }
    

}

