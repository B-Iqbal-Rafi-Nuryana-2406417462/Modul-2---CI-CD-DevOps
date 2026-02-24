package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl() {}

    @Override
    public Product create(final Product product){
        product.setProductId(UUID.randomUUID().toString());
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll(){
        final Iterator<Product> productIterator = productRepository.findAll();
        final List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(final String productId){
        return productRepository.findById(productId);
    }

    @Override
    public Product updateProduct(final Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(final String productId){
        productRepository.deleteProduct(productId);
    }
}
