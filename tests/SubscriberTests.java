
package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.time.LocalDate;

import model.Subscriber;
import model.Subscription;
import model.BillingFrequency;
import model.SubscriptionStatus;
import model.Category;

public class SubscriberTests {

    private static int passed = 0;
    private static int failed = 0;

    private static Subscription createSubscription(
        int id,
        String name,
        double price,
        BillingFrequency frequency,
        SubscriptionStatus status,
        Category category
    ) {
        return new Subscription(
            id, name, price, frequency,
            LocalDate.now().plusDays(10), status, category
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

    private static void assertOutput(
        Runnable action,
        String expectedOutput,
        String name
    ) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        if (output.toString().contains(expectedOutput)) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + output);
        }
    }

    public static void runTests() {
        passed = 0;
        failed = 0;

        System.out.println("\n========== SUBSCRIBER TESTS ==========");

        // Constructor and getters
        Subscriber subscriber = new Subscriber(
            1, "Celia Joy", "celia@example.com"
        );

        assertTrue(
            subscriber.getId() == 1 &&
            subscriber.getName().equals("Celia Joy") &&
            subscriber.getEmail().equals("celia@example.com"),
            "Valid subscriber creation and getters"
        );

        // Constructor validation
        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(0, "Celia", "celia@example.com"),
            "Reject zero subscriber ID");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(-1, "Celia", "celia@example.com"),
            "Reject negative subscriber ID");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(2, null, "celia@example.com"),
            "Reject null subscriber name");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(2, " ", "celia@example.com"),
            "Reject blank subscriber name");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(2, "Celia", null),
            "Reject null email");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(2, "Celia", " "),
            "Reject blank email");

        assertThrows(IllegalArgumentException.class,
            () -> new Subscriber(2, "Celia", "wrong-email"),
            "Reject email without @");

        // Setters
        subscriber.setName("Celia Joy Updated");

        assertTrue(
            subscriber.getName().equals("Celia Joy Updated"),
            "Update subscriber name"
        );

        subscriber.updateSubscriptionBillingFrequency(
            1, 
            BillingFrequency.YEARLY
        );

        assertTrue(
            netflix.getBillingFrequency() == BillingFrequency.YEARLY,
            "Update subscription billing frequency"
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionBillingFrequency(
                999, BillingFrequency.MONTHLY
            ),
            "Reject billing frequency update for nonexistent subscription"
        );

        asssertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionBillingFrequency(1, null),
                1, BillingFrequency.MONTHLY
            "Reject billing frequency update for subscription with different billing frequency"
        );

        subscriber.updateSubscriptionBillingFrequency(
            1,
            BillingFrequency.MONTHLY
        );

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.setName(null),
            "Reject null name in setter");

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.setName(" "),
            "Reject blank name in setter");

        subscriber.setEmail("new@example.com");

        assertTrue(
            subscriber.getEmail().equals("new@example.com"),
            "Update subscriber email"
        );

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.setEmail(null),
            "Reject null email in setter");

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.setEmail("invalid-email"),
            "Reject invalid email in setter");

        // Subscription management
        Subscription netflix = createSubscription(
            1, "Netflix", 15.99,
            BillingFrequency.MONTHLY,
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        Subscription spotify = createSubscription(
            2, "Spotify", 10.99,
            BillingFrequency.MONTHLY,
            SubscriptionStatus.ACTIVE,
            Category.MUSIC
        );

        Subscription youtube = createSubscription(
            3, "YouTube", 120.0,
            BillingFrequency.YEARLY,
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.addSubscription(null),
            "Reject null subscription");

        subscriber.addSubscription(netflix);
        subscriber.addSubscription(spotify);
        subscriber.addSubscription(youtube);

        assertTrue(
            subscriber.findSubscriptionById(1) == netflix,
            "Find subscription by ID"
        );

        assertTrue(
            subscriber.findSubscriptionById(999) == null,
            "Return null for missing subscription"
        );

        subscriber.updateSubscriptionPrice(1, 20.0);

        assertTrue(
            Math.abs(netflix.getPrice() - 20.0) < 0.001,
            "Update subscription price"
        );

        subscriber.updateSubscriptionPrice(1, 15.99);

        assertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionPrice(999, 20.0),
            "Reject price update for nonexistent subscription"
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionPrice(1, -20.0),
            "Reject negative price during update"
        );

        subscriber.updateSubscriptionName(1, "Netflix Premium");

        assertTrue(
            netflix.getName().equals("Netflix Premium"),
            "Update subscription name through subscriber"
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionName(999, "Unknown"),
            "Reject name update for nonexistent subscription"
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> subscriber.updateSubscriptionName(1, " "),
            "Reject blank subscription name during update"
        );

        subscriber.updateSubscriptionName(1, "Netflix");

        Subscription duplicate = createSubscription(
            1, "Duplicate Netflix", 20.0,
            BillingFrequency.MONTHLY,
            SubscriptionStatus.ACTIVE,
            Category.ENTERTAINMENT
        );

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.addSubscription(duplicate),
            "Reject duplicate subscription ID"
        );

        // Spending calculations
        assertTrue(
            Math.abs(subscriber.calculateMonthlySpending() - 36.98) < 0.001,
            "Calculate monthly spending"
        );

        assertTrue(
            Math.abs(subscriber.calculateYearlySpending() - 443.76) < 0.001,
            "Calculate yearly spending"
        );

        assertTrue(
            Math.abs(
                subscriber.calculateSpendingByCategory(
                    Category.ENTERTAINMENT
                ) - 25.99
            ) < 0.001,
            "Calculate entertainment spending"
        );

        assertTrue(
            Math.abs(
                subscriber.calculateSpendingByCategory(Category.MUSIC) - 10.99
            ) < 0.001,
            "Calculate music spending"
        );

        // Only active subscriptions should count
        spotify.pause();

        assertTrue(
            Math.abs(subscriber.calculateMonthlySpending() - 25.99) < 0.001,
            "Exclude paused subscriptions from monthly spending"
        );

        spotify.activate();

        assertTrue(
            Math.abs(subscriber.calculateMonthlySpending() - 36.98) < 0.001,
            "Include reactivated subscriptions in spending"
        );

        // Remove subscription
        subscriber.removeSubscription(netflix);

        assertTrue(
            subscriber.findSubscriptionById(1) == null,
            "Remove subscription"
        );

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.removeSubscription(null),
            "Reject null subscription during removal");

        assertThrows(IllegalArgumentException.class,
            () -> subscriber.removeSubscription(netflix),
            "Reject removal of missing subscription");

        assertTrue(
            Math.abs(subscriber.calculateMonthlySpending() - 20.99) < 0.001,
            "Recalculate spending after removal"
        );

        Subscriber emptySubscriber = new Subscriber(
            99, "Empty Subscriber", "empty@example.com"
        );

        assertOutput(
            () -> emptySubscriber.displaySubscriptions(),
            "No subscriptions found",
            "Display message for empty subscriptions"
        );

        System.out.println(
            "\nSubscriber results: " + passed +
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
