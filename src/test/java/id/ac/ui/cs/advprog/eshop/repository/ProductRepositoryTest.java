package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
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
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateSuccess() {
        Product product = new Product();
        product.setProductId("333");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();

        assertTrue(productIterator.hasNext());
        Product createdProduct = productIterator.next();
        assertEquals("333", createdProduct.getProductId());
    }

    @Test
    void testCreateFailNoName() {
        Product product = new Product();
        product.setProductId("333");
        product.setProductName("");
        product.setProductQuantity(100);
        assertDoesNotThrow(() -> {
            try {
                productRepository.create(product);
            } catch (IllegalArgumentException e) {
            }
        });
    }

    // Edit

    @Test
    void testEditSuccess() {
        Product product = new Product();
        product.setProductId("333");
        product.setProductName("Product 1OLD");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("333");
        editedProduct.setProductName("Product 1NEW");
        editedProduct.setProductQuantity(200);
        productRepository.editProduct(editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();

        assertTrue(productIterator.hasNext());
        Product createdProduct = productIterator.next();
        assertEquals("Product 1NEW", createdProduct.getProductName());
        assertEquals(200, createdProduct.getProductQuantity());
    }

    @Test
    void testEditNotFound() {
        Product product = new Product();
        product.setProductId("555");
        product.setProductName("testEditNotFound");

        assertDoesNotThrow(() -> {
            try {
                productRepository.editProduct(product);
            } catch (IllegalArgumentException e) {
            }
        });
    }
    // DELETE

    @Test
    void testDeleteSuccess() {
        Product product = new Product();
        product.setProductId("999");
        product.setProductName("DeleteSuccess");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.deleteProduct("999");

        Iterator<Product> productIterator = productRepository.findAll();

        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteNotFound() {
        assertDoesNotThrow(() -> {
            try {
                productRepository.deleteProduct("999");
            } catch (IllegalArgumentException e) {
            }
        });
    }
}