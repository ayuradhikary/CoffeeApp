public class CashPayment implements Payment {
    @Override
    public void makePayment(double amount) {
        System.out.println("-----Processing payment-----");
        System.out.println("Payment received");
        System.out.println("Payment of Rs." + amount +" made using Cash. Thank you.");
    }
}
