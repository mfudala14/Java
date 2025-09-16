// Author: Martyna Fudala R00246197 CS2
package com.example.stage1_oop_martyna_fudala;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookStoreTest {

    @Test
    public void testCustomerDataIntegrity() {
        // Create two customers using the sample data:
        Customer customer1 = new Customer(1, "martyna", "martyna@gmail.com", 123456789);// Creates a Customer object for "martyna" with specified data.
        Customer customer2 = new Customer(2, "maja", "maja@gmail.com", 987654321);// Creates a Customer object for "maja" with specified data.

        // Check that the fields were set correctly:
        Assertions.assertEquals(1, customer1.getCustomerID());// Asserts that customer1's ID is 1.
        Assertions.assertEquals("martyna", customer1.getName());// Asserts that customer1's name is "martyna".
        Assertions.assertEquals("martyna@gmail.com", customer1.getEmail()); // Asserts that customer1's email is "martyna@gmail.com".
        Assertions.assertEquals(123456789, customer1.getPhoneNumber());// Asserts that customer1's phone number is 123456789.

        Assertions.assertEquals(2, customer2.getCustomerID());// Asserts that customer2's ID is 2.
        Assertions.assertEquals("maja", customer2.getName());// Asserts that customer2's name is "maja".
        Assertions.assertEquals("maja@gmail.com", customer2.getEmail());// Asserts that customer2's email is "maja@gmail.com".
        Assertions.assertEquals(987654321, customer2.getPhoneNumber());// Asserts that customer2's phone number is 987654321.
    }

    @Test
    public void testOrderTotalAndSorting() {
        Customer customer = new Customer(1, "martyna", "martyna@gmail.com", 123456789); // Creates a Customer object for testing orders.
        Product productA = new Product(1, "Twilight", "Stephenie Mayer", "Romance", 10.0, 100); // Creates a Product object "Twilight" with a price of 10.0.
        Product productB = new Product(2, "Dune", "Herbert", "Sci-fi", 20.0, 50);// Creates a Product object "Dune" with a price of 20.0.

        // Create two orders:
        // Order 1 is for productA on March 21, 2025, quantity 2 (total should be 10*2 = 20.0)
        Order order1 = new Order(customer, productA, LocalDate.of(2025, 3, 21), 2); // Creates an order for productA on March 21, 2025, with quantity 2.
        // Order 2 is for productB on March 15, 2025, quantity 1 (total should be 20.0)
        Order order2 = new Order(customer, productB, LocalDate.of(2025, 3, 15), 1); // Creates an order for productB on March 15, 2025, with quantity 1.

        // Check order totals:
        Assertions.assertEquals(20.0, order1.getTotalPrice());// Asserts that order1's total price is 20.0.
        Assertions.assertEquals(20.0, order2.getTotalPrice());// Asserts that order2's total price is 20.0.

        // Create a list of orders and sort by date:
        List<Order> orders = new ArrayList<>();// Creates a new ArrayList to hold orders.
        orders.add(order1);// Adds order1 to the list.
        orders.add(order2);// Adds order2 to the list.

        // Sorting orders in ascending order of date:
        orders.sort(Comparator.comparing(Order::getDate)); // Sorts the orders list by their date in ascending order.

        // Order2 (March 15) should come before Order1 (March 21):
        Assertions.assertEquals(LocalDate.of(2025, 3, 15), orders.get(0).getDate()); // Asserts that the first order's date is March 15, 2025.
        Assertions.assertEquals(LocalDate.of(2025, 3, 21), orders.get(1).getDate()); // Asserts that the second order's date is March 21, 2025.
    }
}
