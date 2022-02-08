
package com.mycompany.scheduler.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LabUser
 */
public class Users {
 
    private int user_ID;
    private String user_Name;
    private String password;
  
    private LocalDateTime timeStamp;
    
    private BufferedWriter bw;
    private ObservableList<Users> allUsers = FXCollections.observableArrayList();

    
    /**********************************************
     * @param user_ID the user id.
     * @param user_Name the name of the user.
     * @param password the user's password
    ************************************************/
    public Users(int user_ID, String user_Name, String password) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.password = password;
       
    }

    /** 
     * Default Constructor.
     */
    public Users() {
        this.user_ID = 0;
        this.user_Name = "";
        this.password = "";
       
    }
   
    /**
     * @return the User_ID
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * @param User_ID the User ID to set
     */
    
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    /**
     * @return the user_Name
     */
    public String getUser_Name() {
        return user_Name;
    }

    /**
     * @param user_Name the User Name to set
     */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

  

    /**********************************************
     * Retrieves User record from database that
     * matches the User_ID and assigns it's values
     *  
     * @param id the Users’s id to search for 
     *  
    ************************************************/
    public Boolean getUser(String username, String password){
        
        Boolean found = false;
        timeStamp = LocalDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        String timeStampString = timeStamp.format(timeFormat);
        try{
            
            
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            String sql = "select * from Users WHERE User_Name = '" + username + "' AND Password = BINARY '" + password +"' ;";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
           
            while(rs.next()){
                setUser_ID(rs.getInt(1));
                setUser_Name(rs.getString(2));
                setPassword(rs.getString(3));

                
                found = true;
            
            }
            
           
            
            
            System.out.println(getUser_ID());
            
           
           
            //Close Connection
            
            
            con.close();
           
         }
         catch(SQLException e){System.out.print(e);}
         catch(Exception e){System.out.println(e);}
         
        
         if(found == true){
             try{
             
                bw = new BufferedWriter(new FileWriter("C:\\Users\\LabUser\\Documents\\NetBeansProjects\\Scheduler\\src\\main\\login_activity.txt",true));
                
                bw.write(timeStampString + ":(" + username + ") Successfully Logged In! \n" );
                bw.close();
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
         
            return true;
         }
         else{
             
             
            try{
             
                bw = new BufferedWriter(new FileWriter("C:\\Users\\LabUser\\Documents\\NetBeansProjects\\Scheduler\\src\\main\\login_activity.txt", true));
                timeStamp = LocalDateTime.now(ZoneId.of("UTC"));
                bw.write(timeStampString + ":(" + username + ") Incorrect Log In! \n" );
                bw.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
             
          
         return false;
    
         }  
    }
    
    /**********************************************
      * retrieves all users from the database 
      * @return the users list.
    ************************************************/
    public ObservableList<Users> getallUsers(){
         
         allUsers.clear();
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
            

             
            String sql = sql = "select * from Users";
            
           
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            while(rs.next()){
            Users users = new Users();
            users.setUser_ID(rs.getInt(1));
            users.setUser_Name(rs.getString(2));
            
            allUsers.add(users);
            }
           
            
            
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         
          return allUsers; 
     }
    
    /**********************************************
      * returns a user from the list 
      * that matches the user ID
      * @return the matching user.
    ************************************************/
    public Users getUserFromList(int user_id){
         
        
        if(allUsers.isEmpty()){
        
            getallUsers();
        }
        
        for(Users u : allUsers ){
            
        
            if(u.user_ID == user_id){
            
            
                return u;
            }
        }
        return null;
        
     }
    
  
    
    public static void main(String [] args){
        
        Users c1 = new Users();
    
        c1.getUser("test", "test");
        
    }
    
}
