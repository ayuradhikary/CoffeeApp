public class UserInputValidator {

    public static boolean  checkUserResponseToPreferredCoffee(String userResponseToPreferredCoffee, MenuManager menuManager){
        if(userResponseToPreferredCoffee == null || userResponseToPreferredCoffee.isEmpty()){
            return false;
        }
        else if(userResponseToPreferredCoffee.equalsIgnoreCase("q")){
            return false;
        }
        else return menuManager.checkCoffee(userResponseToPreferredCoffee);
    }

    public static boolean isInteger(String userResponse){
        if(userResponse == null || userResponse.isEmpty()) {
            return false;
        }
        else if(userResponse.equalsIgnoreCase("q")){
            return false;
        } else {
            try {
                Integer.parseInt(userResponse);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public static String checkPaymentMethod(String userPaymentMethodChoice) {
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

    public static boolean checkCardValidation(String cardNumber){
        if (cardNumber == null || cardNumber.trim().isEmpty() || cardNumber.equalsIgnoreCase("q")) {
            return false;
        }
        return cardNumber.length() == 16;
    }

    public static boolean checkUserResponseToOrderMoreItem(String userResponseToOrderMore) {
        if (userResponseToOrderMore == null || userResponseToOrderMore.trim().isEmpty()) {
            return false;
        } else if ("n".equalsIgnoreCase(userResponseToOrderMore)) {
            return false;
        } else if (!"y".equalsIgnoreCase(userResponseToOrderMore)) {
            return false;
        } else {
            return true;
        }
    }

    public static void userInvalidResponse(){
        System.out.println("Invalid response");
    }

}
