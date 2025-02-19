import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < coffeeItem.size(); i++) {
            System.out.println(coffeeItem.get(i) + " x" + quantities.get(i) + " - Rs." + prices.get(i) + " each");
        }
        System.out.println("Total: Rs." + totalPrice);
    }
}
