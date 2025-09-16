// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;


public class OrderController {
    private OrderView view;// Reference to the order view
    private Stage stage;// Reference to the primary stage
    ObservableList<Customer> customers; //Creating observable list for customers
    ObservableList<Product> products; //Creating observable list for products
    private ObservableList<Order> orders = FXCollections.observableArrayList(); // Non-null orders list
    private ObservableList<Order> currentOrders = FXCollections.observableArrayList(); // Orders since last purchase


    public OrderController(OrderView view, Stage stage, ObservableList<Customer> customers, ObservableList<Product> products) {
        this.view = view;// Set the view reference
        this.stage = stage;// Set the stage reference
        this.customers = customers;// Set the customers list
        this.products = products;// Set the products list
        attachEventHandlers();// Attach event handlers for UI controls
        view.getCustomerComboBox().setItems(customers); // Populate customer ComboBox
        view.getProductComboBox().setItems(products);// Populate product ComboBox
        setupComboBoxes();// Configure ComboBoxes to display only id and name/title
    }

    // Configures the customer and product ComboBoxes to show only id and name/title.
    // As before I had all the information and I noticed that it gets very messy,  I figured to short it so it looks better
    private void setupComboBoxes() { // Configure combo boxes for customers and products
        view.getCustomerComboBox().setCellFactory(lv -> new ListCell<>() { // Set how each item in the customer combo box should appear
            @Override
            protected void updateItem(Customer item, boolean empty) { // Override method to customize display
                super.updateItem(item, empty); // Call superclass method
                if (empty || item == null) { // If item is null or empty
                    setText(null); // Show nothing
                } else {
                    setText(item.getCustomerID() + " - " + item.getName()); // Show ID and Name of customer
                }
            }
        });
        view.getCustomerComboBox().setButtonCell(new ListCell<>() { // Set how the selected customer appears
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null); // If nothing is selected, show blank
                } else {
                    setText(item.getCustomerID() + " - " + item.getName()); // Display selected customer
                }
            }
        });

        view.getProductComboBox().setCellFactory(lv -> new ListCell<>() { // Set how items in product combo box appear
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null); // If no item, show blank
                } else {
                    setText(item.getProductID() + " - " + item.getTitle()); // Show product ID and title
                }
            }
        });
        view.getProductComboBox().setButtonCell(new ListCell<>() { // Set how the selected product appears
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null); // If nothing selected, blank
                } else {
                    setText(item.getProductID() + " - " + item.getTitle()); // Display selected product
                }
            }
        });
    }

    private void attachEventHandlers() { // Attach actions to buttons
        view.getAddOrderButton().setOnAction(_ -> addOrder()); // When Add Order button is clicked, call addOrder()
        view.getPurchaseButton().setOnAction(_ -> purchaseButton()); // When Purchase button is clicked, call purchaseButton()
        view.getSortByDateButton().setOnAction(_ -> sortOrdersByDate()); // Sort orders by date on click
        view.getSortByProductButton().setOnAction(_ -> sortOrdersByProduct()); // Sort orders by product title
        view.getCheckProductButton().setOnAction(_ -> checkProductInventory()); // Show product inventory
        view.getCheckCustomerButton().setOnAction(_  -> checkCustomerPurchases()); // Show customer's last month purchases
        view.getSaveSerializeButton().setOnAction(_ -> saveSerializeButton());//Saves information using the serialization
        view.getLoadSerializeButton().setOnAction(_ -> loadSerializeButton());//Loads the information
    }

    private void addOrder() { // Adds a new order
        view.getTotalLabel().setText(""); // Clear total label
        Customer selectedCustomer = view.getCustomerComboBox().getValue(); // Get selected customer
        Product selectedProduct = view.getProductComboBox().getValue(); // Get selected product
        LocalDate date = view.getDatePicker().getValue(); // Get selected date
        int quantity; // Declare quantity variable
        try {
            quantity = Integer.parseInt(view.getQuantityField().getText().trim()); // Parse quantity from input field
        } catch (NumberFormatException e) {
            showAlert("Invalid quantity!"); // Show error if input is not a number
            return;
        }
        if (selectedCustomer == null || selectedProduct == null || date == null || quantity <= 0) { // Validate input
            showAlert("Please fill all fields correctly."); // Show error if input is incomplete
            return;
        }

        view.getTotalLabel().setText(""); // Clear total label again

        Order order = new Order(selectedCustomer, selectedProduct, date, quantity); // Create new order object
        orders.add(order); // Add order to main list
        currentOrders.add(order); // Add order to current session
        updateOrderDisplay(); // Display updated list
        updateTotal(); // Update total price
    }

    private void sortOrdersByDate() { // Sorts orders by date
        currentOrders.sort(Comparator.comparing(Order::getDate)); // Sort using order date
        updateOrderDisplay(); // Refresh display
        updateTotal(); // Update total price
    }

    private void sortOrdersByProduct() { // Sorts orders by product title
        currentOrders.sort(Comparator.comparing(o -> o.getProduct().getTitle())); // Sort alphabetically by title
        updateOrderDisplay(); // Refresh order display
        updateTotal(); // Update total
    }

    private void updateOrderDisplay() { // Updates the display of current orders
        view.getTextArea().clear(); // Clear the text area
        for (Order order : currentOrders) { // Loop through all current orders
            view.getTextArea().appendText(order.toString() + "\n"); // Show each order in text area
        }
    }

    private void updateTotal() { // Updates the total label
        view.getTotalLabel().setText(""); // Clear previous total
        double total = currentOrders.stream().mapToDouble(Order::getTotalPrice).sum(); // Sum all totals
        view.getTotalLabel().setText(String.format("Total: €%.2f", total)); // Display formatted total
    }

    private void showAlert(String message) { // Displays an error alert
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK); // Create alert box
        alert.showAndWait(); // Show alert to user
    }

    public void purchaseButton() { // Completes the current purchase
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your order has been completed", ButtonType.OK); // Confirmation message
        alert.showAndWait(); // Show alert
        view.getTextArea().clear(); // Clear the order display
        view.getTotalLabel().setText(""); // Clear total
        view.getDatePicker().setValue(LocalDate.now()); // Reset date picker
        view.getQuantityField().setText(""); // Clear quantity input
        currentOrders.clear(); // Clear current session orders
    }

    private void checkProductInventory() { // Shows selected product's inventory details
        Product selected = view.getProductComboBox().getValue(); // Get selected product
        if (selected != null) {
            int stock = selected.getStock(); // Get current stock
            int sold = countSoldLastMonth(selected); // Get sales last month
            String message = "Product: " + selected.getTitle() + "\n" + "In Stock: " + stock + "\n" + "Sold Last Month: " + sold; // Create message
            view.getTextArea().setText(message); // Show inventory info
        } else {
            showAlert("Please select a product."); // Error if no product selected
        }
    }

    private int countSoldLastMonth(Product product) { // Counts product sales from last month
        LocalDate now = LocalDate.now(); // Current date
        LocalDate lastMonthStart = now.minusMonths(1).withDayOfMonth(1); // First day of last month
        LocalDate lastMonthEnd = now.minusMonths(1).withDayOfMonth(now.minusMonths(1).lengthOfMonth()); // Last day of last month
        int count = 0; // Total quantity sold
        for (Order order : orders) { // Loop through all orders
            if (order.getProduct().equals(product)) { // Match product
                LocalDate orderDate = order.getDate(); // Get order date
                if (!orderDate.isBefore(lastMonthStart) && !orderDate.isAfter(lastMonthEnd)) { // Check if it's within last month
                    count += order.getQuantity(); // Add quantity
                }
            }
        }
        return count; // Return total sold
    }

    private void checkCustomerPurchases() { // Shows customer's purchases from last month
        Customer selected = view.getCustomerComboBox().getValue(); // Get selected customer
        if (selected != null) {
            LocalDate now = LocalDate.now(); // Current date
            LocalDate lastMonthStart = now.minusMonths(1).withDayOfMonth(1); // Start of last month
            LocalDate lastMonthEnd = now.minusMonths(1).withDayOfMonth(now.minusMonths(1).lengthOfMonth()); // End of last month

            StringBuilder sb = new StringBuilder(); // String builder for display
            sb.append("Purchases made by " + selected.getName() + " last month:\n"); // Header

            currentOrders.clear(); // Clear current display list

            for (Order order : orders) { // Loop through all orders
                if (order.getCustomer().equals(selected)) { // Match customer
                    LocalDate orderDate = order.getDate(); // Get order date
                    if (!orderDate.isBefore(lastMonthStart) && !orderDate.isAfter(lastMonthEnd)) { // Check if it's last month
                        sb.append(order.toString()).append("\n"); // Add order to display
                        currentOrders.add(order); // Add to current orders
                    }
                }
            }

            if (currentOrders.isEmpty()) { // If no orders found
                view.getTextArea().setText("No purchases last month."); // Show message
            } else {
                updateOrderDisplay(); // Show orders
                view.getTextArea().setText(sb.toString()); // Set message
            }
        } else {
            showAlert("Please select a customer."); // Error if no customer
        }
        view.getTotalLabel().setText(""); // Clear total
    }

    private void saveSerializeButton() {
        //Creating a new Book_store object using the observable list
        Book_Store store = new Book_Store(
                new java.util.ArrayList<>(customers),
                new java.util.ArrayList<>(products)
        );
        try {//try block
            StoreDataManager.getInstance().saveStore(store);//Calls the saveStore method in the Singleton class
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Success, Book Store saved successfully", ButtonType.OK);//Confirmation message
            alert.showAndWait();//shows message and waits
        } catch (IOException e) {//if saving fails catches the error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error, Failed to save Book Store", ButtonType.OK);//Error message
            alert.showAndWait();//shows message and waits
        }
    }

    private void loadSerializeButton(){
        try{//try block
            Book_Store store = StoreDataManager.getInstance().loadStore();//Gets the singleton instance and calls loadStore() which reads and returns the stored object from file
            customers.setAll(store.getCustomers());//Replace current observable list contents with the loaded array list from the book store
            products.setAll(store.getProducts());//Replace current observable list contents with the loaded array list from the book store
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Success, Book Store loaded successfully!", ButtonType.OK);//Confirmation message
            alert.showAndWait();
        } catch (IOException | ClassNotFoundException e) {//Catch any input or output error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load book store.", ButtonType.OK);//Error message
            alert.showAndWait();//show alert and wait
        }
    }

    public ObservableList<Order> getOrders() { // Getter for orders list
        return orders; // Return all orders
    }
}