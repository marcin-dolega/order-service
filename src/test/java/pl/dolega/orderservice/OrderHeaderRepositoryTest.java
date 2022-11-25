package pl.dolega.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dolega.orderservice.OrderHeader.OrderHeader;
import pl.dolega.orderservice.OrderHeader.OrderHeaderRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    void getByIdTest() {
        OrderHeader orderHeader = orderHeaderRepository.getReferenceById(1L);
        assertThat(orderHeader).isNotNull();
        assertThat(orderHeader.getCustomerName()).isEqualTo("Craig Walls");
    }
}
