
package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import model.Subscriber;
import manager.SubscriptionManager;

public class SubscriptionManagerTests {

    private static int passed = 0;
    private static int failed = 0;

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
        } 
        finally {
            System.setOut(originalOut);
        }
        
        String actualOutput = output.toString();

        if (actualOutput.contains(expectedOutput)) {
            passed++;
            System.out.println("PASS: " + name);
        }
        else {
            failed++;
            System.out.println("FAIL: " + name);
            System.out.println("Expected output: " + expectedOutput);
            System.out.println("Actual output: " + actualOutput);
        }
    }


    public static void runTests() {
        passed = 0;
        failed = 0;

        System.out.println("\n====== SUBSCRIPTION MANAGER TESTS ======");

        SubscriptionManager manager = new SubscriptionManager();

        Subscriber forever = new Subscriber(
            1, "Forever Hyacinthe", "forever@example.com"
        );

        Subscriber duplicate = new Subscriber(
            1, "Jenny Miriotta", "jenny@example.com"
        );

        // Add subscriber
        assertThrows(IllegalArgumentException.class,
            () -> manager.addSubscriber(null),
            "Reject null subscriber");

        manager.addSubscriber(forever);

        assertTrue(
            manager.findSubscriberById(1) == forever,
            "Register and find subscriber"
        );

        // Duplicate ID
        assertThrows(IllegalArgumentException.class,
            () -> manager.addSubscriber(duplicate),
            "Reject duplicate subscriber ID");

        assertTrue(
            manager.findSubscriberById(1) == forever,
            "Preserve original subscriber after duplicate rejection"
        );

        // Find subscriber validation
        assertThrows(IllegalArgumentException.class,
            () -> manager.findSubscriberById(0),
            "Reject zero ID in findSubscriberById");

        assertThrows(IllegalArgumentException.class,
            () -> manager.findSubscriberById(-1),
            "Reject negative ID in findSubscriberById");

        assertTrue(
            manager.findSubscriberById(500) == null,
            "Return null for missing subscriber"
        );

        // Display subscribers
        // Display subscribers
        assertOutput(
            manager::displaySubscribers,
            "Forever Hyacinthe",
            "Display registered subscribers"
        );

        // Remove subscriber
        assertThrows(IllegalArgumentException.class,
            () -> manager.removeSubscriber(0),
            "Reject zero ID when removing subscriber");

        assertThrows(IllegalArgumentException.class,
            () -> manager.removeSubscriber(500),
            "Reject removal of missing subscriber");

        manager.removeSubscriber(1);

        assertTrue(
            manager.findSubscriberById(1) == null,
            "Remove existing subscriber"
        );

        // Display empty manager
        assertOutput(
            manager::displaySubscribers,
            "No subscribers registered.",
            "Display empty subscriber list"
        );

        System.out.println(
            "\nSubscriptionManager results: " + passed +
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
