// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Store");
        // Initialize Customer View and Controller
        CustomerView customerView = new CustomerView();
        CustomerController customerController = new CustomerController(customerView, primaryStage);
        // Initialize Product View and Controller
        ProductView productView = new ProductView();
        ProductController productController = new ProductController(productView, primaryStage);
        // Initialize Order View and Controller
        OrderView orderView = new OrderView();
        OrderController orderController = new OrderController(orderView, primaryStage, customerController.getCustomerList(), productController.getProductList());
        // Create TabPane and add three tabs
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                new Tab("Customer", customerView.createCustomerTab()),
                new Tab("Order", orderView.createOrderTab()),
                new Tab("Maintenance", productView.createProductTab())
        );
        tabPane.getTabs().forEach(tab -> tab.setClosable(false)); // Prevent tabs from being closable
        Scene scene = new Scene(tabPane, 600, 580);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
