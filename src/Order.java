import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<String, CoffeeItem> orderCoffeeItems;
    private double totalPrice;

    public Order() {
        this.orderCoffeeItems = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public void addOrder(String coffeeItem, int quantity, double price) {
        if (orderCoffeeItems.containsKey(coffeeItem)) {
            CoffeeItem existingItem = orderCoffeeItems.get(coffeeItem);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            totalPrice += price * quantity;
        } else {
            CoffeeItem newcoffeeItem = new CoffeeItem(coffeeItem, quantity, price);
            orderCoffeeItems.put(coffeeItem, newcoffeeItem);
            totalPrice += newcoffeeItem.getTotalPrice();
        }
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void displayOrderDetails() {
        System.out.println("Here is the description of your order: ");
        for (CoffeeItem item : orderCoffeeItems.values()) {
            System.out.println(item.getcoffeeItem() + " x" + item.getQuantity() + " - Rs." + item.getPrice() + " each");
        }
        System.out.println("Total: Rs." + totalPrice);
    }
}