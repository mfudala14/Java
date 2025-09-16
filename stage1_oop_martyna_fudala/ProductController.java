// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProductController {
    private ObservableList<Product> products = FXCollections.observableArrayList();// Creates an ObservableList for runtime products.
    private TextField idField; // Declares a TextField for product ID input (unused in current code).
    private TextField titleField;// Declares a TextField for product title input (unused in current code).
    private TextField priceField;// Declares a TextField for product price input (unused in current code).
    private TextField stockField;// Declares a TextField for product stock input (unused in current code).
    private ProductView view;// Declares a ProductView reference to interact with the UI.
    private Set<Integer> usedIDs = new HashSet<>();// Creates a HashSet to track used product IDs.
    private String fileName = "products.txt";// Sets the file name for saving/loading product data.

    public ProductController(ProductView view, Stage stage){
        this.view = view;//Assigning the provided productView instance to the class variable
        view.getTableView().setItems(products);//links the UI table to the products list so it updates
        attachEventHandlers(stage);//calling the method to set up button click handlers and event listeners

        // Load products directly into the runtime products list.
        ObservableList<Product> loadedProducts = FXCollections.observableArrayList(SerializationUtil.loadBooks());
        products.addAll(loadedProducts);

        for (Product p : products) { // Iterates over each product loaded from file.
            usedIDs.add(p.getProductID());// Adds each product's ID to the usedIDs set.
        }
    }

    private void attachEventHandlers(Stage primaryStage){
        view.getAddButton().setOnAction(_ -> addProduct());//setting the add button to call the addProduct() when clicked
        view.getDeleteButton().setOnAction(_ -> deleteProduct());//setting the delete button to call the deleteProduct() when clicked
        view.getListButton().setOnAction(_ -> listProducts());//setting the list button to call the listProduct() when clicked
        view.getLoadButton().setOnAction(_ -> loadProducts(fileName));//setting the load button to call the loadProduct() when clicked
        view.getSaveButton().setOnAction(_ -> saveProducts(fileName));//setting the save button to call the saveProduct() when clicked
        view.getExitButton().setOnAction(_ -> exit(primaryStage));//setting the exit button to call the exitProduct() when clicked
    }

    private void addProduct(){
        try {
            // Find the smallest unused ID
            int nextID = 1;//the next available product ID as 1.
            while (usedIDs.contains(nextID)) {//Increments nextID until an unused ID is found.
                nextID++;// Increments nextID if it is already used.
            }

            String title = view.getTitleInput().getText().trim();//Gets and trims the product title from the UI input.
            String author = view.getAuthorInput().getText().trim();//Gets and trims the product author from the UI input.
            String genre = view.getGenreInput().getText().trim();//Gets and trims the product genre from the UI input.
            double price = Double.parseDouble(view.getPriceInput().getText().trim());//Converts the price input to a double value.
            int stock = Integer.parseInt(view.getStockInput().getText().trim());//Converts the stock input to an int value.

            if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                Product newProduct = new Product(nextID, title, author, genre, price, stock);//Creates a new Product object with the given details.
                products.add(newProduct);//Adds the new products to the list
                usedIDs.add(nextID); // Mark the ID as used

                // Clear the input fields
                view.getTitleInput().clear();
                view.getAuthorInput().clear();
                view.getGenreInput().clear();
                view.getPriceInput().clear();
                view.getStockInput().clear();
            }
        } catch (NumberFormatException ex) {
            //Shows an error message if invalid numbers are entered.
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price and Stock must be valid numbers!");
            alert.showAndWait();//Displays the error alert.
        }
    }

    private void deleteProduct() {
        String productIDText = view.getIDInput().getText().trim();//Gets the product ID input from the user.
        if (!productIDText.isEmpty()) {
            try {
                int productID = Integer.parseInt(productIDText);//Converts the ID input to an integer.
                //Removes the product with the matching ID:
                boolean removed = products.removeIf(product -> product.getProductID() == productID);

                if (removed) {
                    usedIDs.remove(productID); // Make this ID available again
                    view.getTextArea1().clear();//clear the text area
                    view.getTextArea1().appendText("Customer with ID " + productID + " has been removed.\n");//Displays a success message in the UI.
                } else {
                    view.getTextArea1().clear();//clear the text area
                    //show this message in the text area
                    view.getTextArea1().appendText("No customer found with ID " + productID + ".\n");
                }

                view.getIDInput().clear();//clear the field where the ID is inputted
            } catch (NumberFormatException _) {
                //show this alert when this exception is shown
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID. Please enter a valid integer.");
                alert.showAndWait();
            }
        } else {
            //show the alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Please enter a product ID to remove.");
            alert.showAndWait();
        }
    }

    private void listProducts() {
        view.getTextArea1().clear();
        view.getTextArea1().appendText("List of Products:");
        view.tableView.setItems(products); // Re-set the observable list
        for (Product product : products) { // Iterates over each product in the products list.
            view.tableView.setItems(products); // Appends each product's details to the text area.

        }
    }

    private void loadProducts(String file) {
        products.clear();//clear the list
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {//opens the file for reading
            String line;
            view.getTextArea1().clear();//clear the text area
            view.getTextArea1().appendText("Products from text file:\n");//adding add this text to the textArea
            while ((line = reader.readLine()) != null) {//reads each line from the file
                String[] data = line.split(", ");//Splits the line into individual product attributes.
                if (data.length == 6) {// Checks if the line contains exactly six elements.
                    int productID = Integer.parseInt(data[0]); // Parses the product ID.
                    String title = data[1];// Retrieves the product title.
                    String author = data[2];// Retrieves the product author.
                    String genre = data[3];// Retrieves the product genre.
                    double price = Double.parseDouble(data[4]);// Parses the product price.
                    int stock = Integer.parseInt(data[5]);// Parses the product stock.
                    products.add(new Product(productID, title, author, genre, price, stock)); // Creates and adds a new Product object.
                    usedIDs.add(productID);// Marks the product ID as used.
                }
            }
            view.tableView.setItems(products); // Ensure table gets updated
            view.tableView.refresh(); // Force update
        } catch (IOException e) {
            //show the error message in the top text area
            view.getTextArea1().appendText("No saved products found.\n");
        }
    }


    private void saveProducts(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {//opends the file for writing
            for (Product product : products) {
                writer.write(product.toString());//Writes the product details to the file.
                writer.newLine();
            }
            view.getTextArea1().clear();
            view.getTextArea1().appendText("Products have been saved successfully!");
        } catch (IOException e) {
            view.getTextArea1().clear();
            view.getTextArea1().appendText("Error saving products.");
        }
    }

    private void exit(Stage stage){
        //setting the alert with the sentence to see if they want to save with the buttons yes and no
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit without saving?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        //the alert waits for the customer response
        alert.showAndWait().ifPresent(response -> {
            //if the response is no
            if (response == ButtonType.NO) {
                saveProducts(fileName); // execute the saveInfo function
                PauseTransition delay = new PauseTransition(Duration.seconds(3));//Pause so the person has time to read the message making sure the information is saved before the stage closes
                delay.setOnFinished(_ -> stage.close());// Set the action to be performed after the delay
                delay.play();//Start the delay

                //If the response is yes
            } else if (response == ButtonType.YES) {
                stage.close(); //close the stage
            }
        });


    }

    public ObservableList<Product> getProductList() {// Provides a public method to access the products list.
        return products;// Returns the observable list of products.
    }
}
