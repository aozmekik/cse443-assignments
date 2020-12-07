
/**
 * ModernPayment Payment interface with the modern methods.
 */

public interface ModernPayment {
    /**
     * @param cardNo is card number.
     * @param amount money amount of transaction
     * @param destination IBAN.
     */
    int pay(String cardNo, float amount, String destination, String installments);
}