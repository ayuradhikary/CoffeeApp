public class CardPayment implements Payment{

    @Override
    public void makePayment(double amount) {
        Utility.logMessagePrompt("--processing payment---");
        Utility.logMessageWithArgument("The payment of %s made using credit card", amount);
    }

}
