import java.util.UUID;

public class Customer {
    String custId;
    PaymentMethod paymentMethod;
    String orderId;

    public Customer(PaymentMethod paymentMethod, String custId){
        this.paymentMethod = paymentMethod;
        this.custId = custId;
    }

    public String getCustId(){
        return custId;
    }

    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }

    public String getOrderId(){
        return orderId;
    }

}
