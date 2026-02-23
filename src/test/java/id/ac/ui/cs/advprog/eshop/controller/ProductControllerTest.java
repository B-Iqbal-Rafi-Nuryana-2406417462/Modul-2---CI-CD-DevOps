package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    private String createdProductId;

    @BeforeEach
    void setUp() {
        // Buat produk nyata di repository sebelum setiap test yang butuh data awal
        Product product = new Product();
        product.setProductName("Sabun Mandi");
        product.setProductQuantity(10);
        Product saved = productService.create(product);
        createdProductId = saved.getProductId();
    }

    @Test
    void createProductPage_ShouldReturnCreateViewWithModel() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createProductPost_ShouldCreateAndRedirectToList() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productName", "Sampo Rambut")
                        .param("productQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void productListPage_ShouldReturnListViewWithProducts() throws Exception {
        MvcResult result = mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"))
                .andReturn();

        assertNotNull(result.getModelAndView());
        Object products = result.getModelAndView().getModel().get("products");
        assertNotNull(products);
    }

    @Test
    void editProductPage_ShouldReturnEditViewWithProduct() throws Exception {
        mockMvc.perform(get("/product/edit/" + createdProductId))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void updateProduct_ShouldUpdateAndRedirectToList() throws Exception {
        mockMvc.perform(post("/product/update")
                        .param("productId", createdProductId)
                        .param("productName", "Sabun Mandi Premium")
                        .param("productQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Product updated = productService.findById(createdProductId);
        assertNotNull(updated);
        assertEquals("Sabun Mandi Premium", updated.getProductName());
        assertEquals(20, updated.getProductQuantity());
    }

    @Test
    void deleteProduct_ShouldDeleteAndRedirectToList() throws Exception {
        mockMvc.perform(get("/product/delete/" + createdProductId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Product deleted = productService.findById(createdProductId);
        assertNull(deleted);
    }
}

