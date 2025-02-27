
public class CoffeeOrderProcess {
    MenuManager menuManager;
    CoffeeMaker coffeeMaker;
    PaymentManager paymentManager;
    CoffeeOrderFactory coffeeOrderFactory;
    String userResponseToPreferredCoffee;
    String quantity;
    String userResponseToAdditionalPreferredCoffee;
    String additionalQuantity;
    CustomerOrderDetailsDTO customerOrderDetailsDTO;
    String orderId;
    ConcreteDatabase coffeeAppDatabase;

    public CoffeeOrderProcess(MenuManager menuManager, CoffeeMaker coffeeMaker, PaymentManager paymentManager,CoffeeOrderFactory coffeeOrderFactory,CustomerOrderDetailsDTO customerOrderDetailsDTO){
        this.menuManager = menuManager;
        this.coffeeMaker = coffeeMaker;
        this.paymentManager = paymentManager;
        this.coffeeOrderFactory = coffeeOrderFactory;
        this.customerOrderDetailsDTO = customerOrderDetailsDTO;
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
        orderId = coffeeOrderFactory.getOrderId();
        double price = coffeeOrderFactory.getTotalPrice();
        customerOrderDetailsDTO.setOrderId(orderId);
        customerOrderDetailsDTO.setCoffeeName(coffeeName);
        customerOrderDetailsDTO.setQuantity(coffeeQuantity);
        customerOrderDetailsDTO.setPrice(price);
        coffeeOrderFactory.orderDetails();
    }

    public void processAdditionalOrder(String addedCoffeeName, String addedQuantity){
        coffeeOrderFactory.addAdditionalOrder(menuManager,addedCoffeeName,addedQuantity);
        customerOrderDetailsDTO.setadditionalCoffeeName(addedCoffeeName);
        customerOrderDetailsDTO.setAdditionalQuantity(addedQuantity);
        double additionalPrice = coffeeOrderFactory.getAdditionalAmount();
        customerOrderDetailsDTO.setAdditionalPrice(additionalPrice);
        coffeeOrderFactory.orderDetails();
    }

    public void prepareCoffee(String coffeeName, String coffeeQuantity){
        Utility.logMessagePrompt("-----preparing your coffee----");
        coffeeMaker.setCoffeeName(coffeeName);
        coffeeMaker.setQuantity(coffeeQuantity);
        Coffee coffee =  coffeeMaker.make();
    }

    public boolean handlePayment(){
        return paymentManager.makePayment(coffeeOrderFactory.getTotalPrice(),customerOrderDetailsDTO);
    }

    public boolean handleAdditionalPayment(){
        return paymentManager.makePayment(coffeeOrderFactory.getAdditionalAmount(), customerOrderDetailsDTO);
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
        coffeeAppDatabase = DBConnector.connectToDb("CoffeeAppDatase");
        Table customer = DBConnector.connectToTable("Customer");
        String[] customerColumns = {"custId","payment_method","orderId"};
        customer.addColumnsAndInsert("Customer",customerColumns,customerOrderDetailsDTO.custId,customerOrderDetailsDTO.paymentMethod,customerOrderDetailsDTO.orderId);
        Table order = DBConnector.connectToTable("Order");
        String[] orderColumns = {"orderId","coffee_name","coffee_price","quantity","added_coffee_name","additional_price","added_coffee_quantity"};
        order.addColumnsAndInsert("Order",orderColumns,customerOrderDetailsDTO.orderId,
                customerOrderDetailsDTO.coffeeName,customerOrderDetailsDTO.price,customerOrderDetailsDTO.quantity,
                customerOrderDetailsDTO.additionalCoffeeName,customerOrderDetailsDTO.additionalPrice,
                customerOrderDetailsDTO.additionalQuantity);
        Utility.logMessagePrompt("Thank you for choosing us");
        return false;
    }

}