public class UserInputValidator {

    boolean checkUserResponseToPreferredCoffee(String userResponseToPreferredCoffee, MenuManager menuManager){
        if(userResponseToPreferredCoffee == null || userResponseToPreferredCoffee.isEmpty()){
            return false;
        }
        else if(userResponseToPreferredCoffee.equalsIgnoreCase("q")){
            return false;
        }
        else return menuManager.checkCoffee(userResponseToPreferredCoffee);
    }

    boolean checkOrderQuantity(String userResponseToOrderQuantity){
        if(userResponseToOrderQuantity == null || userResponseToOrderQuantity.isEmpty()) {
            return false;
        }
        else if(userResponseToOrderQuantity.equalsIgnoreCase("q")){
            return false;
        } else {
            try {
                Integer.parseInt(userResponseToOrderQuantity);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    String checkPaymentMethod(String userPaymentMethodChoice) {
        if (userPaymentMethodChoice == null || userPaymentMethodChoice.trim().isEmpty()) {
            return "Invalid payment method, terminating transaction";
        }

        userPaymentMethodChoice = userPaymentMethodChoice.trim();

        if (userPaymentMethodChoice.equalsIgnoreCase("q")) {
            return "Order Cancellation, terminating transaction";
        } else if (userPaymentMethodChoice.equalsIgnoreCase("1")) {
            return "Cash";
        } else if (userPaymentMethodChoice.equalsIgnoreCase("2")) {
            return "Credit card";
        } else {
            return "Invalid payment method, terminating transaction";
        }
    }

    boolean checkCardValidation(String cardNumber){
        if (cardNumber == null || cardNumber.trim().isEmpty() || cardNumber.equalsIgnoreCase("q")) {
            return false;
        }
        return cardNumber.length() == 16;
    }

    boolean checkUserResponseToOrderMoreItem(String userResponseToOrderMore) {
        if (userResponseToOrderMore == null || userResponseToOrderMore.trim().isEmpty()) {
            return false;
        } else if (userResponseToOrderMore.equalsIgnoreCase("n")) {
            return false;
        } else if (!"y".equalsIgnoreCase(userResponseToOrderMore)) {
            return false;
        } else {
            return true;
        }
    }
}
