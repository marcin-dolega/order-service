package pl.dolega.orderservice.orderHeader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderHeaderTest {

    @Test
    void testEquals() {
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(1L);

        OrderHeader orderHeader2 = new OrderHeader();
        orderHeader1.setId(1L);

        assertTrue(orderHeader1.equals(orderHeader2));
    }

    @Test
    void testNotEquals() {
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(1L);

        OrderHeader orderHeader2 = new OrderHeader();
        orderHeader2.setId(2L);

        assertFalse(orderHeader1.equals(orderHeader2));
    }
}