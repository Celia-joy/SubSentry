import java.time.LocalDate;

public class Subscription {
    private int id;
    private String name;
    private double price;
    private BillingFrequency billingFrequency;
    private LocalDate renewalDate;
    private SubscriptionStatus status;
    private Category category;



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
        this.billingFrequency = billingFrequency;
        this.status = status;
        this.category = category;

        if (price >= 0){
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (name != null && !name.isBlank()){
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if(renewalDate != null){
            this.renewalDate = renewalDate;
        }
        else {
            throw new IllegalArgumentException("Renewal date cannot be null");
        }
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name ){
        if(name != null && !name.isBlank()){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if(price >= 0){
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public BillingFrequency getBillingFrequency(){
        return billingFrequency;
    }

    public void setBillingFrequency(BillingFrequency billingFrequency){
        this.billingFrequency = billingFrequency;
    }

    public LocalDate getRenewalDate(){
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate){
        if(renewalDate != null){
            this.renewalDate = renewalDate;
        }
        else{
            throw new IllegalArgumentException("Renewal date cannot be null");
        }
    }

    public SubscriptionStatus getStatus(){
        return status;
    }

    public void setStatus(SubscriptionStatus status){
        this.status = status;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public double calculateMonthlyCost(){
        if(billingFrequency == BillingFrequency.MONTHLY){
            return price;
        }
        return price / 12 ;
    }

    public double calculateYearlyCost(){
        if (billingFrequency == BillingFrequency.YEARLY){
            return price * 12;
        }
        return price;
    }

    public boolean isActive(){
        return status == SubscriptionStatus.ACTIVE;
    }

    public void cancel(){
        this.status = SubscriptionStatus.CANCELLED;
    }

    public void pause(){
        this.status = SubscriptionStatus.PAUSED;
    }

    public void activate(){
        this.status = SubscriptionStatus.ACTIVE;
    }

}
