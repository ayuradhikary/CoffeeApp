public class Coffee {
    String coffeeName;
    String quantity;
    CoffeeOrderFactory coffeeOrderFactory;

    public Coffee(CoffeeMaker coffeeMaker){
        this.coffeeOrderFactory = coffeeMaker.getCoffeeOrderFactory();
        this.coffeeName = coffeeMaker.getCoffeeName();
        this.quantity = coffeeMaker.getQuantity();
    }
}
