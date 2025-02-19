import java.util.Scanner;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        UserInputValidator userInputValidator = new UserInputValidator();
        CoffeeOrderFactory coffeeOrderFactory = new CoffeeOrderFactory();
        PaymentManager paymentManager = new PaymentManager();

        System.out.println("--------Welcome------");

        boolean running = true;
        while (running){

            System.out.println("Here is our Menu");
            menuManager.displayMenu();
            System.out.println("Choose your preferred coffee or press Q to exit: ");
            String userResponseToPreferredCoffee = userInputScanner.nextLine();

            if(userInputValidator.checkUserResponseToPreferredCoffee(userResponseToPreferredCoffee,menuManager)){
                System.out.println("How many of "+ userResponseToPreferredCoffee +" would you like to order? ");
                String quantity = userInputScanner.next();
                userInputScanner.nextLine();
                if(userInputValidator.checkOrderQuantity(quantity)){
                    coffeeOrderFactory.createOrder(menuManager,userResponseToPreferredCoffee,quantity);
                    coffeeOrderFactory.orderDetails();
                    System.out.println("-----preparing your coffee----");

                    boolean paymentSuccessful = paymentManager.makePayment(userInputValidator, coffeeOrderFactory.getTotalPrice());
                    if(!paymentSuccessful){
                        System.out.println("Payment failed, terminating order");
                        running = false;
                    }else{
                        CoffeeMaker coffeeMaker = new CoffeeMaker();
                        coffeeMaker.setCoffeeName(userResponseToPreferredCoffee);
                        coffeeMaker.setCoffeeOrderFactory(coffeeOrderFactory);
                        coffeeMaker.setQuantity(quantity);
                        Coffee coffee = coffeeMaker.make();
                        coffee.makeCoffee();
                        HandleAdditionalOrder handleAdditionalOrder = new HandleAdditionalOrder(userInputScanner,userInputValidator,coffeeOrderFactory,menuManager);
                        handleAdditionalOrder.handleAdditionalPayment();
                        running = false;
                    }
                }else{
                    System.out.println("Invalid response, please enter a valid qualtity.");
                    running = false;
                }
            }
            else {
                System.out.println("Invalid response, please choose a valid option.");
                running = false;
            }
            userInputScanner.close();
        }
    }
}
