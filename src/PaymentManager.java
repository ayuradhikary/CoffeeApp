import java.util.Scanner;

public class PaymentManager {
    private Scanner userInputToPaymentScanner = new Scanner(System.in);
    private PaymentFactory paymentFactory;
    private String cardNumber;
    private String cardHolderName;

    public boolean makePayment(double price) {
        PaymentMethod paymentMethod = getPaymentMethod();
        if (paymentMethod == null) {
            return false;
        }
        System.out.println("Proceeding payment with " + paymentMethod.getDescription());
        Payment payment = createPayment(paymentMethod);
        if (payment == null) {
            return false;
        }

        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            if (!processCreditCardPayment()) {
                return false;
            }
        } else if (paymentMethod == PaymentMethod.CASH) {
            System.out.println("Proceeding with cash payment.");
        }

        payment.makePayment(price);
        return true;
    }

    private PaymentMethod getPaymentMethod() {
        System.out.println("How would you like to pay? ");
        System.out.println("1. Cash, 2. Credit card");
        String userPaymentMethod = userInputToPaymentScanner.next();

        if (!UserInputValidator.isInteger(userPaymentMethod)) {
            return null;
        }

        return UserInputValidator.checkPaymentMethod(userPaymentMethod);
    }

    private Payment createPayment(PaymentMethod paymentMethod) {
        paymentFactory = new PaymentFactory();
        return paymentFactory.createPayment(paymentMethod);
    }

    private boolean processCreditCardPayment() {
        System.out.println("Please enter your card number: ");
        cardNumber = userInputToPaymentScanner.next();
        userInputToPaymentScanner.nextLine();
        if (!UserInputValidator.checkCardValidation(cardNumber)) {
            return false;
        }
        System.out.println("Please enter the card holder name: ");
        cardHolderName = userInputToPaymentScanner.nextLine();
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            System.out.println("Invalid credit card holder name");
            return false;
        }
        System.out.println("please enter the expitation date of the card: ");
        String cardExpirationDate = userInputToPaymentScanner.nextLine();
        System.out.println("card expiration date entered");
        if(!UserInputValidator.dateComparision(cardExpirationDate)){
            System.out.println("your card has been expired, terminating transaction.");
            return false;
        }
        System.out.println("Please enter your card verification value/code: ");
        String cardVerificationValue = userInputToPaymentScanner.nextLine();
        if(!UserInputValidator.isInteger(cardVerificationValue)){
            System.out.println("Invalid verification code");
            return false;
        }
        if(cardVerificationValue.length() != 4){
            System.out.println("The length of card verification value must be equal to 4, terminating order!");
            return false;
        }
        System.out.println("Card details, card number ending with " + cardNumber.substring(12, 16) +
                " and cardholder name: " + cardHolderName);
        return true;
    }
}
