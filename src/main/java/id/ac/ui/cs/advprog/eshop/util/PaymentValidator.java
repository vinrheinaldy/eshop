package id.ac.ui.cs.advprog.eshop.util;
import enums.PaymentMethod;
import java.util.Map;

public class PaymentValidator {

    public static boolean isValidPaymentData(String paymentMethod, Map<String, String> paymentData) {
        if (paymentMethod.equals(PaymentMethod.VOUCHER.getValue())) {
            return paymentData.containsKey("voucherCode") && VoucherValidator.isValidVoucher(paymentData.get("voucherCode"));
        } else if (paymentMethod.equals(PaymentMethod.CASH.getValue())){
            return paymentData.containsKey("address") && paymentData.containsKey("deliveryFee");
        } else if (paymentMethod.equals(PaymentMethod.BANK.getValue())){
            return paymentData.containsKey("bankName") && paymentData.containsKey("referenceCode");
        }
        return false;
    }
}