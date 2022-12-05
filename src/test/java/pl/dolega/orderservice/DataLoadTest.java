package pl.dolega.orderservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.dolega.orderservice.address.Address;
import pl.dolega.orderservice.customer.Customer;
import pl.dolega.orderservice.customer.CustomerRepository;
import pl.dolega.orderservice.orderHeader.OrderHeader;
import pl.dolega.orderservice.orderHeader.OrderHeaderRepository;
import pl.dolega.orderservice.orderLine.OrderLine;
import pl.dolega.orderservice.product.Product;
import pl.dolega.orderservice.product.ProductRepository;
import pl.dolega.orderservice.product.ProductStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {

    final String PRODUCT_D1 = "Product 1";
    final String PRODUCT_D2 = "Product 2";
    final String PRODUCT_D3 = "Product 3";

    final String TEST_CUSTOMER = "TEST CUSTOMER";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void testLazyVsEager() {
        OrderHeader orderHeader = orderHeaderRepository.getReferenceById(5L);
        System.out.println("Order Id is: " + orderHeader.getId());
        System.out.println("Customer Name is: " + orderHeader.getCustomer().getCustomerName());
    }

    @Disabled
    @Rollback(value = false)
    @Test
    void testDataLoader() {
        List<Product> products = loadProducts();
        Customer customer = loadCustomers();

        int ordersToCreate = 1000;

        for (int i = 0; i < ordersToCreate; i++) {
            System.out.println("Creating order #: " + i);
            saveOrder(customer, products);
        }

        orderHeaderRepository.flush();
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products) {
        Random random = new Random();

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(20));
            orderHeader.addOrderLine(orderLine);
        });
        return orderHeaderRepository.save(orderHeader);
    }

    private Customer loadCustomers() {
        return getOrSaveCustomer(TEST_CUSTOMER);
    }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
                .orElseGet(() -> {
                    Customer c1 = new Customer();
                    c1.setCustomerName(customerName);
                    c1.setEmail("test@example.com");
                    Address address = new Address();
                    address.setAddress("123 Main");
                    address.setCity("New Orleans");
                    address.setState("LA");
                    c1.setAddress(address);
                    return customerRepository.save(c1);
                });
    }

    private List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        products.add(getOrSaveProduct(PRODUCT_D1));
        products.add(getOrSaveProduct(PRODUCT_D2));
        products.add(getOrSaveProduct(PRODUCT_D3));
        return products;
    }

    private Product getOrSaveProduct(String description) {
        return productRepository.findByDescription(description)
                .orElseGet(() -> {
                    Product p1 = new Product();
                    p1.setDescription(description);
                    p1.setProductStatus(ProductStatus.NEW);
                    return productRepository.save(p1);
                });
    }
}
