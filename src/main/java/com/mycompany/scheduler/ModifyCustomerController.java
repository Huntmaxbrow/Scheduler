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

public class ModifyCustomerController {
    
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
    TextField customerIdTF;
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
    Button saveBtn;
 
    
    @FXML
    int country_ID;
 
    @FXML
    String emptyMessage = "";
    
    @FXML
    String emptyCountryDiv = "";
    
    @FXML
    String incorrectNum = "";
    
    @FXML
    Alert alert = new Alert(AlertType.WARNING);
    
    @FXML
            
    Users currentUser = new Users();
    
      
    @FXML        
    Customers cusrrentCustomer = new Customers();
    
    @FXML
    Countries countries = new Countries();
    
    @FXML
    int countryID;
    
    @FXML
    FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions ();
    
    /**
    *Receives the user logged in
    *and sets current user to it.
    * 
    */
    @FXML
    public void receiveUser(Users user){
        
        
        currentUser = user;
        
        System.out.println(user.getPassword());
        
        
        
    }
    
      /**
    *Receives the user logged in
    *and sets current user to it.
    * 
    */
    @FXML
    public void receiveCustomer(Customers customer){
        
        
        cusrrentCustomer = customer;
        
        customerIdTF.setText(Integer.toString(cusrrentCustomer.getCustomer_ID()));
        customerNameTF.setText(cusrrentCustomer.getCustomer_Name());
        addressTF.setText(cusrrentCustomer.getAddress());
        postalCodeTF.setText(cusrrentCustomer.getPostal_Code());
        phoneTF.setText(cusrrentCustomer.getPhone());
        
        int countryID = firstLevelDivision.getDivisionFromList(customer.getDivision_ID()).getCountry_ID();
        
        
        countryCB.setValue(countries.getCountryFromList(countryID));
        setDivisionBox();
        divisionCB.setValue(firstLevelDivision.getDivisionFromList(customer.getDivision_ID()));
        
    }
    
    
    /**
    *sets the country box and assigns new customerID at start
    * 
    */
    @FXML
    public void initialize(){
        
        
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
    
    /**
    *Sets and filters the division Box
    *to display the division that matches
    *the selected country
    * 
    */
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
    *Checks if data is vaild.
    * 
    *@return true if data is vaild.
    * 
    */
    @FXML
    public Boolean vaildate() throws IOException{
        
        emptyMessage = "";
        incorrectNum = "";
        emptyCountryDiv = "";
                
        
        if(custIdLBL.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Customer ID";
            
            }
            else{
                
                 emptyMessage =  emptyMessage + ", Customer ID";
            }
        }
            
        if(customerNameTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Customer Name";
            
            }
            else{
                
                 emptyMessage = emptyMessage + ", Customer Name";
            }     
        }
        
        if(addressTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Address";
            
            }
            
            else{
                
                 emptyMessage = emptyMessage + ", Address";
            }
        }
            
        if(postalCodeTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
            emptyMessage = "Postal Code";
            
            }
            else{
                
                 emptyMessage = emptyMessage+ ", Postal Code";
            }
            
                          
         }
        if(phoneTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
            emptyMessage = "Phone";
            
            }
            else{
                
                 emptyMessage = emptyMessage+ ", Phone";
            }
        }   
       
        
        
        
       
            if(!phoneTF.getText().matches("[0-9]*") || phoneTF.getText().matches(".*[^a-zA-Z].*")){
          
                if(!phoneTF.getText().matches("[0-9-]*") ){
                    
                    incorrectNum = "Please enter a number.";
            
                }
                   
                   
                                     
         }
        
        if(countryCB.getValue() == null){
        
           
            
            emptyCountryDiv = "Please select a Country";
           
            
        }
        else if(divisionCB.getValue() == null){
        
            emptyCountryDiv = "Please select a Division";
        
        }
        
        if(emptyCountryDiv.isBlank() && incorrectNum.isBlank() && emptyMessage.isBlank()){
        
        
            
            
            return true;
        }
        
        else{
            
            return false;
        
        
        }
        
        
    }
    
   
 
    
    
     /**
    *Displays the alert for invaild information
    * 
    */
    @FXML
    public void displayAlert() throws IOException{
        
        
        String emptyAlert = "Please enter information for the following fields: " + emptyMessage;
        
            if(!emptyMessage.isBlank() && !emptyCountryDiv.isBlank() && !incorrectNum.isBlank()){
            
                alert.setContentText(emptyAlert + "\n\n" + emptyCountryDiv + "\n\n" + incorrectNum );
                alert.setHeight(300);
            
            }
            else if(!emptyMessage.isBlank() && !emptyCountryDiv.isBlank()){
            
                alert.setContentText(emptyAlert + "\n\n" + emptyCountryDiv);
                alert.setHeight(250);
            
            }
            else if(!emptyMessage.isBlank() && !incorrectNum.isBlank()){
            
                alert.setContentText(emptyAlert + "\n\n" + incorrectNum );
                alert.setHeight(250);
            
            }
            else if(!emptyCountryDiv.isBlank() && !incorrectNum.isBlank()){
            
                alert.setContentText(emptyCountryDiv + "\n\n" + incorrectNum );
                alert.setHeight(250);
            
            }
            else if(!emptyMessage.isBlank()){
            
                alert.setContentText(emptyAlert);
                alert.setHeight(150);
            
            }
            else if(!emptyCountryDiv.isBlank()){
            
                alert.setContentText(emptyCountryDiv);
                alert.setHeight(150);
            }
            else if(!incorrectNum.isBlank()){
                alert.setContentText(incorrectNum);
                alert.setHeight(150);
            }
        
            alert.showAndWait();
        
    }
    
    
     /**
    *Adds customer to database if data is vaild
    * 
    */
    @FXML
    public void saveCustomer() throws IOException{
        
        
        
        if(vaildate()){
        
        
               
        }
        else{
        
         
        
        }
        
    }
    
  
    /**
    *Changes back to the Customer home page
    * 
    */
    @FXML
    public void cancel() throws IOException{
        
        
        
        App.changePage("customers", currentUser);
        
    }
 
    
    
    

}

