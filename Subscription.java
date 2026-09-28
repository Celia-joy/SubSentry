import java.time.LocalDate;

public class Subscription {
    private int id;
    private String name;
    private double price;
    private BillingFrequency billingFrequency;
    private LocalDate renewalDate;
    private SubscriptionStatus status;
    private Category category;

}

public Subscription (
    int id,
    String name,
    double price,
    BillingFrequency billingFrequency,
    LocalDate renewalDate,
    SubscriptionStatus status,
    Category category
){
    this.id = id;
    this.name = name;
    this.price = price;
    this.billingFrequency = billingFrequency;
    this.renewalDate = renewalDate;
    this.status = status;
    this.category = category;
}