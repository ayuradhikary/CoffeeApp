
public class CoffeeOrderFactory{
    Order order;

    public CoffeeOrderFactory(){
        order = new Order();
    }

    public void createOrder(MenuManager menuManager, String coffeeItem, String quantity) {
        if (menuManager.checkCoffee(coffeeItem)) {
            double price = menuManager.getPrice(coffeeItem);
            int orderQuantity = Integer.parseInt(quantity);
            order.addOrder(coffeeItem, orderQuantity, price);
        } else {
            System.out.println("Item " + coffeeItem + " not found in the menu.");
        }
    }

    public void orderDetails(){
        order.displayOrderDetails();
    }

    public  double getTotalPrice(){
        return order.getTotalPrice();
    }

}