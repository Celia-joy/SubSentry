import java.util.ArrayList;

public class SubscriptionManager{
    private ArrayList<Subscriber>subscribers;

    public SubscriptionManager(){
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void displaySubscribers(){
        for(Subscriber subscriber : subscribers){
            System.out.println(
                "ID: " + subscriber.getId() +
                ", Name: " + subscriber.getName() +
                ", Email: " + subscriber.getEmail()
            );
        }
    }

    public Subscriber findSubscriberById(int id){
        for(Subscriber subscriber : subscribers){
            if(subscriber.getId() == id){
                return subscriber;
            }
        }
        return null;
    }
}