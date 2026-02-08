package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String productId){
        for (Product product : productData){
            if (product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    public Product save(Product product){
        Product existingProduct = findById(product.getProductId());
        if (existingProduct != null){
            int indexOfExistingProduct = productData.indexOf(existingProduct);
            productData.set(indexOfExistingProduct, product);
            return product;
        }
        return null;
    }

    public void deleteProduct(String productId){
        Product productToDelete = findById(productId);
        if (productToDelete != null){
            productData.remove(productToDelete);
        }
    }
}
