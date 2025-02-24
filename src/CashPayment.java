public class CashPayment implements Payment {
    @Override
    public void makePayment(double amount) {
        Utility.logMessagePrompt("-----Processing payment-----");
        Utility.logMessagePrompt("Payment received");
    }
}
