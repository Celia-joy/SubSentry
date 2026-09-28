import java.util.ArrayList;

public class SubscriptionManager{
    private ArrayList<Subscription>subscriptions;

    public SubscriptionManager(){
        subscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void displaySubscriptions(){
        for(Subscription subscription : subscriptions){
            System.out.println(subscription);
        }
    }

    public Subscription findSubscriptionById(int id){
        for(Subscription subscription : subscriptions){
            if(subscription.getId() == id){
                return subscription;
            }
        }
        return null;
    }
}