package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("P002");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("P002");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("P003");
        product2.setProductName("Sabun Mandi Aroma Jeruk");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdFound() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("P001");
        assertNotNull(foundProduct);
        assertEquals("P001", foundProduct.getProductId());
        assertEquals("Sabun Cuci Piring", foundProduct.getProductName());
        assertEquals(75, foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("P999");
        assertNull(foundProduct);
    }

    @Test
    void testEditExistingProduct_ShouldUpdateSuccessfully() {
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sabun Cuci Piring");
        product.setProductQuantity(75);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("P001");
        updatedProduct.setProductName("Sabun Cuci Piring Ultra");
        updatedProduct.setProductQuantity(100);

        Product savedProduct = productRepository.save(updatedProduct);
        assertNotNull(savedProduct);
        assertEquals("Sabun Cuci Piring Ultra", savedProduct.getProductName());
        assertEquals(100, savedProduct.getProductQuantity());

        Product foundProduct = productRepository.findById("P001");
        assertEquals("Sabun Cuci Piring Ultra", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testDeletingProduct_ShouldReturnNull() {
        Product product = new Product();
        ProductRepository productRepository = new ProductRepository();
        product.setProductId("P999");
        product.setProductName("Produk Tidak Ada");
        product.setProductQuantity(10);
        productRepository.save(product);
        productRepository.deleteProduct(product.getProductId());
        assertNull(productRepository.findById("P999"));
    }
}
