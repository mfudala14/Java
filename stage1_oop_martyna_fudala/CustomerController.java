//Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerController {
    private Set<Integer> usedIDs = new HashSet<>(); // Tracks used customer IDs
    private ObservableList<Customer> customerList; //Declares an observable List to hold Customer objects
    private CustomerView view; //Declaring a CustomerView reference for the UI
    private String file = "customers.txt"; //setting the file name

    public CustomerController(CustomerView view, Stage stage) {
        this.view = view; // Assigns the provided view to the class variable
        attachEventHandlers(stage); // Calls method to attach event handlers to UI controls
        customerList = FXCollections.observableArrayList(SerializationUtil.loadCustomers()); // Loads customers from file into an observable list.
        for (Customer c : customerList) {  // Iterates over each customer in the list.
            usedIDs.add(c.getCustomerID()); // Adds each customer's ID to the usedIDs set.
        }
    }

    private void attachEventHandlers(Stage primaryStage) {
        view.getAddButton().setOnAction(_ -> addCustomer());// setting an action where when a button Add is pressed the addCustomer is executed
        view.getListButton().setOnAction(_ -> listCustomers());// setting an action where when a button List is pressed the listCustomers is executed
        view.getRemoveButton().setOnAction(_ -> removeCustomer());//setting an action where when a button Remove is pressed the removeCustomer is executed
        view.getLoadButton().setOnAction(_ -> loadInfo(file));//setting an action where when a button Load is pressed the loadInfo is executed
        view.getSaveButton().setOnAction(_ -> saveInfo(file));//setting an action where when a button Save is pressed the saveInfo is executed
        view.getExitButton().setOnAction(_ -> exit(primaryStage));//setting an action where when a button Exit is pressed the exit is executed
        view.getLoadDataBase().setOnAction(_ -> loadDatabase());//setting an action where when a button load JDBC is pressed the loadDatabase is executed
        view.getSaveDataBase().setOnAction(_ -> saveDatabase());//setting an action where when a button save JDBC is pressed the saveDatabase is executed
    }

    private void addCustomer() {
        int nextID1 = 1;//the next available product ID as 1.
        while (usedIDs.contains(nextID1)) {//Increments nextID until an unused ID is found.
            nextID1++; // Increments nextID1 until an unused ID is found.
        }
        // Get input values from the view's text fields
        String name = view.getNameField().getText().trim();// Retrieves and trims the customer's name.
        String email = view.getEmailField().getText().trim();// Retrieves and trims the customer's email.
        String phoneText = view.getPhoneField().getText().trim();// Retrieves and trims the customer's phone.
        view.getTextArea().clear();// clear the text area

        if (name.isEmpty() || email.isEmpty() || phoneText.isEmpty()) { // Checks if any required field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!"); // Creates an error alert if fields are missing.
            alert.showAndWait();//shows alert and wait
            return;// Exits the method if validation fails.
        }

        int phone;// Declares an integer variable to hold the parsed phone number.
        try {
            phone = Integer.parseInt(phoneText);// Converts the phoneText to an integer.
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid phone number. Please enter a valid integer.");// Creates an alert for invalid phone numbers.
            alert.showAndWait();// Displays the alert.
            return;
        }

        // Create and add the new customer
        Customer customer = new Customer(nextID1, name, email, phone);// Creates a new Customer object with the next available ID.
        customerList.add(customer);// Adds the new customer to the observable list.
        usedIDs.add(nextID1);// Marks the new ID as used by adding it to the usedIDs set.
        SerializationUtil.saveCustomers(customerList); // Saves the updated customer list to file.

        // Clear input fields and update text area
        view.getTextArea().appendText("Customer has been added with an ID: " + nextID1 + "\n"); // Displays a confirmation message in the text area.
        view.getNameField().clear();//clears the field
        view.getEmailField().clear();//clears the field
        view.getPhoneField().clear();//clears the field
    }



    private void listCustomers() {
        view.getTextArea().clear(); //clears any existing  text in the text area before listing customers
        for (Customer customer : customerList) {//loops through each Customer object in the customer list
            view.getTextArea().appendText(customer.toString() + "\n"); // adds the customer's details as a string to the text area
        }
    }


    private void removeCustomer() {
        String idText = view.removeField.getText().trim(); // Get the ID input from the text field
        if (!idText.isEmpty()) { // Ensure input is not empty
            try {
                int idToRemove = Integer.parseInt(idText); // Convert input to integer
                boolean removed = customerList.removeIf(customer -> customer.getCustomerID() == idToRemove);// Remove the customer with the matching ID
                if (removed) {
                    view.getTextArea().appendText("Customer with ID " + idToRemove + " has been removed.\n");
                } else {
                    view.getTextArea().appendText("No customer found with ID " + idToRemove + ".\n");
                }
                listCustomers(); // Update the customer list display
                view.removeField.clear(); // Clear the remove field
            } catch (NumberFormatException e) { // Handle non-integer inputs
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID. Please enter a valid integer.");//Show alert if exception occurs
                alert.showAndWait();
            }
        } else {//if input is empty
            view.getTextArea().appendText("Error: Please enter an ID to remove.\n");
        }
    }


    private void loadInfo(String file){
        customerList.clear();//cleat the customers file so there won't be any repeats
        view.getTextArea().clear();//clear any existing text in the textArea
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {//Creates a BufferReader to read from the file
            view.getTextArea().appendText("Loaded Customers:\n");
            String line;//Declares a variable to hold each line of text from the file
            while ((line = reader.readLine()) != null) {//Reads each line from the file until the end
                String[] data = line.split(", ");//Splits each line
                if (data.length == 4) {//checks if the line has exactly 4 elements
                    int id = Integer.parseInt(data[0]);//Parses the first element as an integer for the customer ID
                    String name = data[1];// Assigns the second element to name.
                    String email = data[2];// Assigns the second element to email.
                    int phoneNumber = Integer.parseInt(data[3]);//Parses the forth element as an integer for the customer phone Number
                    Customer customer = (new Customer(id, name, email, phoneNumber));//Creates new Customer object with parsed data
                    customerList.add(customer);//adds the customer object to the customers array list
                    usedIDs.add(id);
                    view.getTextArea().appendText(customer + "\n");//Appends the customers information to the text Area
                }
            }
        } catch (IOException e) {//Catches any IOExceptions that may occur during file reading
            view.getTextArea().appendText("No saved customers found.");//Prints error message
        }
    }


    private void saveInfo(String file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { //Creates a BufferWriter inside a try block to write to the specified file
            for (Customer customer : customerList){//Iterates over each Customer Object in the customers array list
                writer.write(customer.toString());//writes the string of the current customer to the file
                writer.newLine();//Inserts new line in the file after each customer's information
            }
            view.getTextArea().appendText("Customers have been saved successfully!\n");//Appends the success message
        } catch (IOException e) {// Catches any IOException error that may occur during file writing
            view.getTextArea().appendText("Error");//Appends the error message to the text area
        }
    }


    private void exit(Stage stage){
        //setting the alert with the sentence to see if they want to save with the buttons yes and no
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit without saving?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        //the alert waits for the customer response
        alert.showAndWait().ifPresent(response -> {
            //if the response is no
            if (response == ButtonType.NO) {
                saveInfo(file); // execute the saveInfo function
                PauseTransition delay = new PauseTransition(Duration.seconds(3));//Pause so the person has time to read the message making sure the information is saved before the stage closes
                delay.setOnFinished(_ -> stage.close());// Set the action to be performed after the delay
                delay.play();//Start the delay

                //If the response is yes
            } else if (response == ButtonType.YES) {
                stage.close(); //close the stage
            }
        });
    }

    public ObservableList<Customer> getCustomerList() {// Provides public access to the customer list.
        return customerList;   // Returns the observable customer list.
    }

    private void loadDatabase(){
        List<Customer> customers = new CustomerDAO().loadAll();//Calls the DAO to retrieve all customer records from the database and stores them in a list.
        StringBuilder sb = new StringBuilder();//Creates a StringBuilder to efficiently build a formatted string of customer details.
        for (Customer c : customers) {//Loops through each customer object in the list.
            sb.append(c.getCustomerID()).append(" - ")//Appends the customer's ID followed by a dash to the StringBuilder.
                    .append(c.getName()).append(", ")//Appends the customer's name followed by a comma.
                    .append(c.getEmail()).append(", ")//Appends the customer's email followed by a comma.
                    .append(c.getPhoneNumber()).append("\n");//Appends the customer's phone number followed by a newline.
            }
        view.getTextArea().setText(sb.toString());//Displays the formatted customer data in the text area of the UI.
    }

    private void saveDatabase(){
        String name = view.getNameField().getText().trim();//gets name from text field
        String email = view.getEmailField().getText().trim();//gets email from text field
        String phoneText = view.getPhoneField().getText().trim();//gets phone number from text field

        if (name.isEmpty() || email.isEmpty() || phoneText.isEmpty()) {//Checks if any of the input fields are empty.
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");//create an error
            alert.showAndWait();//shows error and wait
            return;
        }

        int phone;//declares an integer to store a parsed phone number
        try {
            phone = Integer.parseInt(phoneText);//Attempts to convert the phone input into an integer.
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid phone number. Please enter digits only.");//creates an error
            alert.showAndWait();//shows error and waits
            return;
        }

        Customer customer = new Customer.Builder()//Starts building a new Customer object using the Builder pattern.
                .name(name)//sets the customer name
                .email(email)//sets the customer email
                .phoneNumber(phone)//sets the customer phone number
                .build();//Finishes building the Customer object.

        new CustomerDAO().save(customer);//Calls the DAO method to save the new customer to the database.
        view.getTextArea().appendText("Customer saved to database: " + name + "\n");//gives confirmation message
        //Clears the fields:
        view.getNameField().clear();
        view.getEmailField().clear();
        view.getPhoneField().clear();

    }

}




