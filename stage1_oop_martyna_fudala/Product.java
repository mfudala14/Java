//Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.io.Serializable;

public class Product implements Serializable {
    //Declaring variables
    private int productID;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int stock;

    public Product(int productID, String title, String author, String genre, double price, int stock){
        this.productID = productID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
    }

    //Get Methods
    public int getProductID(){
        return productID;

    }
    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public double getPrice(){
        return price;
    }

    public int getStock(){
        return stock;
    }

    //Set Methods
    public void setProductID(int productID){
        this.productID = productID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    //toString
    public String toString() {
        return  productID + ", " + title + ", " + author + ", " + genre + ", " + price + ", " + stock;
    }

}
