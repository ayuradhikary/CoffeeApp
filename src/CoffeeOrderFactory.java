
public class CoffeeOrderFactory{
    Order order;

    public CoffeeOrderFactory(){
        order = new Order();
    }

    public void createOrder(MenuManager menuManager, String coffeeItem, int quantity) {
        if (menuManager.checkCoffee(coffeeItem)) {
            double price = menuManager.getPrice(coffeeItem);
            order.addOrder(coffeeItem, quantity, price);
        } else {
            System.out.println("Item " + coffeeItem + " not found in the menu.");
        }
    }

    public void orderDetails(){
        order.displayOrderDetails();
    }

}