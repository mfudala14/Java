// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    //Defines a method to save a Customer object to the database.
    public void save(Customer customer) {
        String sql = "INSERT INTO Customers (name, email, phoneNumber) VALUES (?, ?, ?)";//Prepares an SQL query to insert a new customer record using placeholders.
        try (PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {//Uses the Singleton DatabaseManager to get the connection and prepare the SQL statement.
            stmt.setString(1, customer.getName());//Sets the first parameter in the query to the customer’s name.
            stmt.setString(2, customer.getEmail());//Sets the second parameter to the customer’s email.
            stmt.setInt(3, customer.getPhoneNumber());//Sets the third parameter to the customer’s phone number.
            stmt.executeUpdate();//Executes the SQL update, inserting the customer into the database.
        } catch (SQLException e) {
            e.printStackTrace();//prints stack trace for debugging
        }
    }

    public List<Customer> loadAll() {
        List<Customer> customers = new ArrayList<>();//Initializes an empty list to store the retrieved customers.
        String sql = "SELECT * FROM Customers";//Prepares an SQL query to select all records from the Customers table.
        try (Statement stmt = DatabaseManager.getInstance().getConnection().createStatement();//Creates a statement using the Singleton connection to execute the SQL query.
             ResultSet rs = stmt.executeQuery(sql)) {//Executes the query and stores the results in a ResultSet.
            while (rs.next()) {//Loops through each row in the result set.
                Customer customer = new Customer.Builder()//Starts building a new Customer object using the Builder pattern.
                        .customerID(rs.getInt("customerID"))//Sets the customer ID from the result set.
                        .name(rs.getString("name"))//Sets the name from the result set
                        .email(rs.getString("email"))//Sets the email from the result set
                        .phoneNumber(rs.getInt("phoneNumber"))//Sets the phone Number from the result set
                        .build();//Builds the complete Customer object.
                customers.add(customer);//Adds the constructed customer to the list.
            }
        } catch (SQLException e) {
            e.printStackTrace();//Prints the error details to the console
        }
        return customers;//Returns the list of all customers retrieved from the database.
    }

}
