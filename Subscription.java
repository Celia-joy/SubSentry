import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Subscription {
    private int id;
    private String name;
    private double price;
    private BillingFrequency billingFrequency;
    private LocalDate renewalDate;
    private SubscriptionStatus status;
    private Category category;
    private static int totalSubscriptions = 0;



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
        totalSubscriptions++;
    }

    public static int getTotalSubscriptions(){
        return totalSubscriptions;
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
            return price;
        }
        return price * 12;
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

    @Override
    public String toString() {
        return "Subscription{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", billingFrequency=" + billingFrequency +
            ", renewalDate=" + renewalDate +
            ", status=" + status +
            ", category=" + category +
            '}';
    }
    public long daysUntilRenewal(){
        return ChronoUnit.DAYS.between(
            LocalDate.now(),
            renewalDate
        );
    }

    public void displayRenewalInfo(){
        long days = daysUntilRenewal();

        if(days <= 0 ){
            System.out.println(name + "renewal is due today or has passed");
        }
        else if (days <= 7) {
            System.out.println("Warning " + name + " renews in " + days + " days!");
        }
        else {
            System.out.println(name + " renews in " + days + "days!");
        }
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(!(obj instanceof Subscription)){
            return false;
        }

        Subscription other = (Subscription) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }
}
