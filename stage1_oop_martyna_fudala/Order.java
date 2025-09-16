// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.time.LocalDate;

public class Order {
    //Declaring the variables
    private int order_id;
    private Customer customer;
    private Product product;
    private LocalDate date;
    private int quantity;

    public Order(Customer customer, Product product, LocalDate date, int quantity) {
        this.customer = customer;
        this.product = product;
        this.date = date;
        this.quantity = quantity;
    }

    public Customer getCustomer(){return customer;}
    public LocalDate getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity(){return quantity; }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }


    //Setting the toString

    @Override
    public String toString() {
        return date + " - " +  product.getTitle() + " - " + quantity + " - Total: €" + String.format("%.2f", getTotalPrice());
    }


}
