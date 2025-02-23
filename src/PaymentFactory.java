public class PaymentFactory {
    public Payment createPayment(PaymentMethod paymentMethod){
        if(paymentMethod == PaymentMethod.CASH){
           return new CashPayment();
        }else if(paymentMethod == PaymentMethod.CREDIT_CARD) {
           return new CardPayment();
        }else {
            return null;
        }
    }
}
