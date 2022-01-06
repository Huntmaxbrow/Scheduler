
package com.mycompany.scheduler.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LabUser
 */
public class Appointments {
 
    private int appointment_ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_Updated_By;
    private int customer_ID;
    private int user_ID;
    private int contact_ID;
    String contactName = "";
    String convertedTime = "";
    
    Contacts displayContact = new Contacts();
    
    private ObservableList<Appointments> allAppointmentsList = FXCollections.observableArrayList();
    
    /**********************************************
     * @param appointment_ID the appointment id.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param type the type of appointment.
     * @param startTime the starting time of the appointment.
     * @param endTime the ending time of the appointment.
     * @param create_date the creation date of the appointment
     * @param created_by the user ID for who created the appointment.
     * @param last_update the last date the appointment was updated.
     * @param last_Updated_By the user ID for who updated the appointment.
     * @param customer_ID the customer_ID associated to the appointment
     * @param user_ID the user_ID associated to the appointment
     * @param contact_ID the contact_ID associated to the appointment
    ************************************************/
    public Appointments(int appointment_ID, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, String create_date, String created_by,  String last_update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID) {
        this.appointment_ID = appointment_ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
        this.customer_ID = customer_ID;
        this.user_ID = user_ID;
        this.contact_ID = contact_ID;
    }

    /** 
     * Default Constructor.
     */
    public Appointments() {
        this.appointment_ID = 0;
        this.title = "";
        this.description = "";
        this.location = "";
        this.type = "";
        this.startTime = null;
        this.endTime = null;
        this.create_date = "";
        this.created_by = "";
        this.last_update = "";
        this.last_Updated_By = "";
        this.customer_ID = 0;
        this.user_ID = 0;
        this.contact_ID = 0;
    }
   
    /**
     * @return the appointment_ID
     */
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * @param appointment_ID the the appointment_ID to set
     */
    
    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /**
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * 
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the appointment type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
     /**
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the start time to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
   
    /**
     * @return the end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the end time to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the create date
     */
    public String getCreate_date() {
        return create_date;
    }

    /**
     * @param create_date the create date to set
     */
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    
    /**
     * @return the created by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created by date to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the last update
     */
    public String getLast_update() {
        return last_update;
    }

    /**
     * @param last_update the last update date to set
     */
    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
 
    
    /**
     * @return the last Updated By userID
     */
    public String getLast_Updated_By() {
        return last_Updated_By;
    }
    
    
    /**
     * @param last_Updated_By the last_Updated_By to set
     */
    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    /**
     * @return the customer_ID
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * @param customer_ID the customer_ID to set
     */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }
   
    /**
     * @return the user_ID
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * @param user_ID the user ID to set
     */
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
    
    /**
     * @return the contact ID
     */
    public int getContact_ID() {
        return contact_ID;
    }

    /**
     * @param contact_ID the contact ID to set
     */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }
    
    
     /**
     * @return the contact Name
     */
    public String getContact_Name() {
                    
        return displayContact.getContactFromList(getContact_ID()).getContact_Name();
        
        
    }

    /**
     * @param contact_Name the Contact Name to set
     */
    public void setContact_Name(String contact_Name) {
        this.contactName = contact_Name;
    }

    
 
    /**********************************************
     *
     * @return the all appointments list.
     *  
    ************************************************/
    
     public ObservableList<Appointments> getAllAppointmentList(){
     
         return this.allAppointmentsList;
     }
    
    //sql methods
    /**********************************************
     * Retrieves Appointment record from database that
     * matches the appointment ID and assigns it's values
     *  
     * @param id the appointment’s id to search for 
     *  
    ************************************************/
    public void getAppointment(int id){
         try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            String sql = "select * from Appointments WHERE Appointment_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
           
            while(rs.next()){
            setAppointment_ID(rs.getInt(1));
            setTitle(rs.getString(2));
            setDescription(rs.getString(3));
            setLocation(rs.getString(4));
            setType(rs.getString(5));
            setStartTime(rs.getTimestamp(6).toLocalDateTime());
            setEndTime(rs.getTimestamp(7).toLocalDateTime());
            setCreate_date(rs.getTimestamp(8).toString());
            setCreated_by(rs.getString(9));
            setLast_update(rs.getTimestamp(10).toString());
            setLast_Updated_By(rs.getString(11));
            setCustomer_ID(rs.getInt(12));
            setUser_ID(rs.getInt(13));
            setContact_ID(rs.getInt(14));
            
            }
            
            System.out.println(getType());
           
            
            //Close Connection
            con.close();
            
         }
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e)
      {
          System.out.println(e);
      }
    }  
    /**********************************************
     * update Appointment Records using current values.
     ************************************************/
    public void updateAppointment(){
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
          
            
            String sql;
            sql = "UPDATE appointments set Appointment_ID='" + appointment_ID + "'," +
                    "Title='" + title + "'," +
                    "Description='" + description + "'," +
                    "Location='" + location + "'," +
                    "Type='" + type + "'," +
                    "Start='" + startTime.toString() + "'," +
                    "End='" + endTime.toString() + "'," +
                    "Create_Date='" + create_date + "'," +
                    "Created_By='" + created_by + "'," +
                    "Last_update= UTC_TIMESTAMP," +
                    "Last_Updated_By='" + last_Updated_By + "'," + 
                    "Customer_ID='" + customer_ID + "'" +
                    "User_ID='" + user_ID + "'" +
                    "Contact_ID='" + contact_ID + "'" +
                    "WHERE Appointment_ID=" + appointment_ID + "";
       
            System.out.println(sql);
            stmt.executeUpdate(sql);
           
           
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.print(e);
         }
    } 
     
     /**********************************************
     * adds new Appointment record to database.
    * @param appointment_ID the appointment id.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param type the type of appointment.
     * @param startTime the starting time of the appointment.
     * @param endTime the ending time of the appointment.
     * @param create_date the creation date of the appointment
     * @param created_by the user ID for who created the appointment.
     * @param last_update the last date the appointment was updated.
     * @param last_Updated_By the user ID for who updated the appointment.
     * @param customer_ID the customer_ID associated to the appointment
     * @param user_ID the user_ID associated to the appointment
     * @param contact_ID the contact_ID associated to the appointment
    ************************************************/
    public void addAppointment(int appointment_ID, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, String created_by, String last_Updated_By, int customer_ID, int user_ID, int contact_ID){
         try{
           
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            //executing statement
            String sql;
            sql = "insert into customers (appointment_ID, title, description, location, type, startTime, endTime, create_date, created_by, last_update, last_Updated_By, customer_ID, user_ID, contact_ID)"
                    + " values ('" + appointment_ID + "','" +
                    title + "','" +
                    description + "','" +
                    location + "','" +
                    type + "','"+
                    startTime.toString() + "','" +
                    endTime.toString() + "'," +
                    "UTC_TIMESTAMP,'" +
                    created_by + "'," +
                    "UTC_TIMESTAMP,'" +
                    last_Updated_By + "','" +
                    customer_ID + "','" + 
                    user_ID + "','" + 
                    contact_ID + "'" + 
                     ")";
                    
           System.out.println(sql);
           stmt.executeUpdate(sql);
         
            
           con.close();
         }
         catch(SQLException e){
             System.out.print(e);
         }
          catch(Exception e)
      {
          System.out.println(e);
      }
    }
    
    /**********************************************
     * deletes Appointment from the database.
     * @param appointment_id the id of the appointment to delete.
    ************************************************/
    public void deleteCustomer(int appointment_id){
  
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
            

            String sql;
            sql = "DELETE FROM appointments WHERE Appointment_ID = " + "" + appointment_id + ";";
            

            stmt.execute(sql);  

            con.close();
         }
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e)
      {
          System.out.println(e);
      }
    }
    
    /**********************************************
      * retrieves all appointment from the database 
      * @return the appointment list.
    ************************************************/
    public ObservableList<Appointments> getAllAppointments(){
         
         allAppointmentsList.clear();
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
            

             
            String sql = sql = "select * from appointments";
            
           
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            while(rs.next()){
                
                Appointments appointment = new Appointments();
                appointment.setAppointment_ID(rs.getInt(1));
                appointment.setTitle(rs.getString(2));
                appointment.setDescription(rs.getString(3));
                appointment.setLocation(rs.getString(4));
                appointment.setType(rs.getString(5));
                appointment.setStartTime(rs.getTimestamp(6).toLocalDateTime());
                appointment.setEndTime(rs.getTimestamp(7).toLocalDateTime());
                appointment.setCreate_date(rs.getTimestamp(8).toString());
                appointment.setCreated_by(rs.getString(9));
                appointment.setLast_update(rs.getTimestamp(10).toString());
                appointment.setLast_Updated_By(rs.getString(11));
                appointment.setCustomer_ID(rs.getInt(12));
                appointment.setUser_ID(rs.getInt(13));
                appointment.setContact_ID(rs.getInt(14));

                allAppointmentsList.add(appointment);
            }
           
            
            
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         
          return allAppointmentsList; 
     }
    
    
     
    
    /**********************************************
      * retrieves a new unused Appointment ID
      * @return the customer list.
    ************************************************/
    public int getNewAppointmentID(){
         
         int newAppointmentID = 0;
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            

             
            String sql = sql = "select * from customers";
            
           
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            rs.last();
            
            
            newAppointmentID =  rs.getInt(1) + 1;
           
            
                       
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         
          return newAppointmentID; 
     }
    
    /**********************************************
    * retrieves a new unused Appointment ID
    * @return the customer list.
    ************************************************/
    public ZonedDateTime convertToLocal(LocalDateTime dateTime){
         
        ZonedDateTime localTime = dateTime.atZone(ZoneId.of("America/New_York"));
        
          
        System.out.println(localTime);
        return(localTime);
        
     }
    
    /**********************************************
    * retrieves a new unused Appointment ID
    * @return the customer list.
    ************************************************/
    public String formatTime(ZonedDateTime dateTime){
         
        
        
       DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
       
       String formattedTime = dateTime.format(format.withZone(ZoneId.of("America/New_York")));
       
       return formattedTime;
     }
    
    
   
  
    public static void main(String [] args){
        
        Appointments c1 = new Appointments();
    
        c1.getAppointment(2);
        System.out.print(c1.formatTime(c1.convertToLocal(c1.getStartTime())));
    }
    
}
