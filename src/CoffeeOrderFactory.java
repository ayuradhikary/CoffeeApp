
public class CoffeeOrderFactory{
    Order order;
    private double orderPrice;
    private double additionalAmount;
    String orderId;

    public CoffeeOrderFactory(){
        order = new Order();
        additionalAmount = 0.0;
        orderPrice = 0.0;
        orderId = order.getOrderId();
    }

    public void createOrder(MenuManager menuManager, String coffeeItem, String quantity) {
            double price = menuManager.getPrice(coffeeItem);
            int orderQuantity = Integer.parseInt(quantity);
            order.addOrder(coffeeItem, orderQuantity, price);
            orderPrice += price * orderQuantity;
    }

    public void addAdditionalOrder(MenuManager menuManager, String coffeeItem, String quantity){
            additionalAmount = 0.0;
            double price = menuManager.getPrice(coffeeItem);
            int orderQuantity = Integer.parseInt(quantity);
            order.addOrder(coffeeItem, orderQuantity, price);
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

    public String getOrderId(){
        return orderId;
    }

}