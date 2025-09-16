// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;


public class ProductView {
    TableView<Product> tableView = new TableView<>();// Creating a table to display to list of Product objects.
    ObservableList<Product> products = FXCollections.observableArrayList();// Initializes an observable list to hold Products data for the table

    private TextField IDInput = new TextField();//Creating a text field for the ID
    private TextField titleInput = new TextField();//Creating a text file for the title
    private TextField authorInput = new TextField();//Creating a text file for the author
    private TextField genreInput = new TextField();//Creating a text file for the genre
    private TextField priceInput = new TextField();//Creating a text file for the price
    private TextField stockInput = new TextField();//Creating a text file for the stock

    TextArea textArea1 = new TextArea(); // Text area for the output
    Button addButton = new Button("Add");//Creating the Add Button
    Button deleteButton = new Button("Delete");//Creating the Delete Button
    Button listButton = new Button("List");//Creating the List Button
    Button loadButton = new Button("Load");//Creating the Load Button
    Button saveButton = new Button("Save");//Creating the Save Button
    Button exitButton = new Button("Exit");//Creating the Exit Button

    public ProductView(){
        setup();//Constructor initializes the view by calling the setup()
        tableView.setItems(products);//binds the table to the products list
    }

    private void setup() {
        textArea1.setMaxWidth(600);//limiting the text area width to 600
        textArea1.setMaxHeight(30);//limiting the text area height to 30
        //Ensures the table columns resize proportionally to fit the table width:
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, Integer> productIDColumn = new TableColumn<>("ID");//Creating the product ID column
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));//connecting it to the getProductID()


        TableColumn<Product, String> titleColumn = new TableColumn<>("Title");//creating the title column
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Product, String> authorColumn = new TableColumn<>("Author");//creating the author column
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Product, String> genreColumn = new TableColumn<>("Genre");//creating the genre column
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");//creating the price column
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> stockColumn = new TableColumn<>("Stock");//creating the stock column
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //Adds all the created columns to the Table view
        tableView.getColumns().addAll(productIDColumn, titleColumn, authorColumn, genreColumn, priceColumn, stockColumn);

        // Input Fields
        IDInput.setPromptText("ID");//naming the field
        IDInput.setMaxWidth(40);//setting the width

        titleInput.setPromptText("Title");//naming the field
        titleInput.setMaxWidth(150);//setting the width

        authorInput.setPromptText("Author");//naming the field
        authorInput.setMaxWidth(150);//setting the width

        genreInput.setPromptText("Genre");//naming the field
        genreInput.setMaxWidth(100);//setting the width

        priceInput.setPromptText("Price");//naming the field
        priceInput.setMaxWidth(50);//setting the width

        stockInput.setPromptText("Stock");//naming the field
        stockInput.setMaxWidth(50);//setting the width

    }

    public VBox createProductTab(){
        //Creating horizontal row of input fields with spacing of 10 pixels
        HBox buttonRow1 = new HBox(10, titleInput, authorInput, genreInput, priceInput, stockInput);
        HBox buttonRow2 = new HBox(10, addButton, deleteButton, IDInput, listButton, loadButton, saveButton, exitButton);
        //Making the rows in center
        buttonRow1.setAlignment(Pos.CENTER);
        buttonRow2.setAlignment(Pos.CENTER);

        //creating vertical layout combining all components with 10 pixels
        VBox layout = new VBox(10, textArea1, tableView, buttonRow1, buttonRow2);
        layout.setAlignment(Pos.CENTER);//making them in the center
        return layout;//returning the layout
    }

    //Getting the access to the product variables and the buttons.
    public TableView<Product> getTableView() { return tableView; }
    public TextField getIDInput() { return IDInput; }
    public TextField getTitleInput() { return titleInput; }
    public TextField getAuthorInput() { return authorInput; }
    public TextField getGenreInput() { return genreInput; }
    public TextField getPriceInput() { return priceInput; }
    public TextField getStockInput() { return stockInput; }
    public TextArea getTextArea1() { return textArea1; }
    public Button getAddButton() { return addButton; }
    public Button getDeleteButton() { return deleteButton; }
    public Button getListButton() { return listButton; }
    public Button getLoadButton() { return loadButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getExitButton() { return exitButton; }


}
