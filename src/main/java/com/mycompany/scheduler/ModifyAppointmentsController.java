package com.mycompany.scheduler;

import com.mycompany.scheduler.Model.Appointments;
import com.mycompany.scheduler.Model.Contacts;
import com.mycompany.scheduler.Model.Countries;
import com.mycompany.scheduler.Model.Customers;
import com.mycompany.scheduler.Model.FirstLevelDivisions;
import com.mycompany.scheduler.Model.Users;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class ModifyAppointmentsController {
    
    @FXML
    Label apptIdLBL;
    @FXML
    Label titleLBL;
    @FXML
    Label descLBL;
    @FXML
    Label typeLBL;
    @FXML
    Label locationLBL;
    @FXML
    Label contactLBL;  
    @FXML
    Label startHourLBl;
    @FXML
    Label startMinuteLbl;
    @FXML
    Label endLbl;
    @FXML
    Label endHourLBl;
    @FXML
    Label endMinuteLbl;
    @FXML
    Label startLbl;
    
    @FXML
    TextField appointmentTF;
    @FXML
    TextField titleTF;
    @FXML
    TextField descTF;
    @FXML
    TextField typeTF;
    @FXML
    TextField locationTF;
    @FXML
    TextField contactTF;  
    
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;        
    
    
    @FXML
    ComboBox<Contacts> contactCB;
    @FXML
    ComboBox<Customers> customerCB;
    @FXML
    ComboBox startHourCB;
    @FXML
    ComboBox startMinCB;
    @FXML
    ComboBox startPmAmCB;
    @FXML
    ComboBox endHourCB;
    @FXML
    ComboBox endMinCB;
    @FXML
    ComboBox endPmAmCB;
    
    @FXML
    Button cancelBtn;
    @FXML
    Button addBtn;
 
    
    
    @FXML
    String emptyMessage = "";
    
    @FXML
    String emptyCustContactMessage = "";
    
    @FXML
    String startEmptyDateTime = "";
    
    @FXML
    String endEmptyDateTime = "";
    
    @FXML
    String incorrectDateTime = "";
    

    Alert alert = new Alert(AlertType.WARNING);
    
    Users currentUser = new Users();
    
    Customers customers = new Customers();
    
    Appointments appointments = new Appointments();
    Appointments currentAppointment = new Appointments();    
    
    Contacts contacts = new Contacts(); 
    
    LocalTime startTime;
    
    LocalTime endTime;
            
    private ObservableList<Customers> allCustomersList = FXCollections.observableArrayList();
    
    private ObservableList<Contacts> allContactsList = FXCollections.observableArrayList();
    
    
    LocalDateTime currentStartDateTime;
    LocalDateTime currentEndDateTime;
    
    LocalDateTime StartDateTimeEST;
    LocalDateTime endDateTimeEST;

    LocalDateTime StartDateTimeUTC;
    LocalDateTime endDateTimeUTC;

    /**
    *Receives the user logged in
    *and sets current user to it.
    * 
    */
    @FXML
    public void receiveUser(Users user){
        
        
        currentUser = user;
        
        System.out.println(currentUser.getPassword());
        
        
        
    }
    
    /**
    *Receives the Selected Appointments
    *and sets the JavaFX components.
    * 
    */
    @FXML
    public void receiveAppointments(Appointments appointment){
        
        
        currentAppointment = appointment;
        
        
        appointmentTF.setText(Integer.toString(currentAppointment.getAppointment_ID()));
        titleTF.setText(currentAppointment.getTitle());
        descTF.setText(currentAppointment.getDescription());
        locationTF.setText(currentAppointment.getLocation());
        typeTF.setText(currentAppointment.getType());
        
        
        int contactID = currentAppointment.getContact_ID();
        
        int customerID = currentAppointment.getCustomer_ID();
        
        contactCB.setValue(contacts.getContactFromList(contactID));
        customerCB.setValue(customers.getCustomerFromList(customerID));
        
        
        
        currentStartDateTime = currentAppointment.convertToLocal(currentAppointment.getStartTime());
        currentEndDateTime = currentAppointment.convertToLocal(currentAppointment.getEndTime());
        
        startDatePicker.setValue(currentStartDateTime.toLocalDate());
        endDatePicker.setValue(currentEndDateTime.toLocalDate());
        
        
        if(currentStartDateTime.getHour() > 12){
            startHourCB.setValue(currentStartDateTime.getHour()-12);
            startPmAmCB.setValue("PM");
        }
        else{
            startHourCB.setValue(currentStartDateTime.getHour());
            startPmAmCB.setValue("AM");
        }
        startMinCB.setValue(currentStartDateTime.getMinute());
        
        
        if(currentEndDateTime.getHour() > 12){
            endHourCB.setValue(currentEndDateTime.getHour()-12);
            endPmAmCB.setValue("PM");
        }
        else{
            endHourCB.setValue(currentEndDateTime.getHour());
            endPmAmCB.setValue("AM");
        }
       
        endMinCB.setValue(currentEndDateTime.getMinute());
        
        
        
        customerCB.setValue(customers.getCustomerFromList(currentAppointment.getUser_ID()));
        contactCB.setValue(contacts.getContactFromList(currentAppointment.getContact_ID()));
        
    }
    
    
    /**
    *sets the country box and assigns new customerID at start
    * 
    */
    @FXML
    public void initialize(){
        
        appointmentTF.setText(Integer.toString(currentAppointment.getAppointment_ID()));
        setContactBox();
        setCustomerBox();
        setTimeBoxes();
        
      
        
    }
     /**
    *Sets the contacts into the contact Combo box 
    *and displays contact name 
    * 
    */
    @FXML
    public void setContactBox(){
        
        contactCB.setItems(contacts.getAllContacts());
        
        contactCB.setConverter(new StringConverter<Contacts>(){
            @Override
            public String toString(Contacts contact) {
                
                if(contact == null){
                
                    return null;
                }
                else{
                
                        return contact.getContact_Name();
                
                }
                
                
            }

            @Override
            public Contacts fromString(String arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

          
        });
    }
    
    
    
     /**
    *Sets the contacts into the contact Combo box 
    *and displays contact name 
    * 
    */
    @FXML
    public void setTimeBoxes(){
        
        
        
         for(int i = 1; i < 13; i++){
         
             startHourCB.getItems().add(i);
             endHourCB.getItems().add(i);
         }
        
        for(int i = 0; i < 60; i++){
        
            startMinCB.getItems().add(i);
            endMinCB.getItems().add(i);
        }
        
        endPmAmCB.getItems().addAll("AM", "PM");
        startPmAmCB.getItems().addAll("AM", "PM");
        
        endMinCB.setConverter(new StringConverter<Integer>(){
            @Override
            public String toString(Integer i) {
                
                if(i == null){
                
                
                return null;
                }
                 
                if(i > 9){
                
                    return Integer.toString(i);
                }
                else{
                
                        return "0" + Integer.toString(i);
                
                }
                
                
            }

            @Override
            public Integer fromString(String arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

          
        });
        
        
        
        startMinCB.setConverter(new StringConverter<Integer>(){
            @Override
            public String toString(Integer i) {
                
                if(i == null){
                
                
                return null;
                }
                 
                if(i > 9){
                
                    return Integer.toString(i);
                }
                else{
                
                        return "0" + Integer.toString(i);
                
                }
                
                
            }

            @Override
            public Integer fromString(String arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

          
        });
    }
    
    
    /**
    *Sets the customers into the customer Combo box 
    *and displays customer name 
    * 
    */
    @FXML
    public void setCustomerBox(){
        
        customerCB.setItems(customers.getAllCustomers());
        
        customerCB.setConverter(new StringConverter<Customers>(){
            @Override
            public String toString(Customers customer) {
                
                if(customer == null){
                
                    return null;
                }
                else{
                
                        return customer.getCustomer_Name();
                
                }
                
                
            }

            @Override
            public Customers fromString(String arg0) {
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
        emptyCustContactMessage = "";
        startEmptyDateTime = "";
        endEmptyDateTime = "";
        incorrectDateTime = "";

            
        if(titleTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Title";
            
            }
            else{
                
                 emptyMessage = emptyMessage + ", Title";
            }     
        }
        
        if(descTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Description";
            
            }
            
            else{
                
                 emptyMessage = emptyMessage + ", Description";
            }
        }
        if(typeTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Type";
            
            }
            
            else{
                
                 emptyMessage = emptyMessage + ", Type";
            }
        }
        
        
        
            
        if(locationTF.getText().isBlank()){
        
            if(emptyMessage.isBlank()){
            
            emptyMessage = "Location";
            
            }
            else{
                
                 emptyMessage = emptyMessage+ ", Location";
            }
            
                          
         }
        
        
        
        
        if(contactCB.getValue() == null){
                   
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Please select a Contact";
            
            }
            else{
                
                 emptyMessage = emptyMessage+ ", Contact";
            }
           
            
        }
        if(customerCB.getValue() == null){
            
            
            
            
            
            if(emptyMessage.isBlank()){
            
                emptyMessage = "Please select a Customer";
            
            }
            else{
                
                 emptyMessage = emptyMessage+ ", Customer";
            }
        
        
        }
        
         if(startDatePicker.getValue() == null){
        
            
            
            startEmptyDateTime = "Date";
            
            
            
        }   
        
        
        
        
        
        if(startHourCB.getValue() == null){
                   
            if(startEmptyDateTime.isBlank()){
            
                startEmptyDateTime = "Hour";
            
            }
            else{
                
                 startEmptyDateTime = startEmptyDateTime+ ", Hour";
            }
           
            
        }
        if(startMinCB.getValue() == null){
                   
            if(startEmptyDateTime.isBlank()){
            
                startEmptyDateTime = "Minute";
            
            }
            else{
                
                 startEmptyDateTime = startEmptyDateTime+ ", Minute";
            }
           
            
        }
        if(startPmAmCB.getValue() == null){
                   
            if(startEmptyDateTime.isBlank()){
            
                startEmptyDateTime = "AM/PM";
            
            }
            else{
                
                 startEmptyDateTime = startEmptyDateTime+ ", AM/PM";
            }
           
            
        }
   
        if(endDatePicker.getValue() == null){
      
            endEmptyDateTime = "Date";
              
        } 
        
        if(endHourCB.getValue() == null){
                   
            if(endEmptyDateTime.isBlank()){
            
                endEmptyDateTime = "Hour";
            
            }
            else{
                
                 endEmptyDateTime = endEmptyDateTime + ", Hour";
            }
           
            
        }
        if(endMinCB.getValue() == null){
                   
            if(endEmptyDateTime.isBlank()){
            
                endEmptyDateTime = "Minute";
            
            }
            else{
                
                 endEmptyDateTime = endEmptyDateTime+ ", Minute";
            }
           
            
        }
        if(endPmAmCB.getValue() == null){
                   
            if(endEmptyDateTime.isBlank()){
            
                endEmptyDateTime = "AM/PM";
            
            }
            else{
                
                 endEmptyDateTime = endEmptyDateTime+ ", AM/PM";
            }
           
            
        }
        
        
        
        
        
        
        
      
        
        if(emptyMessage.isBlank() && emptyCustContactMessage.isBlank() && startEmptyDateTime.isBlank() && endEmptyDateTime.isBlank() && incorrectDateTime.isBlank() && getTime()){
        
           
           
            
                return true;

        }
        
        else{
            
       
            return false;
        
            
        }
        
        
    }
    
   
 
    
    
     /**
    *Displays the alert for invaild information
    *
    * 
    */

    
    @FXML
    public void displayAlert() throws IOException{
        
        
        String emptyAlert = "Please enter information for the following fields: " + emptyMessage;
        String startDateTimeAlert = "The Following Fields for Appointment Start must be Entered: " + startEmptyDateTime;
        String endtDateTimeAlert = "The Following Fields for Appointment End must be Entered: " + endEmptyDateTime; 
        
        if(!emptyMessage.isBlank() && !startEmptyDateTime.isBlank() && !endEmptyDateTime.isBlank()&& !incorrectDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + startDateTimeAlert + "\n\n" + endtDateTimeAlert + "\n\n" + incorrectDateTime);
            alert.setHeight(450);
        }
        if(!emptyMessage.isBlank() && !startEmptyDateTime.isBlank() && !endEmptyDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + startDateTimeAlert + "\n\n" + endtDateTimeAlert );
            alert.setHeight(350);

        }
        
        else if(!emptyMessage.isBlank() && !startEmptyDateTime.isBlank() &&  !incorrectDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + startDateTimeAlert + "\n\n" + incorrectDateTime);
            alert.setHeight(350);

        }
        else if(!emptyMessage.isBlank() && !endEmptyDateTime.isBlank() && !incorrectDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + endtDateTimeAlert + "\n\n" + incorrectDateTime);
            alert.setHeight(350);

        }
        
         else if(!startEmptyDateTime.isBlank() && !endEmptyDateTime.isBlank() && !incorrectDateTime.isBlank()){

            alert.setContentText(startDateTimeAlert + "\n\n" + endtDateTimeAlert + "\n\n" + incorrectDateTime);
            alert.setHeight(350);

        }
        
        
        else if(!emptyMessage.isBlank() && !startEmptyDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + startDateTimeAlert);
            alert.setHeight(280);

        }
        else if(!emptyMessage.isBlank() && !endEmptyDateTime.isBlank()){

            alert.setContentText(emptyAlert + "\n\n" + endtDateTimeAlert );
            alert.setHeight(280);

        }
        else if(!startEmptyDateTime.isBlank() && !endEmptyDateTime.isBlank()){

            alert.setContentText(startDateTimeAlert + "\n\n" + endtDateTimeAlert );
            alert.setHeight(280);

        }
        else if(!emptyMessage.isBlank()){

            alert.setContentText(emptyAlert);
            alert.setHeight(200);

        }
        else if(!startEmptyDateTime.isBlank()){

            alert.setContentText(startDateTimeAlert);
            alert.setHeight(200);
        }
        else if(!endEmptyDateTime.isBlank()){
            alert.setContentText(endtDateTimeAlert);
            alert.setHeight(200);
        }
        else if(!incorrectDateTime.isBlank()){
            alert.setContentText(incorrectDateTime);
            alert.setHeight(280);
        }

        alert.showAndWait();
        
    }
    
    
    
    /**
    *Retrieves and checks if time is vaild.
    * 
    *@return true if time is vaild.
    */ 
    
    @FXML
    public Boolean getTime() {
    
        int startHour = (int) startHourCB.getValue();
        int startMin = (int) startMinCB.getValue();

        int endHour = (int) endHourCB.getValue();
        int endMin = (int) endMinCB.getValue();


        if(startPmAmCB.getValue().equals("PM")){

            if(startHour == 12){

                startHour = 12; 
            }
            else{

            startHour = startHour + 12;
            }
        }

        if(endPmAmCB.getValue().equals("PM")){

            if(endHour == 12){

                endHour = 12; 
            }
            else{

            endHour = endHour + 12;
            }
        }
        
        if(startPmAmCB.getValue().equals("AM")){

            if(startHour == 12){

                startHour = 0; 
            }
        }

        if(endPmAmCB.getValue().equals("AM")){

            if(endHour == 12){

                endHour = 0; 
            }
        }
        
        startTime = LocalTime.of(startHour, startMin);
        endTime = LocalTime.of(endHour, endMin);
        
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
       
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        StartDateTimeEST = appointments.convertToEST(startDateTime);
        endDateTimeEST = appointments.convertToEST(endDateTime);
        
        StartDateTimeUTC = appointments.convertToUTC(StartDateTimeEST);
        endDateTimeUTC = appointments.convertToUTC(endDateTimeEST);
       
       
        if(StartDateTimeEST.isAfter(endDateTimeEST)){
        
            
            incorrectDateTime = "Start Time Must Be Before End Time";
        
        }
        else if(!appointments.inBusinessHours(startTime, endTime)){
        
            
            if(!incorrectDateTime.isBlank()){
            
                incorrectDateTime = incorrectDateTime  + "\n\nStart and End Time must be between 8:00 a.m. to 10:00 p.m. EST";
            }
            else{
                incorrectDateTime = "Start and End Time must be between 8:00 a.m. to 10:00 p.m. EST";
            }
        
        }
        
        
        else if(appointments.isOverlapping(StartDateTimeUTC, endDateTimeUTC)){
        
             incorrectDateTime = "The Date and Time is overlapping an existing Appointments";
        
        }
    
        if(incorrectDateTime.isEmpty()){
        
        
            return true;
        }
        else return false;
    }
    
     /**
    *Adds appointment to database if data is vaild
    * 
    */
    
    @FXML
    public void addAppointment() throws IOException{
        
        
        
        if(vaildate()){
            
            appointments.addAppointment(Integer.parseInt(appointmentTF.getText()), titleTF.getText(), descTF.getText(), locationTF.getText(), typeTF.getText(), StartDateTimeUTC, endDateTimeUTC,currentUser.getUser_Name(),currentUser.getUser_Name(),customerCB.getValue().getCustomer_ID(),currentUser.getUser_ID(), contactCB.getValue().getContact_ID()) ;
            App.changePage("home", currentUser);

        }
        else{
        
            displayAlert();
        
        }
        
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

