package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {

    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("P001");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }
    @Test
    void testGetProductId(){
        assertEquals("P001", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testSetProductId() {
        this.product.setProductId("P999");
        assertEquals("P999", this.product.getProductId());
    }

    @Test
    void testSetProductName() {
        this.product.setProductName("Sabun Mandi");
        assertEquals("Sabun Mandi", this.product.getProductName());
    }

    @Test
    void testSetProductQuantity() {
        this.product.setProductQuantity(50);
        assertEquals(50, this.product.getProductQuantity());
    }

    @Test
    void testDefaultProductValues() {
        Product emptyProduct = new Product();
        assertNull(emptyProduct.getProductId());
        assertNull(emptyProduct.getProductName());
        assertEquals(0, emptyProduct.getProductQuantity());
    }
}

