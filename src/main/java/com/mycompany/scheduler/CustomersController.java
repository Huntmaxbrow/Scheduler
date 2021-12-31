package com.mycompany.scheduler;

import com.mycompany.scheduler.Model.Customers;
import com.mycompany.scheduler.Model.Users;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class CustomersController {
    
    @FXML
    Label customerLBL;
    @FXML
     Label deleteLbl;
    @FXML
    TableView<Customers> customerTable;
    @FXML
    TableColumn customerIDCol;
    @FXML
    TableColumn customerNameCol;
    @FXML
    TableColumn addressCol;
    @FXML
    TableColumn postalCodeCol;
    @FXML
    TableColumn PhoneCol;
    
    @FXML
    TableColumn divIdCol;
    
    @FXML
    String blankPassMessage = "Please Enter a password.";
    
    @FXML
    String blankUserMessage = "Please Enter a User.";
    
    @FXML
    String incorrectLoginMessage = "Inocrrect Login.";
    

    Alert alert = new Alert(AlertType.WARNING,"Are You Sure You Want To Delete This Customer?",  ButtonType.YES, ButtonType.NO);
    
    Users currentUser = new Users();
    
    Customers customers = new Customers();
    
    
    
    /**
    *Receives the user logged in
    *and sets current user to it.
    * 
    */
    @FXML
    public void receiveUser(Users user){
        
        
        currentUser = user;
        
        
        
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
        
        
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divIdCol.setCellValueFactory(new PropertyValueFactory<>("division_ID"));
        customerTable.setItems(customers.getAllCustomers());
        
        
        
        
    }
    /**
    *Changes back to the home page
    * 
    */
    @FXML
    public void home() throws IOException{
        
        
        
        App.changePage("home", currentUser);
        
    }
    /**
    *changes to the  add customer page
    *
    * 
    * @throws java.io.IOException
    */
    @FXML
    public void addCustomers() throws IOException{
    
        App.changePage("addCustomers", currentUser);
        
    }
    
    
     /**
    *changes to the modify customer page
    * and sends user and customer objects
    *
    * 
    * @throws java.io.IOException
    */
    @FXML
    public void modifyCustomers() throws IOException{
        
        
        if(customerTable.getSelectionModel().getSelectedItem() != null){
    
            App.changePage("modifyCustomer", currentUser, customerTable.getSelectionModel().getSelectedItem());
        }
        
    }
    
    
    
    
     /**
    *removes customer from database after confirming 
    *
    * 
    * @throws java.io.IOException
    */
    @FXML
    public void deleteCustomers() throws IOException{
    
        if(customerTable.getSelectionModel().getSelectedItem() != null){
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.YES){
                
                
                deleteLbl.setText("Deleted " + customerTable.getSelectionModel().getSelectedItem().getCustomer_Name() );
                customers.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomer_ID());
                SetTable();
                PauseTransition deletedCustMessage = new PauseTransition(Duration.seconds(3));
                
                deleteLbl.setVisible(true);
                deletedCustMessage.setOnFinished( e -> deleteLbl.setVisible(false));
                deletedCustMessage.play();

            }
        
        }
       
        
    }
    
    
    
    
    
    

}

