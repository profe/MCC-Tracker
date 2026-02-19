public class HealthTracker {
    // constants

    // instance vars
    private double weight;
    private double height;
    private double bmi;

    // constructors

    // mutators
    public boolean setWeight(double weight) {
        if (weight > 0) { //only set if valid data
            this.weight = weight;
            return true;
        } else {
            return false;
        }
    }

    // accessors
    public double getWeight() {
        return this.weight;
    }

    // other required methods
    @Override
    public String toString() {
        return "HealthTracker: weight = " + this.weight +
                ", height = " + this.height +
                ", BMI = " + this.bmi;
    }
}