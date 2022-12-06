package pl.dolega.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dolega.orderservice.customer.Customer;
import pl.dolega.orderservice.customer.CustomerRepository;
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

        customer.setCustomerName("Testing Version 0");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 1");
        Customer savedCustomer1 = customerRepository.save(savedCustomer);
        System.out.println("Version is: " + savedCustomer1.getVersion());


        savedCustomer1.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer1);
        System.out.println("Version is: " + savedCustomer2.getVersion());

        customerRepository.deleteById(savedCustomer.getId());
    }

}
