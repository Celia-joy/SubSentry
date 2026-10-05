package manager;

import java.util.HashMap;
import model.Subscriber;

public class SubscriptionManager{
    private HashMap<Integer, Subscriber>subscribers;

    public SubscriptionManager(){
        subscribers = new HashMap<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.put(subscriber.getId(), subscriber);
    }

    public void displaySubscribers(){
        for(Subscriber subscriber : subscribers.values()){
            System.out.println(
                "ID: " + subscriber.getId() +
                ", Name: " + subscriber.getName() +
                ", Email: " + subscriber.getEmail()
            );
        }
    }
    public Subscriber findSubscriberById(int id){
        return subscribers.get(id);
    }
}