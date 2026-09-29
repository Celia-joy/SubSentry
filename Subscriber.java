import java.util.ArrayList;
public class Subscriber{
    private int id;
    private String name;
    private String email;
    private ArrayList<Subscription> subscriptions;

    public Subscriber(int id, String name, String email){
        if(id > 0 ){
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        if(name != null && !name.isBlank()){
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(email != null && !email.isBlank() && email.contains("@")){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Invalid email");
        }
        this.subscriptions = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name != null && !name.isBlank()){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        if(email != null && !email.isBlank() && email.contains("@")){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void addSubscription(Subscription subscription){
        if(subscription != null){
            subscriptions.add(subscription);
        }
        else{
            throw new IllegalArgumentException("Subscription cannot be null");
        }
    }
    public void removeSubscription(Subscription subscription){
        if(subscription != null){
            subscriptions.remove(subscription);
        }
        else{
            throw new IllegalArgumentException("Subscription cannot be null");
        }     
    }
    public Subscription findSubscriptionById(int id){
        for (Subscription subscription : subscriptions){
            if (subscription.getId() == id){
                return subscription;
            }
        }
        return null;
    }

    public void displaySubscriptions(){
        for (Subscription subscription : subscriptions){
            System.out.println(subscription);
        }
    }

    public double calculateMonthlySpending(){
        double total = 0;
        for (Subscription subscription : subscriptions){
            if(subscription.isActive()){
                total += subscription.calculateMonthlyCost();
            }
        }
        return total;
    }

    public double calculateYearlySpending(){
        double total = 0;
        for (Subscription subscription : subscriptions){
            if(subscription.isActive()){
                total += subscription.calculateYearlyCost();
            }
        }
        return total;
    }

    public double calculateSpendingByCategory(Category category){
        double total = 0;

        for(Subscription subscription : subscriptions){
            if(subscription.isActive()
            && subscription.getCategory()== category){
                total += subscription.calculateMonthlyCost();
            }
        }
        return total;
    }

    public void displaySpendingByCategory(){
        for (Category category : Category.values()){
            double spending = calculateSpendingByCategory(category);

            if(spending > 0){
                System.out.println(category + ": $" + spending + " per month");
            }
        }
    }

}

