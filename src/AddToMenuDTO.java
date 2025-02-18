public class AddToMenuDTO {
    private String coffeeName;
    private double coffeePrice;

    public AddToMenuDTO(String coffeeName, double coffeePrice){
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }
}
