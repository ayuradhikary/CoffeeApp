public class CardPayment implements Payment{

    @Override
    public void makePayment(double amount) {
        System.out.println("--processing payment---");
        System.out.print("The payment of "+ amount +" made using credit card");
    }

}
