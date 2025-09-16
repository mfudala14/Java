// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomerView {
    private Text caption;
    TextArea textArea = new TextArea(); // Text area for the output
    TextField nameField = new TextField(); // Text field to put the name
    TextField emailField = new TextField(); // Text field to put the email
    TextField phoneField = new TextField(); // Text field to put the phone number
    TextField removeField = new TextField();// Text field to put what customer id for it to be removed



    Button addButton = new Button("Add"); //When Button pressed you can add a customer to the list
    Button listButton = new Button("List"); //When Button pressed you get list of customers
    Button removeButton = new Button("Remove"); //When Button pressed you get to remove a customer.
    Button loadButton = new Button("Load"); //When Button pressed the information is loaded from the text file
    Button saveButton = new Button("Save"); //When Button pressed the information is saved to the text file
    Button exitButton = new Button("Exit"); //When Button pressed you get a pop-up window to ask the user to save or not
    Button loadDataBase = new Button("Load (JDBC)");//Creates a button to load the information from the database on mysql
    Button saveDataBase = new Button("Save (JDBC)");//Creates a button to save the info to sql database

    public CustomerView(){
        caption = new Text(68, 240, "Customer Information"); //setting title
        caption.setFill(Color.PLUM);//setting color of the title
        caption.setFont(Font.font("Verdana", 30)); //setting the font and size

    }


    public VBox createCustomerTab(){
        nameField.setPromptText("Enter name"); // description of the name field
        emailField.setPromptText("Enter email"); // description of the email field
        phoneField.setPromptText("Enter phone number"); // description of the phone number
        removeField.setPromptText("ID");//description of the id field

        nameField.setMaxWidth(400);//setting the maximum width of the name field
        emailField.setMaxWidth(400);//setting the maximum width of the email field
        phoneField.setMaxWidth(400);//setting the maximum width of the phone number field
        textArea.setMaxWidth(400);//setting the maximum width of the text area
        removeField.setMaxWidth(30);//setting the maximum width of the remove field

        HBox buttonRow1 = new HBox(10, addButton, removeButton, removeField ,listButton); // having the 1st row buttons put into a horizontal line
        HBox buttonRow2 = new HBox(10, loadButton, saveButton, exitButton); // having the 2nd row of buttons in a horizontal line
        HBox buttonRow3 = new HBox(10, saveDataBase, loadDataBase);//Puts the load buttons into a horizontal line
        buttonRow1.setAlignment(Pos.CENTER); // making the buttons to be in the center
        buttonRow2.setAlignment(Pos.CENTER); // making the buttons to be in the center
        buttonRow3.setAlignment(Pos.CENTER); // making the button to be in the center

        VBox layout1 = new VBox(10, caption, nameField, emailField, phoneField, buttonRow1, textArea, buttonRow2, buttonRow3); //Creates vertical layout with 10 pixel of spacing between elements and adding text area and buttons
        layout1.setAlignment(Pos.CENTER);//making the layout in the center

        // Apply background color here
        layout1.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));//setting the background lavender
        return layout1;//returning the layout
    }

    // Getters for the UI components
    public TextField getNameField() { return nameField; }
    public TextField getEmailField() { return emailField; }
    public TextField getPhoneField() { return phoneField; }
    public TextField getRemoveField() { return removeField; }
    public TextArea getTextArea() { return textArea; }
    public Button getAddButton() { return addButton; }
    public Button getListButton() { return listButton; }
    public Button getRemoveButton() { return removeButton; }
    public Button getLoadButton() { return loadButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getExitButton() {return exitButton; }
    public Button getLoadDataBase() {return loadDataBase; }
    public Button getSaveDataBase() {return saveDataBase; }
}
