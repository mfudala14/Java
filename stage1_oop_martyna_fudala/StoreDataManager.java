// Author: Martyna Fudala R00246197 CS2
//Demonstrating the use of Singleton Pattern to handle Serialization/Deserialization
package com.example.stage1_oop_martyna_fudala;
import java.io.*;

public class StoreDataManager {
    private static StoreDataManager instance;//This is the Singleton instance
    private static final String FILE_NAME = "store_data.ser";//This defines the file name used when saving or loading the data

    //This is a private constructor, it prevents other classes from using the new StoreDataManager. This is the key part of the singleton pattern.
    private StoreDataManager() {
    }

    //This is the singleton access method
    public static StoreDataManager getInstance() {
        //If the instance have not been created yet this creates it
        if (instance == null) {
            instance = new StoreDataManager();
        }
        return instance;//returns the singleton instance
    }

    public void saveStore(Book_Store store) throws IOException {//Public method saves the Book store object to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {//Creates an ObjectOutputStream which is used to write java object to a file
            oos.writeObject(store);//This line serializes the Book store object and writes it to the file
        }
    }

    public Book_Store loadStore() throws IOException, ClassNotFoundException {//This method loads a book store object from the saved .ser file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {//Creates an ObjectInputStream to read the file
            return (Book_Store) ois.readObject();//Read the object from the file, casts it back to a Book_store
        }
    }
}
