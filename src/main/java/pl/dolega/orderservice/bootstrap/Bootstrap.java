package pl.dolega.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.dolega.orderservice.customer.Customer;
import pl.dolega.orderservice.customer.CustomerRepository;
import pl.dolega.orderservice.orderHeader.OrderHeader;
import pl.dolega.orderservice.orderHeader.OrderHeaderRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

//    @Transactional
//    public void readOrderData() {
//        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
//
//        orderHeader.getOrderLines().forEach(ol -> {
//            System.out.println(ol.getProduct().getDescription());
//            ol.getProduct().getCategories().forEach(
//                    cat -> System.out.println(cat.getDescription()));
//        });
//    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);

        System.out.println("Version is: " + savedCustomer.getVersion());
        customerRepository.deleteById(savedCustomer.getId());
    }

}
