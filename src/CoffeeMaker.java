public class CoffeeMaker {
    private String coffeeName = "";
    private String quantity = "";
    private CoffeeOrderFactory coffeeOrderFactory;

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
        if (coffeeOrderFactory == null) {
            throw new IllegalStateException("CoffeeOrderFactory cannot be null");
        }
        return new Coffee(this);
    }

}
