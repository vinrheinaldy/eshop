package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import enums.PaymentStatus;
import enums.PaymentMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void Setup() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);
        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        order.setStatus(OrderStatus.SUCCESS.getValue());
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentValid() {
        Payment payment = new Payment("d4e5f6a7-b8c9-0123-4567-89abcdef0123", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData, order);

        assertNotNull(payment);
        assertEquals("d4e5f6a7-b8c9-0123-4567-89abcdef0123", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatPaymentNotValidStatus(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("d4e5f6a7-b8c9-0123-4567-89abcdef0123", PaymentMethod.VOUCHER.getValue(), "PENDING", paymentData, order);
        });
    }

    @Test
    void testCreatPaymentNotValidMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("d4e5f6a7-b8c9-0123-4567-89abcdef0123", "CREDIT", PaymentStatus.SUCCESS.getValue(), paymentData, order);
        });
    }
}