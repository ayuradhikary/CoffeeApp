import java.util.Scanner;

public class HandleAdditionalOrder {
    Scanner userInputScanner;
    UserInputValidator userInputValidator;
    CoffeeOrderFactory coffeeOrderFactory;
    MenuManager menuManager;
    PaymentManager paymentManager;
    boolean running = true;

    public HandleAdditionalOrder(Scanner userInputScanner, UserInputValidator userInputValidator, CoffeeOrderFactory coffeeOrderFactory, MenuManager menuManager) {
        this.userInputScanner = userInputScanner;
        this.userInputValidator = userInputValidator;
        this.coffeeOrderFactory = coffeeOrderFactory;
        this.menuManager = menuManager;
        this.paymentManager = new PaymentManager();
    }

    public void handleAdditionalPayment() {
        while (running) {
            System.out.println("Would you like to order something else(Y/N)? ");
            String userResponseToOrderMore = userInputScanner.next();
            userInputScanner.nextLine();
            if (userInputValidator.checkUserResponseToOrderMoreItem(userResponseToOrderMore)) {
                System.out.println("What would you like to order additionally? ");
                menuManager.displayMenu();
                String userResponseToAdditionalPreferredCoffee = userInputScanner.nextLine();
                if (userInputValidator.checkUserResponseToPreferredCoffee(userResponseToAdditionalPreferredCoffee, menuManager)) {
                    System.out.println("How many of "+ userResponseToAdditionalPreferredCoffee +" would you like to order? ");
                    String quantity = userInputScanner.next();
                    userInputScanner.nextLine();
                    if(userInputValidator.checkOrderQuantity(quantity)){
                        coffeeOrderFactory.addAdditionalOrder(menuManager,userResponseToAdditionalPreferredCoffee,quantity);
                        coffeeOrderFactory.orderDetails();
                        System.out.println("-----preparing your coffee----");

                        boolean paymentSuccessful = paymentManager.makePayment(userInputValidator, coffeeOrderFactory.getAdditionalAmount());
                        if(!paymentSuccessful){
                            System.out.println("Payment failed, terminating order");
                            running = false;
                        }else{
                            CoffeeMaker coffeeMaker = new CoffeeMaker();
                            coffeeMaker.setCoffeeName(userResponseToAdditionalPreferredCoffee);
                            coffeeMaker.setCoffeeOrderFactory(coffeeOrderFactory);
                            coffeeMaker.setQuantity(quantity);
                            Coffee coffee = coffeeMaker.make();
                            coffee.makeCoffee();
                            continue;
                        }
                    }else{
                        System.out.println("Invalid response, please enter a valid qualtity.");
                    }
                }
                running = false;
            } else {
                System.out.println("Thank you for choosing us!");
                running = false;
            }
            userInputScanner.close();
        }
    }
}