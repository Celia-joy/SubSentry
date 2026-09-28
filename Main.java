import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        Subscription netflix = new Subscription (
            1,
            "Netflix",
            15.99,
            BillingFrequency.MONTHLY,
            LocalDate.of(2026, 10, 1),
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        Subscription spotify = new Subscription (
            2,
            "Spotify",
            10.99,
            BillingFrequency.MONTHLY,
            LocalDate.of(2026, 10, 1),
            SubscriptionStatus.ACTIVE,
            Category.MUSIC
        );

        Subscriber celia = new Subscriber(
            1,
            "Celia Joy",
            "joyihirwecelia@gmail.com"
        );

        celia.addSubscription(netflix);
        celia.addSubscription(spotify);
        celia.displaySubscriptions();
        System.out.println("Monthly spending: $" + celia.calculateMonthlySpending());
        System.out.println("Yearly spending: $" + celia.calculateYearlySpending());

        SubscriptionManager manager = new SubscriptionManager();
        manager.addSubscriber(celia);
        manager.displaySubscribers();

        Subscriber found = manager.findSubscriberById(1);
        System.out.println(found.getName());

        /*
        System.out.println("ID: " + netflix.getId());
        System.out.println("Name: " + netflix.getName());
        System.out.println("Price: $" + netflix.getPrice());
        System.out.println("Billing Frequency: " + netflix.getBillingFrequency());
        System.out.println("Renewal Date: " + netflix.getRenewalDate());
        System.out.println("Status: " + netflix.getStatus());
        System.out.println("Category: " + netflix.getCategory());
        System.out.println("Monthly cost: $" + netflix.calculateMonthlyCost());
        System.out.println("Yearly cost: $" + netflix.calculateYearlyCost());
        System.out.println("Is active? " + netflix.isActive());

        netflix.cancel();

        System.out.println("Status after cancellation: " + netflix.getStatus());
        */

        /*
        System.out.println(netflix);
        netflix.pause();
        System.out.println(netflix);

        netflix.activate();
        System.out.println(netflix);
        */      
    }
}