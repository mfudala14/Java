// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Book_Store implements Serializable {
    @Serial//This tells that next line is a part of the serialization system.
    //Unique ID that java uses during serialization to make sure the class definition hasn't changed since the object was saved.
    private static final long serialVersionUID = 1L;

    private List<Customer> customers;//private field to store a list of customers
    private List<Product> products;//private field to store a list of products

    public Book_Store(List<Customer> customers, List<Product> products) {
        this.customers = customers;//Assigns customer to the class own customer field
        this.products = products;//Assigns products to the class own product field
    }

    //Getter method
    public List<Customer> getCustomers() {
        return customers;
    }

    //Getter method
    public List<Product> getProducts() {
        return products;
    }
}
