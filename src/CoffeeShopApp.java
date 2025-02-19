import java.util.Scanner;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        UserInputValidator userInputValidator = new UserInputValidator(menuManager);
        CoffeeOrderFactory coffeeOrderFactory = new CoffeeOrderFactory();

        boolean running = true;
        while (running){
            System.out.println("----Welcome------");
            System.out.println("Here is our Menu");
            menuManager.displayMenu();
            System.out.println("Choose your preferred coffee or press Q to exit: ");
            String userResponseToPreferredCoffee = userInputScanner.nextLine();

            if(userInputValidator.checkEquals(userResponseToPreferredCoffee)){
                System.out.println("How many of "+ userResponseToPreferredCoffee +" would you like to order? ");
                int quantity = userInputScanner.nextInt();
                // add validation
                coffeeOrderFactory.createOrder(menuManager,userResponseToPreferredCoffee,quantity);
                coffeeOrderFactory.orderDetails();
                break;
            }
            else{
                break;
            }

        }
    }
}
