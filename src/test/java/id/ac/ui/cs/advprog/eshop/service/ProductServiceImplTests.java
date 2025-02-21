package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testCreateProductWithNullId() {
        product.setProductId(null);
        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testCreateProductWithEmptyId() {
        product.setProductId("");
        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        Iterator<Product> productIterator = products.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> foundProducts = productService.findAll();
        assertEquals(products, foundProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteProduct(product.getProductId());
        productService.deleteProduct(product.getProductId());
        verify(productRepository, times(1)).deleteProduct(product.getProductId());
    }

    @Test
    void testDeleteProductWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(null));
    }

    @Test
    void testDeleteProductWithEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(""));
    }

    @Test
    void testEditProduct() {
        when(productRepository.editProduct(any(Product.class))).thenReturn(product);
        Product editedProduct = productService.editProduct(product);
        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).editProduct(product);
    }

    @Test
    void testEditProductWithNullId() {
        product.setProductId(null);
        assertThrows(IllegalArgumentException.class, () -> productService.editProduct(product));
    }

    @Test
    void testEditProductWithEmptyId() {
        product.setProductId("");
        assertThrows(IllegalArgumentException.class, () -> productService.editProduct(product));
    }
}