package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    private List<Payment> paymentData = new ArrayList<>();
    public Payment create(final Payment payment) {
        paymentData.add(payment);
        return payment;
    }
    public Payment findById(final String paymentId) {
        return paymentData.stream()
                .filter(payment -> payment.getId().equals(paymentId))
                .findFirst()
                .orElse(null);
    }
    public Boolean existById(final String productId) {
        return paymentData.stream()
                .anyMatch(payment -> payment.getId().equals(productId));
    }
    public Payment delete(final Payment payment) {
        paymentData.remove(payment);
        return payment;
    }
    public List<Payment> findAll() {
        return new ArrayList<>(paymentData);
    }

}