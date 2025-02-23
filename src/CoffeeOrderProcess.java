import java.awt.desktop.AboutEvent;
import java.util.Objects;
import java.util.Scanner;

public class CoffeeOrderProcess {
    MenuManager menuManager;
    CoffeeMaker coffeeMaker;
    PaymentManager paymentManager;
    CoffeeOrderFactory coffeeOrderFactory;
    Scanner userInputScanner;
    String userResponseToPreferredCoffee;
    String quantity;
    String userResponseToAdditionalPreferredCoffee;
    String additionalQuantity;

    public CoffeeOrderProcess(MenuManager menuManager, CoffeeMaker coffeeMaker, PaymentManager paymentManager,CoffeeOrderFactory coffeeOrderFactory){
        this.menuManager = menuManager;
        this.coffeeMaker = coffeeMaker;
        this.paymentManager = paymentManager;
        this.coffeeOrderFactory = coffeeOrderFactory;
        userInputScanner = new Scanner(System.in);

    }

    public void displayMenu(){
        menuManager.displayMenu();
    }

    public boolean getUserCoffeeChoice(){
        while (true){
            System.out.println("please enter your coffee choice: ");
            userResponseToPreferredCoffee = userInputScanner.nextLine();
            Object userResponseChecked = UserInputValidator.checkUserResponseToPreferredCoffee(userResponseToPreferredCoffee, menuManager);
            if(userResponseChecked == null){
                UserInputValidator.userInvalidResponse();
                continue;
            } else if (userResponseChecked instanceof Boolean  booleanObj) {
                return booleanObj;
            }
        }
    }

    public boolean getUserAdditionalCoffeeChoice(){
        while (true){
            System.out.println("please enter your coffee choice: ");
            userResponseToAdditionalPreferredCoffee = userInputScanner.nextLine();
            Object userResponseChecked = UserInputValidator.checkUserResponseToPreferredCoffee(userResponseToAdditionalPreferredCoffee, menuManager);
            if(userResponseChecked == null){
                UserInputValidator.userInvalidResponse();
                continue;
            } else if (userResponseChecked instanceof Boolean  booleanObj) {
                return booleanObj;
            }
        }
    }

    public boolean getOrderQuantity(){
        System.out.println("How many "+ userResponseToPreferredCoffee+" would you like to order?: ");
        quantity = userInputScanner.next();
        userInputScanner.nextLine();
        if(UserInputValidator.isInteger(quantity)){
            return true;
        }else {
            UserInputValidator.userInvalidResponse();
            return false;
        }
    }

    public void processOrder(String coffeeName, String coffeeQuantity){
        coffeeOrderFactory.createOrder(menuManager,coffeeName,coffeeQuantity);
        coffeeOrderFactory.orderDetails();
    }

    public void processAdditionalOrder(String addedCoffeeName, String addedQuantity){
        coffeeOrderFactory.addAdditionalOrder(menuManager,addedCoffeeName,addedQuantity);
        coffeeOrderFactory.orderDetails();
    }

    public void prepareCoffee(String coffeeName, String coffeeQuantity){
        System.out.println("-----preparing your coffee----");
        coffeeMaker.setCoffeeName(coffeeName);
        coffeeMaker.setQuantity(coffeeQuantity);
        Coffee coffee =  coffeeMaker.make();
    }

    public boolean handlePayment(){
        return paymentManager.makePayment(coffeeOrderFactory.getTotalPrice());
    }

    public boolean handleAdditionalPayment(){
        return paymentManager.makePayment(coffeeOrderFactory.getAdditionalAmount());
    }

    public boolean handleAddtionalOrder(){
        System.out.println(" would you like to order again? ");
        String userResponseToOrderMore = userInputScanner.next();
        userInputScanner.nextLine();
        if(!UserInputValidator.checkUserResponseToOrderMoreItem(userResponseToOrderMore)){
            return false;
        }
        displayMenu();
        System.out.println("Please place the next order: ");
        if(!getUserAdditionalCoffeeChoice()){
            return false;
        }
        System.out.println("How many of "+userResponseToAdditionalPreferredCoffee+" would you like to order? ");
        additionalQuantity = userInputScanner.next();
        userInputScanner.nextLine();
        if(!UserInputValidator.isInteger(additionalQuantity)){
            return false;
        }
        processAdditionalOrder(userResponseToAdditionalPreferredCoffee,additionalQuantity);
        prepareCoffee(userResponseToAdditionalPreferredCoffee,additionalQuantity);
        return handleAdditionalPayment();
    }

    public boolean handleCoffeeOrderProcess(){
        displayMenu();
        if(!getUserCoffeeChoice()){
            return false;
        }
        if(!getOrderQuantity()){
            return false;
        }
        processOrder(userResponseToPreferredCoffee,quantity);
        prepareCoffee(userResponseToPreferredCoffee,quantity);
        if(!handlePayment()){
            return false;
        }
        while (true) {
            boolean additionalOrderHandled = handleAddtionalOrder();
            if (!additionalOrderHandled) {
                break;
            }
        }
        System.out.println("Thank you for choosing us");
        return false;
    }

}