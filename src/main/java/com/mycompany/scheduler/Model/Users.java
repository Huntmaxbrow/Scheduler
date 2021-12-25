
package com.mycompany.scheduler.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void getUser(String username, String password){
         try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            String sql = "select * from Users WHERE User_Name = '" + username + "' AND Password = '" + password +"' ;";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
           
            while(rs.next()){
            setUser_ID(rs.getInt(1));
            setUser_Name(rs.getString(2));
            setPassword(rs.getString(3));
          
            
            }
            System.out.println(getUser_ID());
            
           
           
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
  
    
    public static void main(String [] args){
        
        Users c1 = new Users();
    
        c1.getUser("test", "test");
        
    }
    
}
