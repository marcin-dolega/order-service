package pl.dolega.orderservice.orderHeader;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dolega.orderservice.customer.Customer;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
     List<OrderHeader> findAllByCustomer(Customer customer);
}
