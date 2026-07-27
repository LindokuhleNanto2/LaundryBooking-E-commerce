package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.Order;
import com.cput.laundryecommercebookingsystem.domain.OrderItem;
import com.cput.laundryecommercebookingsystem.domain.Product;

/*OrderItemFactory.java
 * Factory class for creating OrderItem objects.
 * Author: Sabotseng Ndaba (230235875)
 * Date: 26 July 2026
 */

public class OrderItemFactory {

    public static OrderItem createOrderItem(Order order,
                                            Product product,
                                            int quantity,
                                            double unitPrice) {

        if (order == null ||
                product == null ||
                quantity <= 0 ||
                unitPrice < 0) {
            return null;
        }

        return new OrderItem.Builder()
                .setOrder(order)
                .setProduct(product)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .build();
    }
}

