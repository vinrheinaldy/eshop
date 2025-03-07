package id.ac.ui.cs.advprog.eshop.repository;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepositoryImpl();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        order.setStatus(OrderStatus.SUCCESS.getValue());
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = new Payment("d4e5f6a7-b8c9-0123-4567-89abcdef0123", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData, order);
        Payment payment2 = new Payment("e7b8f9a0-1234-5678-9abc-def012345678", PaymentMethod.BANK.getValue(), PaymentStatus.REJECTED.getValue(), paymentData, order);
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.create(payment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        paymentRepository.create(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment payment = payments.get(1);
        paymentRepository.create(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertNull(findResult);
    }

    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.create(payment);
        }

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(paymentList.size(), payments.size());
    }

}