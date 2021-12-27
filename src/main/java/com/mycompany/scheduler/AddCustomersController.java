package com.mycompany.scheduler;

import com.mycompany.scheduler.Model.Countries;
import com.mycompany.scheduler.Model.Customers;
import com.mycompany.scheduler.Model.FirstLevelDivisions;
import com.mycompany.scheduler.Model.Users;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class AddCustomersController {
    
    @FXML
    Label custIdLBL;
    @FXML
    Label CustomerNameLBL;
    @FXML
    Label addressLBL;
    @FXML
    Label postalCodeLBL;
    @FXML
    Label phoneLBL;  
    @FXML
    Label countryLBL;
    @FXML
    Label divisionLBL;
    
    @FXML
    TextField cusomerIdTF;
    @FXML
    TextField customerNameTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField postalCodeTF;
    @FXML
    TextField phoneTF;  
   
    @FXML
    ComboBox<Countries> countryCB;
    @FXML
    ComboBox<FirstLevelDivisions> divisionCB;
    
    @FXML
    Button cancelBtn;
    @FXML
    Button addBtn;
    
    
    
    @FXML
    int country_ID;
 
    

  
    @FXML
    String blankPassMessage = "Please Enter a password.";
    
    @FXML
    String blankUserMessage = "Please Enter a User.";
    
    @FXML
    String incorrectLoginMessage = "Inocrrect Login.";
    

    Alert alert = new Alert(AlertType.WARNING);
    
    Users currentUser = new Users();
    
    Customers customers = new Customers();
    
    Countries countries = new Countries();
    FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions ();
    
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
        
        cusomerIdTF.setText(Integer.toString(customers.getNewCustomerID()));
        setCountryBox();
      
        
    }
     /**
    *Sets the countries into the country Combo box 
    *and displays country name 
    * 
    */
    @FXML
    public void setCountryBox(){
        
        countryCB.setItems(countries.getAllCountries());
        
        countryCB.setConverter(new StringConverter<Countries>(){
            @Override
            public String toString(Countries country) {
                
                if(country == null){
                
                    return null;
                }
                else{
                
                        return country.getCountry();
                
                }
                
                
            }

            @Override
            public Countries fromString(String arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

          
        });
    }
    
    @FXML   
    public void setDivisionBox(){
        
        divisionCB.valueProperty().set(null);
        
        
        if(!(countryCB.getValue() == null)){
            
            country_ID = countryCB.getValue().getCountry_ID();
            
            divisionCB.setItems(FXCollections.observableArrayList(firstLevelDivision.getAllDivisions().stream().filter( d -> d.getCountry_ID() == country_ID).collect(Collectors.toList())));
        
            
            
            
        
        }
        
        
        
        divisionCB.setConverter(new StringConverter<FirstLevelDivisions>(){
            @Override
            public String toString(FirstLevelDivisions division) {
                  
                if(division == null){
                
                    return null;
                }
                else{
                
                        return division.getDivision();
                
                }
                
            }

            @Override
            public FirstLevelDivisions fromString(String arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
           

          
        });
        
        
        
    }
    
    
  
    /**
    *Changes back to the Customer home page
    * 
    */
    @FXML
    public void cancel() throws IOException{
        
        
        
        App.changePage("home", currentUser);
        
    }
 
    
    
    

}

