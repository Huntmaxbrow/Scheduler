
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
public class Customers {
 
    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private String create_Date;
    private String created_by;
    private String last_update;
    private String last_Updated_By;
    private int division_ID;
    
    
    private ObservableList<Customers> allCustomersList = FXCollections.observableArrayList();
    
    /**********************************************
     * @param customer_ID the customer id.
     * @param customer_Name the name of the customer.
     * @param address the address of the customer.
     * @param postal_Code the postal code for the customer.
     * @param phone the phone number of customer.
     * @param create_Date the creation date of the customer
     * @param created_by the user ID for who created the customer.
     * @param last_update the last date the customer was updated.
     * @param last_Updated_By the user ID for who updated the customer.
     * @param division_ID the Division ID associated to the customer
    ************************************************/
    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, String create_Date, String created_by, String last_update, String last_Updated_By, int division_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
        this.division_ID = division_ID;
    }

    /** 
     * Default Constructor.
     */
    public Customers() {
        this.customer_ID = 0;
        this.customer_Name = "";
        this.address = "";
        this.postal_Code = "";
        this.phone = "";
        this.create_Date = null;
        this.created_by = "";
        this.last_update = null;
        this.last_Updated_By = "";
        this.division_ID = 0;
    }
   
    /**
     * @return the customer_ID
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * @param customer_ID the customer ID to set
     */
    
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * @return the customer_Name
     */
    public String getCustomer_Name() {
        return customer_Name;
    }

    /**
     * @param customer_Name the customer Name to set
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * 
     * @return the postal Code
     */
    public String getPostal_Code() {
        return postal_Code;
    }

    /**
     * @param postal_Code the postal Code to set
     */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the create Date
     */
    public String getCreate_Date() {
        return create_Date;
    }

    /**
     * @param create_Date the create Date to set
     */
    public void setCreate_Date(String create_Date) {
        this.create_Date = create_Date;
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
     * @return the division_ID
     */
    public int getDivision_ID() {
        return division_ID;
    }

    /**
     * @param division_ID the division_ID to set
     */
    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }
    
    
    //sql methods
    /**********************************************
     * Retrieves customer record from database that
     * matches the customerID and assigns it's values
     *  
     * @param id the customers???s id to search for 
     *  
    ************************************************/
    public void getCustomer(int id){
         try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            String sql = "select * from customers WHERE Customer_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
           
            while(rs.next()){
            setCustomer_ID(rs.getInt(1));
            setCustomer_Name(rs.getString(2));
            setAddress(rs.getString(3));
            setPostal_Code(rs.getString(4));
            setPhone(rs.getString(5));
            setCreate_Date(rs.getTimestamp(6).toString());
            setCreated_by(rs.getString(7));
            setLast_update(rs.getTimestamp(8).toString());
            setLast_Updated_By(rs.getString(9));
            setDivision_ID(rs.getInt(10));
            
            
            }
            
           
            System.out.println(getCustomer_Name());
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
     * update Customer Records using current values.
     ************************************************/
    public void updateCustomer(){
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
          
            
            String sql;
            sql = "UPDATE customers set Customer_Name='" + customer_Name + "'," +
                    "Address='" + address + "'," +
                    "Postal_Code='" + postal_Code + "'," +
                    "Phone='" + phone + "'," +
                    "Create_Date='" + create_Date + "'," +
                    "Created_by='" + created_by + "'," +
                    "Last_update='UTC_TIMESTAMP'," +
                    "Last_Updated_By='" + last_Updated_By + "'," + 
                    "Division_ID='" + division_ID + "'" +
                    "WHERE Customer_ID=" + customer_ID + "";
       
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
     * adds new customer record to database.
     * @param customer_ID the customer id.
     * @param customer_Name the name of the customer.
     * @param address the address of the customer.
     * @param postal_Code the postal code for the customer.
     * @param phone the phone number of customer.
     * @param create_Date the creation date of the customer
     * @param created_by the user ID for who created the customer.
     * @param last_update the last date the customer was updated.
     * @param last_Updated_By the user ID for who updated the customer.
     * @param division_ID the Division ID associated to the customer
    ************************************************/
    public void addCustomer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, String created_by, String last_Updated_By, int division_ID){
         try{
           
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
           
            //executing statement
            String sql;
            sql = "insert into customers (Customer_ID,customer_Name, address, postal_Code, phone, create_date, created_by, last_update, last_Updated_By, division_ID)"
                    + " values ('" + customer_ID + "','" +
                    customer_Name + "','" +
                    address + "','" +
                    postal_Code + "','" +
                    phone + "',"+
                    "UTC_TIMESTAMP,'" +
                    created_by + "'," +
                    "UTC_TIMESTAMP,'" +
                    last_Updated_By + "'," +
                    division_ID + "" + 
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
     * deletes Customer from the database.
     * @param customer_ID the id of the customer to delete.
    ************************************************/
    public void deleteCustomer(int customer_ID){
  
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
            

            String sql;
            sql = "DELETE FROM customers WHERE Customer_ID = " + "" + customer_ID + ";";
            

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
      * retrieves all customers from the database 
      * @return the customer list.
    ************************************************/
    public ObservableList<Customers> getAllCustomers(){
         
         allCustomersList.clear();
         try{
            
             
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule", "sqlUser", "Passw0rd!");
            Statement stmt = con.createStatement();
            

             
            String sql = sql = "select * from customers";
            
           
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            while(rs.next()){
            Customers customer = new Customers();
            customer.setCustomer_ID(rs.getInt(1));
            customer.setCustomer_Name(rs.getString(2));
            customer.setAddress(rs.getString(3));
            customer.setPostal_Code(rs.getString(4));
            customer.setPhone(rs.getString(5));
            customer.setCreate_Date(rs.getTimestamp(6).toString());
            customer.setCreated_by(rs.getString(7));
            customer.setLast_update(rs.getTimestamp(8).toString());
            customer.setLast_Updated_By(rs.getString(9));
            customer.setDivision_ID (rs.getInt(10));
          
            allCustomersList.add(customer);
            }
           
            
            
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         
          return allCustomersList; 
     }
    /**********************************************
      * retrieves all customers from the database 
      * @return the customer list.
    ************************************************/
    public int getNewCustomerID(){
         
         int newCustID = 0;
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
            
            
            newCustID =  rs.getInt(1) + 1;
           
          
            
            
           
            
            
            con.close();
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         
          return newCustID; 
     }
    
    
     /**********************************************
      * @return the appointment list.
    ************************************************/
    public ObservableList<Customers> getCustomersForView(){
        return allCustomersList;
    }
    
    public static void main(String [] args){
        
        Customers c1 = new Customers();
    
        
    }
    
}
