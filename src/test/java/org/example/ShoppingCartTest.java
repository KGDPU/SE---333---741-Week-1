package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    public void testEmptyCartTotalPrice() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0.0, cart.totalPrice());
    }

    @Test
    public void testCartTotalPriceWithItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new CartItem("Apple", 2, 1.50)); // 3.00
        cart.add(new CartItem("Peach", 1, 3.00));  // 3.00

        assertEquals(6.00, cart.totalPrice());
    }
}