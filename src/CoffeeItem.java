public class CoffeeItem {
    private String coffeeItem;
    private int quantity;
    private double price;

    public CoffeeItem(String coffeeItem, int quantity, double price) {
        this.coffeeItem = coffeeItem;
        this.quantity = quantity;
        this.price = price;
    }

    public String getcoffeeItem() {
        return coffeeItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
