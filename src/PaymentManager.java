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
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.println("Please enter your card number: ");
            cardNumber = userInputToPaymentScanner.next();
            userInputToPaymentScanner.nextLine();
            if (UserInputValidator.checkCardValidation(cardNumber)) {
                 break;
            } else {
                System.out.println("Invalid card number.");
                if (attempts == 2) {
                    System.out.println("You have reached the maximum attempts for card number.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.println("Please enter the card holder name: ");
            cardHolderName = userInputToPaymentScanner.nextLine();
            if (cardHolderName != null && !cardHolderName.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Invalid credit card holder name.");
                if (attempts == 2) {
                    System.out.println("You have reached the maximum attempts for card holder name.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.println("Please enter the expiration date of the card (MM/YY): ");
            String cardExpirationDate = userInputToPaymentScanner.nextLine();
            if (UserInputValidator.dateComparision(cardExpirationDate)) {
                break;
            } else {
                System.out.println("Invalid expiration date.");
                if (attempts == 2) {
                    System.out.println("Your card has been expired, terminating transaction.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 2; attempts++) {
            System.out.println("Please enter your card verification value/code (4 digits): ");
            String cardVerificationValue = userInputToPaymentScanner.nextLine();
            if (UserInputValidator.isInteger(cardVerificationValue) && cardVerificationValue.length() == 4) {
                break;
            } else {
                System.out.println("Invalid verification code.");
                if (attempts == 1) {
                    System.out.println("You have reached the maximum attempts for verification code.");
                    return false;
                }
            }
        }

        System.out.println("Card details, card number ending with " + cardNumber.substring(12, 16) +
                " and cardholder name: " + cardHolderName);
        return true;
    }
}
