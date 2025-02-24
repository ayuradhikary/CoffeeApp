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
        Utility.logMessagePrompt("Here is the description of your order: ");
        for (CoffeeItem item : orderCoffeeItems.values()) {
            Utility.logMessageWithArgument("%s x %d - Rs.%f each",
                    item.getcoffeeItem(), item.getQuantity(), item.getPrice());
        }
        Utility.logMessageWithArgument("Total: Rs.%f", totalPrice);

    }
}