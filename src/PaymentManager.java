
public class PaymentManager {
    private PaymentFactory paymentFactory;
    private String cardNumber;
    private String cardHolderName;

    public boolean makePayment(double price) {
        PaymentMethod paymentMethod = getPaymentMethod();
        if (paymentMethod == null) {
            return false;
        }
        Utility.logMessageWithArgument("Proceeding payment with: ",paymentMethod.getDescription());
        Payment payment = createPayment(paymentMethod);
        if (payment == null) {
            return false;
        }

        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            if (!processCreditCardPayment()) {
                return false;
            }
        } else if (paymentMethod == PaymentMethod.CASH) {
            Utility.logMessagePrompt("Proceeding with cash payment");
        }

        payment.makePayment(price);
        return true;
    }

    private PaymentMethod getPaymentMethod() {
        Utility.logMessagePrompt("How would you like to pay? ");
        Utility.logMessagePrompt("1. Cash, 2. Credit card");
        String userPaymentMethod = Utility.getStringInput();

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
            Utility.logMessagePrompt("Please enter your card number: ");
            cardNumber = Utility.getStringInput();
            if (UserInputValidator.checkCardValidation(cardNumber)) {
                 break;
            } else {
                Utility.logMessagePrompt("Invalid card number.");
                if (attempts == 2) {
                    Utility.logMessagePrompt("You have reached the maximum attempts for card number.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 3; attempts++) {
            Utility.logMessagePrompt("Please enter the card holder name: ");
            cardHolderName = Utility.getStringInput();
            if (cardHolderName != null && !cardHolderName.trim().isEmpty()) {
                break;
            } else {
                Utility.logMessagePrompt("Invalid credit card holder name.");
                if (attempts == 2) {
                    Utility.logMessagePrompt("You have reached the maximum attempts for card holder name.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 3; attempts++) {
            Utility.logMessagePrompt("Please enter the expiration date of the card (MM/YY): ");
            String cardExpirationDate = Utility.getStringInput();
            if (UserInputValidator.dateComparision(cardExpirationDate)) {
                break;
            } else {
                Utility.logMessagePrompt("Invalid expiration date.");
                if (attempts == 2) {
                    Utility.logMessagePrompt("Your card has been expired, terminating transaction.");
                    return false;
                }
            }
        }

        for (int attempts = 0; attempts < 2; attempts++) {
            Utility.logMessagePrompt("Please enter your card verification value/code (4 digits): ");
            String cardVerificationValue = Utility.getStringInput();
            if (UserInputValidator.isInteger(cardVerificationValue) && cardVerificationValue.length() == 4) {
                break;
            } else {
                Utility.logMessagePrompt("Invalid verification code.");
                if (attempts == 1) {
                    Utility.logMessagePrompt("You have reached the maximum attempts for verification code.");
                    return false;
                }
            }
        }
        Utility.logMessageWithArgument("Card details, card number ending with %s and cardholder name: %s",
                cardNumber.substring(12, 16), cardHolderName);
        return true;
    }
}
