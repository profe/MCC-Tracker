public class Main {
    public static void main(String[] args) {
        HealthTracker tester = new HealthTracker();
        HealthTracker copyOfTester = new HealthTracker(tester); //DEEP COPY!!
        //HealthTracker copyOfTester = tester; //SHALLOW COPY!!

        System.out.println("Start = " + tester);
        System.out.println("Start Copy= " + copyOfTester);

        tester.setWeight(205.6);
        tester.setHeight(60);

        System.out.println("After changes = " + tester); //205.6, 100
        System.out.println("After changescopy = " + copyOfTester); //100, 100

        // Test for setWeight method
        // testSetWeight();
    }

    // public static void testSetWeight() {
    // HealthTracker ht = new HealthTracker();
    // double testWeight = 205.6;
    // boolean isValid = ht.setWeight(testWeight);
    // if (isValid) {
    // System.out.println("setWeight test passed: weight set to " + testWeight);
    // } else {
    // System.out.println("setWeight test failed: expected " + testWeight + ", got "
    // + ht.getWeight());
    // }
    // }
}
