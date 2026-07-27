package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.Order;
import com.cput.laundryecommercebookingsystem.domain.OrderItem;
import com.cput.laundryecommercebookingsystem.domain.Product;
import com.cput.laundryecommercebookingsystem.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void createOrderItem() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        Order order = new Order.Builder()
                .studentId(student)
                .build();

        Product product = new Product.Builder()
                .setProductId("P001")
                .setName("Laundry Basket")
                .setDescription("Plastic Basket")
                .setPrice(150.00)
                .setCategory("Accessories")
                .setInventoryQuantity(20)
                .build();

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                order,
                product,
                2,
                150.00
        );

        assertNotNull(orderItem);
        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(150.00, orderItem.getUnitPrice());
        assertEquals(300.00, orderItem.getSubtotal());
    }

    @Test
    void createOrderItemWithNullOrder() {

        Product product = new Product.Builder()
                .setProductId("P001")
                .setName("Laundry Basket")
                .setDescription("Plastic Basket")
                .setPrice(150.00)
                .setCategory("Accessories")
                .setInventoryQuantity(20)
                .build();

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                null,
                product,
                2,
                150.00
        );

        assertNull(orderItem);
    }

    @Test
    void createOrderItemWithNullProduct() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        Order order = new Order.Builder()
                .studentId(student)
                .build();

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                order,
                null,
                2,
                150.00
        );

        assertNull(orderItem);
    }

    @Test
    void createOrderItemWithInvalidQuantity() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        Order order = new Order.Builder()
                .studentId(student)
                .build();

        Product product = new Product.Builder()
                .setProductId("P001")
                .setName("Laundry Basket")
                .setDescription("Plastic Basket")
                .setPrice(150.00)
                .setCategory("Accessories")
                .setInventoryQuantity(20)
                .build();

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                order,
                product,
                0,
                150.00
        );

        assertNull(orderItem);
    }

    @Test
    void createOrderItemWithNegativePrice() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        Order order = new Order.Builder()
                .studentId(student)
                .build();

        Product product = new Product.Builder()
                .setProductId("P001")
                .setName("Laundry Basket")
                .setDescription("Plastic Basket")
                .setPrice(150.00)
                .setCategory("Accessories")
                .setInventoryQuantity(20)
                .build();

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                order,
                product,
                2,
                -10.00
        );

        assertNull(orderItem);
    }
}
