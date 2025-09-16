// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance;//Defines a static reference to the singleton instance of DatabaseManager.
    private Connection connection;//Declares a Connection object to store the JDBC connection.

    private DatabaseManager() throws SQLException {//Defines a private constructor to prevent direct instantiation from other classes.
        //Initializes the connection by connecting to a MySQL database using JDBC at the given URL, username, and password.
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/login_schema", "root", "root");
    }

    //Provides a public static method to access the singleton instance of the class.
    public static DatabaseManager getInstance() throws SQLException {
        //Checks if the instance is not created or the existing connection is closed.
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseManager();//Creates a new DatabaseManager instance if needed.
        }
        return instance;//Returns the singleton instance of the class.
    }

    //Provides a public method to return the current database connection.
    public Connection getConnection(){
        return connection;//Returns the JDBC Connection object.
    }
}
