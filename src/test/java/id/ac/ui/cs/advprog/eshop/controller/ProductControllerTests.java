package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }

@Test
void testCreateProductPage() {
    String viewName = productController.createProductPage(model);
    assertEquals("createProduct", viewName);

    Product expectedProduct = new Product();
    Product actualProduct = (Product) model.getAttribute("product");

    assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
    assertEquals(expectedProduct.getProductName(), actualProduct.getProductName());
    assertEquals(expectedProduct.getProductQuantity(), actualProduct.getProductQuantity());
}

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        verify(productService, times(1)).create(product);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.productListPage(model);
        assertEquals("productList", viewName);
        assertEquals(products, model.getAttribute("products"));
    }

    @Test
    void testDeleteProduct() {
        String productId = "123";
        String viewName = productController.deleteProduct(productId);
        verify(productService, times(1)).deleteProduct(productId);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        product.setProductId(productId);
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.editProductPage(productId, model);
        assertEquals("editProduct", viewName);
        assertEquals(product, model.getAttribute("product"));
    }

    @Test
    void testEditProductPageNotFound() {
        String productId = "123";
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.editProductPage(productId, model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String viewName = productController.editProductPost(product, model);
        verify(productService, times(1)).editProduct(product);
        assertEquals("redirect:list", viewName);
    }
    @Test
    void testEditProductPageProductNotFound() {
        String productId = "123";
        Product product = new Product();
        product.setProductId("456"); // Different productId
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.editProductPage(productId, model);
        assertEquals("redirect:/product/list", viewName);
    }
}