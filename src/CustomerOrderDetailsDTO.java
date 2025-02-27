import java.util.ArrayList;

public class CustomerOrderDetailsDTO {
    String orderId;
    String custId;
    PaymentMethod paymentMethod;
    String coffeeName;
    String quantity;
    String additionalCoffeeName;
    String additionalQuantity;
    double price;
    double additionalPrice;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setAdditionalQuantity(String additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public void setadditionalCoffeeName(String additionalCoffeeName){
        this.additionalCoffeeName = additionalCoffeeName;
    }
}
