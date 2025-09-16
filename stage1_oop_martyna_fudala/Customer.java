//Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import java.io.Serializable;

public class Customer implements Serializable {
    //Declaring Variables
    private int customerID;
    private String name;
    private String email;
    private int phoneNumber;

    //Constructor to initialize all fields directly via parameters
    public Customer(int customerID, String name, String email, int phoneNumber){
        //Assigns the provided variables to the instance variable
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //Constructor that accepts a Builder object to initialize the fields
    public Customer(Builder builder){
        //Sets the variables from the builder
        this.customerID = builder.customerID;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    //Get Methods
    public int getCustomerID(){
        return customerID;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public int getPhoneNumber() { return phoneNumber; }

    //Declares a static nested class called Builder to build Customer objects.
    public static class Builder {
        //Builder field to store the variables
        private int customerID;
        private String name;
        private String email;
        private int phoneNumber;

        //Setter method for customerID that returns the builder itself.
        public Builder customerID(int customerID) {
            //Sets the builder’s customerID and returns the builder.
            this.customerID = customerID;
            return this;
        }

        //Sets the builder’s name and returns the builder.
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        //Sets the builder’s email and returns the builder.
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        //Sets the builder’s phoneNumber and returns the builder.
        public Builder phoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        //Creates and returns a new Customer object using the builder.
        public Customer build() {
            return new Customer(this);
        }
    }

    //toString
    public String toString() {
        return  customerID + ", " + name + ", " + email + ", " + phoneNumber;
    }
}

