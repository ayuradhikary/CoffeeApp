import java.util.UUID;

public class CustomerFactory {
    String custId;
    PaymentMethod paymentMethod;
    Customer customer;

    public CustomerFactory(PaymentMethod paymentMethod){
        this.custId = generateCustId();
        this.paymentMethod = paymentMethod;
        makeCustomer();
    }

    public String generateCustId() {
        return UUID.randomUUID().toString();
    }

    private void makeCustomer(){
        customer = new Customer(paymentMethod, custId);
    }

    public Customer getCustomer(){
        return customer;
    }

}
