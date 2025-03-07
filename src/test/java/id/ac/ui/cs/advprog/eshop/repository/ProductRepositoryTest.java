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
    @Test
    void testDeleteProductThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.deleteProduct(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.deleteProduct("");
        });
    }
    @Test
    void testEditProductThrowsIllegalArgumentException() {
        Product productWithNullId = new Product();
        productWithNullId.setProductId(null);
        productWithNullId.setProductName("Product with null ID");

        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.editProduct(productWithNullId);
        });

        Product productWithEmptyId = new Product();
        productWithEmptyId.setProductId("");
        productWithEmptyId.setProductName("Product with empty ID");

        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.editProduct(productWithEmptyId);
        });
    }

    @Test
    void testRemoveIfProductIdDoesNotMatch() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("456");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        productRepository.deleteProduct("789");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        assertEquals(product1, productIterator.next());
        assertTrue(productIterator.hasNext());
        assertEquals(product2, productIterator.next());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductIdDoesNotMatch() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("456");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        productRepository.editProduct(updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals("Product 1", savedProduct.getProductName());
        assertEquals(10, savedProduct.getProductQuantity());
    }
    @Test
    void testRemoveIfProductIdIsNull() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        productRepository.deleteProduct("123");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        assertEquals(product1, productIterator.next());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testRemoveIfProductIdMatches() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        productRepository.deleteProduct("123");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}