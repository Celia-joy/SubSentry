
import java.time.LocalDate;

import model.Subscription;
import model.Subscriber;
import model.BillingFrequency;
import model.SubscriptionStatus;
import model.Category;

import manager.SubscriptionManager;

public class Main {

    public static void main(String[] args) {

        // Create the subscription manager
        SubscriptionManager manager = new SubscriptionManager();

        // Create a subscriber
        Subscriber subscriber = new Subscriber(
            1,
            "Forever Hyacinthe",
            "forever@example.com"
        );

        // Create subscriptions
        Subscription netflix = new Subscription(
            101,
            "Netflix",
            15.99,
            BillingFrequency.MONTHLY,
            LocalDate.now().plusDays(10),
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        Subscription spotify = new Subscription(
            102,
            "Spotify",
            10.99,
            BillingFrequency.MONTHLY,
            LocalDate.now().plusDays(15),
            SubscriptionStatus.ACTIVE,
            Category.MUSIC
        );

        Subscription youtube = new Subscription(
            103,
            "YouTube",
            120.00,
            BillingFrequency.YEARLY,
            LocalDate.now().plusDays(30),
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        // Add subscriptions to the subscriber
        subscriber.addSubscription(netflix);
        subscriber.addSubscription(spotify);
        subscriber.addSubscription(youtube);

        // Register subscriber with the manager
        manager.addSubscriber(subscriber);

        // Display application information
        System.out.println("========== SUBSENTRY ==========");

        System.out.println("\n========== SUBSCRIBERS ==========");
        manager.displaySubscribers();

        System.out.println("\n========== SUBSCRIPTIONS ==========");
        subscriber.displaySubscriptions();

        System.out.println("\n========== SPENDING ==========");

        System.out.println(
            "Monthly spending: $" +
            subscriber.calculateMonthlySpending()
        );

        System.out.println(
            "Yearly spending: $" +
            subscriber.calculateYearlySpending()
        );

        System.out.println("\n========== SPENDING BY CATEGORY ==========");
        subscriber.displaySpendingByCategory();

        System.out.println("\n========== END OF SUBSENTRY ==========");
    }
}
