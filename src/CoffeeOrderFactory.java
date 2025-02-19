
public class CoffeeOrderFactory{
    Order order;
    private double orderPrice;
    private double additionalAmount;

    public CoffeeOrderFactory(){
        order = new Order();
        additionalAmount = 0.0;
        orderPrice = 0.0;
    }

    public void createOrder(MenuManager menuManager, String coffeeItem, String quantity) {
        if (menuManager.checkCoffee(coffeeItem)) {
            double price = menuManager.getPrice(coffeeItem);
            int orderQuantity = Integer.parseInt(quantity);
            order.addOrder(coffeeItem, orderQuantity, price);
            orderPrice += price * orderQuantity;
        } else {
            System.out.println("Item " + coffeeItem + " not found in the menu.");
        }
    }

    public void addAdditionalOrder(MenuManager menuManager, String coffeeItem, String quantity){
        additionalAmount = 0.0;
        if (menuManager.checkCoffee(coffeeItem)) {
            double price = menuManager.getPrice(coffeeItem);
            int orderQuantity = Integer.parseInt(quantity);
            order.addOrder(coffeeItem, orderQuantity, price);
            additionalAmount += price * orderQuantity;
        } else {
            System.out.println("Item " + coffeeItem + " not found in the menu.");
        }
    }

    public void orderDetails(){
        order.displayOrderDetails();
    }

    public  double getTotalPrice(){
        return orderPrice;
    }


    public double getAdditionalAmount() {
        return additionalAmount;
    }

}