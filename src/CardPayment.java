public class CardPayment implements Payment{
    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("--processing payment---");
        System.out.println("Payment of Rs." + amount + " made using Card ending with: " + cardNumber.substring(cardNumber.length() - 4)+" belonging to: "+cardHolderName);
        System.out.println("Thank you");
    }
}
