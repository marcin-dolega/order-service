package pl.dolega.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dolega.orderservice.customer.Customer;
import pl.dolega.orderservice.customer.CustomerRepository;
import pl.dolega.orderservice.orderHeader.OrderHeaderRepository;
import pl.dolega.orderservice.product.Product;
import pl.dolega.orderservice.product.ProductService;
import pl.dolega.orderservice.product.ProductStatus;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductService productService;

    private void updateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println("Updated Qty: " + savedProduct2.getQuantityOnHand());
    }

    @Override
    public void run(String... args) throws Exception {

        updateProduct();

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
