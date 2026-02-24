package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public ProductRepository() {}

    public Product create(final Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(final String productId){
        Product result = null;
        for (final Product product : productData){
            if (product.getProductId().equals(productId)){
                result = product;
                break;
            }
        }
        return result;
    }

    public Product save(final Product product){
        final Product existingProduct = findById(product.getProductId());
        Product result = null;
        if (existingProduct != null){
            final int idx = productData.indexOf(existingProduct);
            productData.set(idx, product);
            result = product;
        }
        return result;
    }

    public void deleteProduct(final String productId){
        final Product productToDelete = findById(productId);
        if (productToDelete != null){
            productData.remove(productToDelete);
        }
    }
}
