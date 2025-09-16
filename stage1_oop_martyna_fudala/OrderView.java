// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OrderView {
    private ComboBox<Customer> customerComboBox = new ComboBox<>(); // Creates a combo box to select a customer.
    private ComboBox<Product> productComboBox = new ComboBox<>(); // Creates a combo box to select a product.
    private DatePicker datePicker = new DatePicker(); // Creates a date picker for selecting a purchase date.
    private TextField quantityField = new TextField();// Creates a text field for entering the quantity.
    private Label totalLabel = new Label("Total: €0.00");// Creates a label to display the total order amount.
    private Button addOrderButton = new Button("Add Order");// Creates a button to add an order.
    private TextArea textArea = new TextArea();// Creates a text area to display order details.
    private Button purchaseButton = new Button("Purchase");// Creates a button to confirm purchase.
    private Button sortByDateButton = new Button("Sort by Date");// Creates a button to sort orders by date.
    private Button sortByProductButton = new Button("Sort by Product");// Creates a button to sort orders by product.
    private Button checkProductButton = new Button("Check Product Inventory");// Creates a button to check the product Inventory
    private Button checkCustomerButton = new Button("Check Customer Purchases Last Month"); // Creates a button to check what customer had purchased last month
    private Button saveSerializeButton = new Button("Save (serialize)");//Creates button to save using serialization
    private Button loadSerializeButton = new Button("Load (deserialize)");//Creates button to load the info that is serialized

    public VBox createOrderTab(){
        customerComboBox.setPromptText("Select Customer");// Sets the prompt text for the customer combo box.
        productComboBox.setPromptText("Select Product");// Sets the prompt text for the product combo box.
        quantityField.setMaxWidth(80); // Limits the width of the quantity text field.
        VBox layout = new VBox(10);// Creates a vertical box layout with 10 pixels spacing.
        HBox buttonRow1 = new HBox(80, customerComboBox, productComboBox); // Creates an HBox with 80 pixels spacing containing the customer and product combo boxes.
        buttonRow1.setAlignment(Pos.CENTER);// Centers the controls in buttonRow1.
        HBox buttonRow2 = new HBox(90, totalLabel, purchaseButton);// Creates an HBox with 90 pixels spacing containing the total label and purchase button.
        buttonRow2.setAlignment(Pos.CENTER);// Centers the controls in buttonRow2.
        HBox buttonRow3 = new HBox(90, sortByDateButton, sortByProductButton); // Creates an HBox with 90 pixels spacing containing the sort buttons.
        buttonRow3.setAlignment(Pos.CENTER);// Centers the controls in buttonRow3.
        HBox buttonRow4 = new HBox(90, saveSerializeButton, loadSerializeButton);//Creates an HBox with 90 pixels spacing containing the save and load button.
        buttonRow4.setAlignment(Pos.CENTER);//Centers the controls in buttonRow4
        layout.setPadding(new Insets(10));// Sets 10 pixels of padding around the layout.
        layout.setAlignment(Pos.CENTER);// Centers the layout's child nodes.
        // Configure product section
        productComboBox.setPromptText("Select a Product");
        quantityField.setPromptText("Quantity");
        datePicker.setPromptText("Date of purchase");
        customerComboBox.setPromptText("Select a Customer"); // Configure customer section
        VBox Row1 = new VBox(10, customerComboBox, checkCustomerButton);// Creating Vertical row of boxes
        Row1.setAlignment(Pos.CENTER_LEFT);// Positioning them on the left
        VBox Row2 = new VBox(10, productComboBox, checkProductButton);// Creating vertical row of buttons
        Row2.setAlignment(Pos.CENTER_RIGHT);// Positioning them to the right
        Separator verticalSeparator = new Separator(Orientation.VERTICAL);// Creating a separator
        verticalSeparator.setPrefHeight(100); // Set a preferred height if needed
        HBox hBox = new HBox(20, Row1, verticalSeparator, Row2);// Put both columns (plus separator) in an HBox
        hBox.setAlignment(Pos.CENTER); // Centers both columns horizontally
        hBox.setPadding(new Insets(10)); // Adds padding around the columns
        VBox root = new VBox(hBox);// Placing the hBox into the root
        root.setAlignment(Pos.CENTER); // Centers the HBox
        root.setPadding(new Insets(10));
        Label label5= new Label("Order Information");
        label5.setFont(new Font(20));
        HBox Row6 = new HBox(10, new Label("Order:"), datePicker, quantityField, addOrderButton);
        Row6.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label5, root, Row6, buttonRow2 , textArea,buttonRow3, buttonRow4); // Adds all controls and labels to the layout in the specified order.
        layout.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));//Makes the background lavender
        return layout;//returns the layout
    }

    // Getters for controls
    public ComboBox<Customer> getCustomerComboBox() { return customerComboBox; }
    public ComboBox<Product> getProductComboBox() { return productComboBox; }
    public Button getPurchaseButton(){return purchaseButton; }
    public DatePicker getDatePicker() { return datePicker; }
    public TextField getQuantityField() { return quantityField; }
    public Label getTotalLabel() { return totalLabel; }
    public Button getAddOrderButton() { return addOrderButton; }
    public TextArea getTextArea() { return textArea; }
    public Button getSortByDateButton() { return sortByDateButton; }
    public Button getSortByProductButton() { return sortByProductButton; }
    public Button getCheckProductButton() { return checkProductButton; }
    public Button getCheckCustomerButton() { return checkCustomerButton; }
    public Button getSaveSerializeButton() { return  saveSerializeButton; }
    public Button getLoadSerializeButton() { return  loadSerializeButton; }
}
