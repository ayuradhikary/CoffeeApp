
public class CoffeeOrderProcess {
    MenuManager menuManager;
    CoffeeMaker coffeeMaker;
    PaymentManager paymentManager;
    CoffeeOrderFactory coffeeOrderFactory;
    String userResponseToPreferredCoffee;
    String quantity;
    String userResponseToAdditionalPreferredCoffee;
    String additionalQuantity;

    public CoffeeOrderProcess(MenuManager menuManager, CoffeeMaker coffeeMaker, PaymentManager paymentManager,CoffeeOrderFactory coffeeOrderFactory){
        this.menuManager = menuManager;
        this.coffeeMaker = coffeeMaker;
        this.paymentManager = paymentManager;
        this.coffeeOrderFactory = coffeeOrderFactory;

    }

    public void displayMenu(){
        menuManager.displayMenu();
    }

    public boolean getUserCoffeeChoice(){
        while (true){
            Utility.logMessagePrompt("please enter your coffee choice: ");
            userResponseToPreferredCoffee = Utility.getStringInput();
            Boolean userResponseChecked = UserInputValidator.checkUserResponseToPreferredCoffee(userResponseToPreferredCoffee, menuManager);
            if(userResponseChecked == null){
                UserInputValidator.userInvalidResponse();
            } else {
                return userResponseChecked;
            }
        }
    }

    public boolean getUserAdditionalCoffeeChoice(){
        while (true){
            Utility.logMessagePrompt("please enter your coffee choice: ");
            userResponseToAdditionalPreferredCoffee = Utility.getStringInput();
            Boolean userResponseChecked = UserInputValidator.checkUserResponseToPreferredCoffee(userResponseToAdditionalPreferredCoffee, menuManager);
            if(userResponseChecked == null){
                UserInputValidator.userInvalidResponse();
            } else {
                return userResponseChecked;
            }
        }
    }

    public boolean getOrderQuantity(){
        Utility.logMessageWithArgument("How many %s would you like to order?: ", userResponseToPreferredCoffee);
        quantity = Utility.getStringInput();
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
        Utility.logMessagePrompt("-----preparing your coffee----");
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
        Utility.logMessagePrompt(" would you like to order again? ");
        String userResponseToOrderMore = Utility.getStringInput();
        if(!UserInputValidator.checkUserResponseToOrderMoreItem(userResponseToOrderMore)){
            return false;
        }
        displayMenu();
        Utility.logMessagePrompt("Please place the next order: ");
        if(!getUserAdditionalCoffeeChoice()){
            return false;
        }
        Utility.logMessageWithArgument("How many of %s would you like to order? ",userResponseToAdditionalPreferredCoffee);
        additionalQuantity = Utility.getStringInput();
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
        Utility.logMessagePrompt("Thank you for choosing us");
        return false;
    }

}