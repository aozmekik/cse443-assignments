public class TurboPaymentAdapter implements ModernPayment {
    private TurboPayment turboPayment;

    public TurboPaymentAdapter(TurboPayment turboPayment) {
        this.turboPayment = turboPayment;
    }

    @Override
    public int pay(String cardNo, float amount, String destination, String installments) {
        return turboPayment.payInTurbo(cardNo, amount, destination, installments);
    }

}
