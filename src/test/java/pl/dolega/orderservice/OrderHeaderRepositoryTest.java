package pl.dolega.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dolega.orderservice.orderHeader.OrderHeader;
import pl.dolega.orderservice.orderHeader.OrderHeaderRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
    }
}
