import java.util.Scanner;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        UserInputValidator userInputValidator = new UserInputValidator();

        boolean running = true;
        while (running){
            System.out.println("----Welcome------");
            System.out.println("Here is our Menu");
            menuManager.displayMenu();
            System.out.println("Choose your preferred coffee or press Q to exit: ");
            String userResponseToPreferredCoffee = userInputScanner.next();

            if(userInputValidator.checkEquals(userResponseToPreferredCoffee)){
                System.out.println("Order logic");
                //writing order logic
                break;
            }
            else{
                break;
            }

        }
    }
}
