import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

        /*Set<Subscription> subscriptionSet = new HashSet<>();
        subscriptionSet.add(netflix);
        subscriptionSet.add(netflixCopy);
        
        System.out.println("Number of subscriptions: " + subscriptionSet.size());
        System.out.println("Same object? " + (netflix == netflixCopy));
        System.out.println("Equal objects? " + netflix.equals(netflixCopy));
        System.out.println("Same hashCode? " + (netflix.hashCode() == netflixCopy.hashCode()));
        */

        Subscription spotify = new Subscription (
            2,
            "Spotify",
            10.99,
            BillingFrequency.MONTHLY,
            LocalDate.of(2026, 10, 1),
            SubscriptionStatus.ACTIVE,
            Category.MUSIC
        );

        /*
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
        */

        /*System.out.println(
            "Entertainment spending: $" +
            celia.calculateSpendingByCategory(Category.ENTERTAINMENT)
        );
        System.out.println(
            "Music spending: $" +
            celia.calculateSpendingByCategory(Category.MUSIC)
        );*/
        /*
        System.out.println("\nSpending by category: ");
        celia.displaySpendingByCategory();

        SubscriptionManager manager = new SubscriptionManager();
        manager.addSubscriber(celia);
        manager.displaySubscribers();

        Subscriber found = manager.findSubscriberById(1);
        System.out.println(found.getName());
        */ 

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
       /*
       Box<String> nameBox = new Box<>("Celia Joy");
       System.out.println(nameBox.getValue());

       Box<Subscription> subscriptionBox = new Box<>(netflix);
       System.out.println(subscriptionBox.getValue());    
       */
        /*
        try {
            Subscriber testSubscriber = new Subscriber(
                2,
                "Test User",
                //"Wrong-emai"
                "test@gmail.com"
            );
            System.out.println("Subscriber created successfully.");
        }

        catch(IllegalArgumentException e){
            System.out.println("Error creating subscriber: " + e.getMessage());
        }

        finally {
            System.out.println("Subscriber creation attempt finished.");
        }
        */

       /*
       
       try {
        checkPrice(-10);
       }
       catch(Exception e){
        System.out.println("Error: " + e.getMessage());
       }
       */
       /*
       try {
        checkSubscriptionPrice(-10);
       }
       catch (SubscriptionException e){
        System.out.println("SubSentry Error: " + e.getMessage());
       }
       */

       System.out.println("Total subscriptions created: " + Subscription.getTotalSubscriptions());
    }

    /*
    public static void checkPrice(double price) throws Exception {
        if (price < 0){
            throw new Exception("Price cannot be negative");
        }
        System.out.println("Price is valid.");
    }
    */
   /*

   public static void checkSubscriptionPrice(double price)
        throws SubscriptionException {
            if (price < 0 ){
                throw new SubscriptionException(
                    "Subscription price cannot be negative."
                );
            }
            System.out.println("Subscription price is valid.");
        }
        */

       /*
Instance = belongs to one object.
static = belongs to the class itself.
*/

}



