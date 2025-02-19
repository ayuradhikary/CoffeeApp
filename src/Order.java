import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Order {
    private List<String> coffeeItem;
    private List<Integer> quantities;
    private List<Double> prices;
    private double totalPrice;

    public Order(){
        this.coffeeItem = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addOrder(String item, int quantity, double price) {
        coffeeItem.add(item);
        quantities.add(quantity);
        prices.add(price);
        totalPrice += price * quantity;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void displayOrderDetails() {
        double totalPrice = IntStream.range(0, coffeeItem.size())
                .mapToDouble(i -> {
                    System.out.println("Here is the description of your order: ");
                    System.out.println(coffeeItem.get(i) + " x" + quantities.get(i) + " - Rs." + prices.get(i) + " each");
                    return quantities.get(i) * prices.get(i);
                })
                .sum();
        System.out.println("Total: Rs." + totalPrice);
    }
}
