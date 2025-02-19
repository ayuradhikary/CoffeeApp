public class Coffee {
    String coffeeName;
    String quantity;
    CoffeeOrderFactory coffeeOrderFactory;

    public Coffee(CoffeeMaker coffeeMaker){
        this.coffeeOrderFactory = coffeeMaker.getCoffeeOrderFactory();
        this.coffeeName = coffeeMaker.getCoffeeName();
        this.quantity = coffeeMaker.getQuantity();
    }

    public void makeCoffee(){
        System.out.println("your "+coffeeName+" has been prepared, for quantity: "+quantity);
        System.out.println("Thank you for trusting us");
    }
}
