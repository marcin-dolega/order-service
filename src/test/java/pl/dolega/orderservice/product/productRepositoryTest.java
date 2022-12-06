package pl.dolega.orderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
public class productRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void testGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1").get();

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productRepository.save(product);

        Product fetchedProduct = productRepository.getReferenceById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
    }

    @Test
    void addAndUpdateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        assertNotNull(savedProduct2);
        assertEquals(25, savedProduct2.getQuantityOnHand());
    }
}
