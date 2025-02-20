import java.util.Scanner;

public class PaymentManager {
    Scanner userInputToPaymentScanner = new Scanner(System.in);

    public boolean makePayment(double totalPrice){
        System.out.println("How would you like to pay?");
        System.out.println("1. Cash, 2.Credit card");
        String paymentMethod = userInputToPaymentScanner.next();
        String checkedPaymentMethod = UserInputValidator.checkPaymentMethod(paymentMethod);

        if (checkedPaymentMethod.contains("Invalid") || checkedPaymentMethod.contains("terminating transaction")) {
            System.out.println(checkedPaymentMethod);
            return false;
        } else {
            System.out.println("Proceeding with payment method: " + checkedPaymentMethod);
            if(checkedPaymentMethod.equalsIgnoreCase("cash")){
                Payment payment = new CashPayment();
                payment.makePayment(totalPrice);
            } else if (checkedPaymentMethod.contains("Credit")) {
                System.out.println("-------Please enter your card details-------");
                System.out.println("Please enter your card number: ");
                String cardNumber = userInputToPaymentScanner.next();

                userInputToPaymentScanner.nextLine();

                if(UserInputValidator.checkCardValidation(cardNumber)) {
                    System.out.println("Please enter the card holder name: ");
                    String cardHolderName = userInputToPaymentScanner.nextLine();
                    if(cardHolderName !=null  && !cardHolderName.trim().isEmpty()){
                        Payment payment = new CardPayment(cardNumber, cardHolderName);
                        payment.makePayment(totalPrice);
                    }else {
                        System.out.println("Invalid card holder name");
                        return false;
                    }
                }else {
                    System.out.println("Invalid card number");
                    return false;
                }
            }
            return true;
        }
    }
}
