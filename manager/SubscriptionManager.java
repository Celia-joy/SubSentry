package manager;

import java.util.HashMap;
import model.Subscriber;

public class SubscriptionManager{
    private HashMap<Integer, Subscriber>subscribers;

    public SubscriptionManager(){
        subscribers = new HashMap<>();
    }

    public void addSubscriber(Subscriber subscriber){
        if(subscriber == null){
            throw new IllegalArgumentException("Subscriber cannot be null");
        }

        if(subscribers.containsKey(subscriber.getId())){
            throw new IllegalArgumentException("Subscriber ID already exists");
        }
        subscribers.put(subscriber.getId(), subscriber);
    }

    public void displaySubscribers(){
        if (subscribers.isEmpty()){
            System.out.println("No subscribers registered.");
            return;
        }
        for(Subscriber subscriber : subscribers.values()){
            System.out.println(
                "ID: " + subscriber.getId() +
                ", Name: " + subscriber.getName() +
                ", Email: " + subscriber.getEmail()
            );
        }
    }
    public Subscriber findSubscriberById(int id){
        if (id <= 0){
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        return subscribers.get(id);
    }
}