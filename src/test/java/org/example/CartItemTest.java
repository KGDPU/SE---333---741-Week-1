package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    public void testCartItemGetters() {
        CartItem item = new CartItem("Watermelon", 2, 10.0);

        assertEquals("Watermelon", item.getProduct());
        assertEquals(2, item.getQuantity());
        assertEquals(10.0, item.getUnitPrice());
    }

    @Test
    public void testCartItemEquals() {
        CartItem item1 = new CartItem("Apple", 5, 1.5);
        CartItem item2 = new CartItem("Apple", 5, 1.5);

        assertEquals(item1, item2);
    }
}