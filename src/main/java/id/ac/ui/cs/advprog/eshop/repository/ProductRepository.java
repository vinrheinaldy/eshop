package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteProduct(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        productData.removeIf(product -> product.getProductId() != null && product.getProductId().equals(productId));
    }

    public Product editProduct(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                break;
            }
        }
        return product;
    }
}
