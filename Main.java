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

        Subscription netflixCopy = new Subscription (
            1,
            "Netflix",
            15.99,
            BillingFrequency.MONTHLY,
            LocalDate.of(2026, 10, 1),
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        System.out.println("Same object? " + (netflix == netflixCopy));
        System.out.println("Equal objects? " + netflix.equals(netflixCopy));
        System.out.println("Same hashCode? " + (netflix.hashCode() == netflixCopy.hashCode()));

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
        System.out.println("Days until renewal: " + netflix.daysUntilRenewal());
        netflix.displayRenewalInfo();
        spotify.displayRenewalInfo();
        /*System.out.println(
            "Entertainment spending: $" +
            celia.calculateSpendingByCategory(Category.ENTERTAINMENT)
        );
        System.out.println(
            "Music spending: $" +
            celia.calculateSpendingByCategory(Category.MUSIC)
        );*/
        System.out.println("\nSpending by category: ");
        celia.displaySpendingByCategory();

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