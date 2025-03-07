package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository {
    Payment create(Payment car);
    List<Payment> findAll();
    Payment findById(String id);
    Payment delete(Payment payment);
}