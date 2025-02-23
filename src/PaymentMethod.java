public enum PaymentMethod {
    CASH("Cash payment"),
    CREDIT_CARD("Credit card payment");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
