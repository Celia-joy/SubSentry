
package tests;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import model.Subscription;
import model.BillingFrequency;
import model.SubscriptionStatus;
import model.Category;

public class SubscriptionTests {

    private static int passed = 0;
    private static int failed = 0;

    private static Subscription createSubscription(
        int id,
        String name,
        double price,
        BillingFrequency frequency,
        LocalDate date,
        SubscriptionStatus status,
        Category category
    ) {
        return new Subscription(
            id, name, price, frequency, date, status, category
        );
    }

    private static void assertTrue(boolean condition, String name) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
        }
    }

    private static void assertThrows(
        Class<? extends Exception> expected,
        Runnable action,
        String name
    ) {
        try {
            action.run();
            failed++;
            System.out.println("FAIL: " + name);
        } catch (Exception actual) {
            if (expected.isInstance(actual)) {
                passed++;
                System.out.println("PASS: " + name);
            } else {
                failed++;
                System.out.println(
                    "FAIL: " + name + " - Wrong exception: " + actual
                );
            }
        }
    }

    public static void runTests() {
        passed = 0;
        failed = 0;

        System.out.println("\n========== SUBSCRIPTION TESTS ==========");

        LocalDate renewal = LocalDate.now().plusDays(10);

        Subscription netflix = createSubscription(
            1, "Netflix", 15.99,
            BillingFrequency.MONTHLY,
            renewal,
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        // Construction and getters
        assertTrue(
            netflix.getId() == 1 &&
            netflix.getName().equals("Netflix") &&
            netflix.getPrice() == 15.99 &&
            netflix.getBillingFrequency() == BillingFrequency.MONTHLY &&
            netflix.getRenewalDate().equals(renewal) &&
            netflix.getStatus() == SubscriptionStatus.ACTIVE &&
            netflix.getCategory() == Category.ENTERTAINMENT,
            "Valid subscription creation and getters"
        );

        // Constructor validation
        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(0, "Netflix", 15.99,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject zero ID");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(-1, "Netflix", 15.99,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject negative ID");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, null, 15.99,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject null name");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, " ", 15.99,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject blank name");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix", -10,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject negative price");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix",
                Double.POSITIVE_INFINITY,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject infinite price");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix",
                Double.NaN,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject NaN price");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix", 15.99,
                null, renewal,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject null billing frequency");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix", 15.99,
                BillingFrequency.MONTHLY, null,
                SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT),
            "Reject null renewal date");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix", 15.99,
                BillingFrequency.MONTHLY, renewal,
                null, Category.ENTERTAINMENT),
            "Reject null status");

        assertThrows(IllegalArgumentException.class,
            () -> createSubscription(2, "Netflix", 15.99,
                BillingFrequency.MONTHLY, renewal,
                SubscriptionStatus.ACTIVE, null),
            "Reject null category");

        // Setter validation
        netflix.setName("Netflix Premium");
        assertTrue(
            netflix.getName().equals("Netflix Premium"),
            "Update subscription name"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setName(null),
            "Reject null name in setter");

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setName(" "),
            "Reject blank name in setter");

        netflix.setPrice(20.99);
        assertTrue(
            netflix.getPrice() == 20.99,
            "Update subscription price"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setPrice(-5),
            "Reject negative price in setter");

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setPrice(Double.POSITIVE_INFINITY),
            "Reject infinite price in setter");

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setPrice(Double.NaN),
            "Reject NaN price in setter");

        netflix.setBillingFrequency(BillingFrequency.YEARLY);
        assertTrue(
            netflix.getBillingFrequency() == BillingFrequency.YEARLY,
            "Update billing frequency"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setBillingFrequency(null),
            "Reject null billing frequency in setter");

        LocalDate newDate = LocalDate.now().plusDays(20);
        netflix.setRenewalDate(newDate);

        assertTrue(
            netflix.getRenewalDate().equals(newDate),
            "Update renewal date"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setRenewalDate(null),
            "Reject null renewal date in setter");

        netflix.setStatus(SubscriptionStatus.PAUSED);
        assertTrue(
            netflix.getStatus() == SubscriptionStatus.PAUSED,
            "Update subscription status"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setStatus(null),
            "Reject null status in setter");

        netflix.setCategory(Category.SOFTWARE);
        assertTrue(
            netflix.getCategory() == Category.SOFTWARE,
            "Update subscription category"
        );

        assertThrows(IllegalArgumentException.class,
            () -> netflix.setCategory(null),
            "Reject null category in setter");

        // Cost calculations
        Subscription monthly = createSubscription(
            10, "Spotify", 10.0,
            BillingFrequency.MONTHLY, renewal,
            SubscriptionStatus.ACTIVE, Category.MUSIC
        );

        Subscription yearly = createSubscription(
            11, "YouTube", 120.0,
            BillingFrequency.YEARLY, renewal,
            SubscriptionStatus.ACTIVE, Category.ENTERTAINMENT
        );

        assertTrue(
            Math.abs(monthly.calculateMonthlyCost() - 10.0) < 0.001,
            "Calculate monthly cost for monthly subscription"
        );

        assertTrue(
            Math.abs(monthly.calculateYearlyCost() - 120.0) < 0.001,
            "Calculate yearly cost for monthly subscription"
        );

        assertTrue(
            Math.abs(yearly.calculateMonthlyCost() - 10.0) < 0.001,
            "Calculate monthly cost for yearly subscription"
        );

        assertTrue(
            Math.abs(yearly.calculateYearlyCost() - 120.0) < 0.001,
            "Calculate yearly cost for yearly subscription"
        );

        // Status operations
        assertTrue(monthly.isActive(), "Active subscription check");

        monthly.pause();
        assertTrue(
            monthly.getStatus() == SubscriptionStatus.PAUSED &&
            !monthly.isActive(),
            "Pause subscription"
        );

        monthly.activate();
        assertTrue(monthly.isActive(), "Reactivate subscription");

        monthly.cancel();
        assertTrue(
            monthly.getStatus() == SubscriptionStatus.CANCELLED &&
            !monthly.isActive(),
            "Cancel subscription"
        );

        // Renewal
        assertTrue(
            monthly.daysUntilRenewal() == 10,
            "Calculate days until renewal"
        );

        // Equality and hash code
        Subscription copy = createSubscription(
            10, "Another Name", 50.0,
            BillingFrequency.YEARLY, renewal,
            SubscriptionStatus.ACTIVE, Category.OTHER
        );

        assertTrue(monthly.equals(copy), "Subscriptions with same ID are equal");

        assertTrue(
            monthly.hashCode() == copy.hashCode(),
            "Equal subscriptions have same hash code"
        );

        assertTrue(
            !monthly.equals(yearly),
            "Subscriptions with different IDs are not equal"
        );

        assertTrue(
            !monthly.equals(null),
            "Subscription is not equal to null"
        );

        assertTrue(
            monthly.equals(monthly),
            "Subscription equals itself"
        );

        Set<Subscription> subscriptionSet = new HashSet<>();
        subscriptionSet.add(monthly);
        subscriptionSet.add(copy);

        assertTrue(
            subscriptionSet.size() == 1,
            "HashSet prevents duplicate equal subscriptions"
        );

        // String representation
        assertTrue(
            monthly.toString().contains("Spotify"),
            "Subscription toString"
        );

        System.out.println(
            "\nSubscription results: " + passed +
            " passed, " + failed + " failed."
        );
    }
    public static int getPassed() {
        return passed;
    }

    public static int getFailed() {
        return failed;
    }
}
