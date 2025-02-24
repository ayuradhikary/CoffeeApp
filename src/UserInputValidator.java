import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInputValidator {

    public static Boolean checkUserResponseToPreferredCoffee(String userResponseToPreferredCoffee, MenuManager menuManager){
        if(userResponseToPreferredCoffee == null || userResponseToPreferredCoffee.isEmpty()){
            return null;
        }
        else if(userResponseToPreferredCoffee.equalsIgnoreCase("q")){
            return false;
        }
        else if(menuManager.checkCoffee(userResponseToPreferredCoffee)){
            return true;
        }else return null;
    }

    public static boolean isInteger(String userResponse){
        if(userResponse == null || userResponse.trim().isEmpty()) {
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

    public static PaymentMethod checkPaymentMethod(String userPaymentMethodChoice) {
        if("1".equals(userPaymentMethodChoice)){
            return PaymentMethod.CASH;
        }else if ("2".equals(userPaymentMethodChoice)){
            return PaymentMethod.CREDIT_CARD;
        }else {
            return null;
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
        Utility.logMessagePrompt("Invalid response");
    }

    public static boolean dateComparision(String date){
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/yy");
        try {
            Date parsedDate = inputFormat.parse(date);
            Date currentDate = new Date();
            return !parsedDate.before(currentDate);
        } catch (ParseException e) {
            System.out.println("Exception: " +e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
