// Author: Martyna Fudala R00246197 CS2
//This file is for the stage 3 in the assignment 1
package com.example.stage1_oop_martyna_fudala;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtil {
    private static final String CUSTOMER_FILE = "customers.txt";// Defines a constant for the customer data file.
    private static final String PRODUCT_FILE = "products.txt";// Defines a constant for the product data file.

    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>(); // Creates a new ArrayList to hold Customer objects.
        File file = new File(CUSTOMER_FILE);// Creates a File object representing the customer file.
        if (!file.exists()) {// Checks if the customer file does not exist.
            return customers;// Returns the empty customer list if the file is missing.
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;// Declares a variable to hold each line of text.
            while ((line = reader.readLine()) != null) { // Reads each line until the end of the file.
                String[] data = line.split(", ");// Splits the line into parts using a comma followed by a space.
                if (data.length == 4) {// Checks if exactly 4 elements were obtained from the split.
                    int id = Integer.parseInt(data[0]);// Parses the first element as the customer ID.
                    String name = data[1];// Assigns the second element to the customer name.
                    String email = data[2];// Assigns the third element to the customer email.
                    int phone = Integer.parseInt(data[3]);// Parses the fourth element as the customer phone number.
                    customers.add(new Customer(id, name, email, phone)); // Creates a new Customer object and adds it to the list.
                }
            }
        } catch (IOException e) {// Catches any IOException that occurs during file reading.
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while loading customers: " + e.getMessage()); // Creates an error alert with the exception message.
            alert.showAndWait();// Displays the alert and waits for user acknowledgment.
        }
        return customers;// Returns the list of loaded customers.
    }

    public static void saveCustomers(ObservableList<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {// Iterates over each Customer in the observable list.
                writer.write(customer.getCustomerID() + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getPhoneNumber()); // Writes the customer's details in CSV format.writer.newLine();                      // Writes a new line after each customer's information.
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while saving customers: " + e.getMessage()); // Creates an error alert with the exception message.
            alert.showAndWait();// Displays the alert and waits
        }
    }

    public static List<Product> loadBooks() {
        List<Product> products = new ArrayList<>();// Creates a new ArrayList to hold Product objects.
        File file = new File(PRODUCT_FILE);// Creates a File object representing the product file.
        if (!file.exists()) { // Checks if the product file does not exist.
            return products;// Returns the empty product list if the file is missing.
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;// Declares a variable to hold each line from the file.
            while ((line = reader.readLine()) != null) { // Reads each line until reaching the end of the file.
                String[] data = line.split(", "); // Splits the line into parts using ", " as the delimiter.
                if (data.length == 6) {// Checks if exactly 6 elements are obtained.
                    int productID = Integer.parseInt(data[0]); // Parses the first element as the product ID.
                    String title = data[1];// Assigns the second element to the product title.
                    String author = data[2];// Assigns the third element to the product author.
                    String genre = data[3];// Assigns the fourth element to the product genre.
                    double price = Double.parseDouble(data[4]); // Parses the fifth element as the product price.
                    int stock = Integer.parseInt(data[5]); // Parses the sixth element as the product stock.
                    products.add(new Product(productID, title, author, genre, price, stock)); // Creates a new Product object and adds it to the list.
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while loading products: " + e.getMessage());
            alert.showAndWait();// Displays the alert and waits
        }
        return products;
    }
    public static void saveBooks(ObservableList<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product product : products) {// Iterates over each Product in the observable list.
                writer.write(product.toString());// Writes the product's details (using its toString method) to the file.
                writer.newLine();// Writes a new line after each product's information.
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while saving products: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
