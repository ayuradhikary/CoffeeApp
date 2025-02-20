public class CoffeeMaker {
    private String coffeeName;
    private String quantity;
    private CoffeeOrderFactory coffeeOrderFactory;

    public CoffeeMaker(CoffeeOrderFactory coffeeOrderFactory){
        this.coffeeOrderFactory = coffeeOrderFactory;
    }

    public CoffeeMaker setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
        return this;
    }

    public CoffeeMaker setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public CoffeeMaker setCoffeeOrderFactory(CoffeeOrderFactory coffeeOrderFactory) {
        this.coffeeOrderFactory = coffeeOrderFactory;
        return this;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public String getQuantity() {
        return quantity;
    }

    public CoffeeOrderFactory getCoffeeOrderFactory() {
        return coffeeOrderFactory;
    }

    public Coffee make() {
        System.out.println("your "+coffeeName+" has been prepared, for quantity: "+quantity);
        System.out.println("Thank you for trusting us");
        return new Coffee(this);
    }

}
