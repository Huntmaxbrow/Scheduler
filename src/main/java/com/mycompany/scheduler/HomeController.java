package com.mycompany.scheduler;

import com.mycompany.scheduler.Model.Appointments;
import com.mycompany.scheduler.Model.Users;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class HomeController {
    
    @FXML
    Label nameLBL;
    @FXML
    Label titleLBL;
    @FXML
    Button viewCustomersBtn;
    @FXML
    Button modifyAppointmentBtn;
    @FXML
    Button addAppointmentBtn;
    
    
    
    @FXML
    TableView<Appointments> appointmentTable;
    @FXML
    TableColumn appointmentIdCol;
    @FXML
    TableColumn titleCol;
    @FXML
    TableColumn descriptionCol;
    @FXML
    TableColumn locationCol;
    @FXML
    TableColumn contactCol;
    @FXML
    TableColumn typeCol;
    @FXML
    TableColumn startCol;
    @FXML
    TableColumn endCol;
    @FXML
    TableColumn customerIDCol;
    @FXML
    TableColumn userIDCol;
    
    
    
    @FXML
    String blankPassMessage = "Please Enter a password.";
    
    @FXML
    String blankUserMessage = "Please Enter a User.";
    
    @FXML
    String incorrectLoginMessage = "Inocrrect Login.";
    

    Alert alert = new Alert(AlertType.WARNING);
    
    Users currentUser = new Users();
    
    Appointments appointments = new Appointments();
    
    
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
    
    
    
    /**
    *Sets the Title to the name of the user
    *
    * 
    */
    @FXML
    public void setName(){
    
        nameLBL.setText(currentUser.getUser_Name());
        
    }
    
    
    /**
    *Starts the scene with the table set up
    * 
    */
    @FXML
    public void initialize(){
        
        
        
        SetTable();
        
    }
    
     /**
    *Sets properties for the Table column
    *and set the customer observable list
    *to the table
    * 
    */
    @FXML
    public void SetTable(){
        
        appointments.getAllAppointments();
        
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
      
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("user_ID"));
        
        
          endCol.setCellFactory(new Callback<TableColumn<Appointments, LocalDateTime>, TableCell<Appointments,LocalDateTime>>(){
        
            @Override public TableCell<Appointments,LocalDateTime> call(TableColumn<Appointments,LocalDateTime> timeTableColumn){
            
            
                return new TableCell<Appointments, LocalDateTime>(){
                
                    public void updateItem(LocalDateTime dateTime, boolean empty) {
                        super.updateItem(dateTime, empty);
                        
                        if(empty){
                            
                            setText("");
                        
                        }
                        else{
                        
                            setText(appointments.formatTime(appointments.convertToLocal(dateTime)));
                        }
                    }
                };
            }
        
        });
          
        startCol.setCellFactory(new Callback<TableColumn<Appointments, LocalDateTime>, TableCell<Appointments,LocalDateTime>>(){
        
            @Override public TableCell<Appointments,LocalDateTime> call(TableColumn<Appointments,LocalDateTime> timeTableColumn){
            
            
                return new TableCell<Appointments, LocalDateTime>(){
                
                    public void updateItem(LocalDateTime dateTime, boolean empty) {
                        super.updateItem(dateTime, empty);
                        
                        if(empty){
                            
                            setText("");
                        
                        }
                        else{
                        
                            setText(appointments.formatTime(appointments.convertToLocal(dateTime)));
                        }
                    }
                };
            }
        
        });
        
        appointmentTable.setItems(appointments.getAllAppointmentList());
        
        
        
        
    }
    

      
    /**
    *Sets the Title to the name of the user
    *
    * 
    */
    @FXML
    public void viewCustomers() throws IOException{
    
        App.changePage("customers", currentUser);
        
    }
    
}

