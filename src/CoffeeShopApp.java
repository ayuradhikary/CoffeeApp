
public class CoffeeShopApp {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        CoffeeOrderFactory coffeeOrderFactory = new CoffeeOrderFactory();
        PaymentManager paymentManager = new PaymentManager();
        CoffeeMaker coffeeMaker = new CoffeeMaker(coffeeOrderFactory);
        CoffeeOrderProcess coffeeOrderProcess = new CoffeeOrderProcess(menuManager,coffeeMaker,paymentManager,coffeeOrderFactory);

        Utility.logMessagePrompt("--------Welcome------");
        Utility.logMessagePrompt("--------Please enter 'Q' to exit--------");

        boolean running = true;
        while (running){
            running = coffeeOrderProcess.handleCoffeeOrderProcess();
        }
    }
}
