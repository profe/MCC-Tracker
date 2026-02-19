public class Main {
    public static void main(String[] args) {
        HealthTracker tester = new HealthTracker();

        System.out.println(tester);

        // Test for setWeight method
        testSetWeight();
    }

    public static void testSetWeight() {
        HealthTracker ht = new HealthTracker();
        double testWeight = 205.6;
        boolean isValid = ht.setWeight(testWeight);
        if (isValid) {
            System.out.println("setWeight test passed: weight set to " + testWeight);
        } else {
            System.out.println("setWeight test failed: expected " + testWeight + ", got " + ht.getWeight());
        }
    }
}
