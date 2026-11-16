
package tests;

public class TestRunner {

    public static void main(String[] args) {

        System.out.println("\n==================================");
        System.out.println("       SUBSENTRY TEST RUNNER");
        System.out.println("==================================");

        SubscriptionTests.runTests();
        SubscriberTests.runTests();
        SubscriptionManagerTests.runTests();

        int totalPassed =
            SubscriptionTests.getPassed() +
            SubscriberTests.getPassed() +
            SubscriptionManagerTests.getPassed();

        int totalFailed =
            SubscriptionTests.getFailed() +
            SubscriberTests.getFailed() +
            SubscriptionManagerTests.getFailed();

        System.out.println("\n==================================");
        System.out.println("         FINAL TEST RESULTS");
        System.out.println("==================================");

        System.out.println("Total passed: " + totalPassed);
        System.out.println("Total failed: " + totalFailed);
        System.out.println("Total tests:  " + (totalPassed + totalFailed));

        if (totalFailed == 0) {
            System.out.println("\nALL TESTS PASSED!");
        } else {
            System.out.println("\nSOME TESTS FAILED. Review the output.");
        }
    }
}
