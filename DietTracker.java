public class DietTracker extends HealthTracker {

    // instance variables
    private String foodName;
    private int calories;
    private double protein;
    private boolean isSingleMeal;
    private String goal;

    // BAD DEFAULT CONSTRUCTOR. please redo after doing setters
    // job is to error check/handle, set ALL the data
    public DietTracker() {
        // set all the INHERITED data
        super();

        // set all the NEW data
        this.foodName = "food";
        this.calories = 0;
        this.protein = 0;
        this.isSingleMeal = true;
        this.goal = "Fill my stomach";
    }

    @Override
    public String toString() {
        return "DietTracker for food: " + this.foodName +
                " with " + this.calories + " calories, protein =" +
                this.protein + ", is single meal? " + this.isSingleMeal +
                " with goal of " + this.goal;
    }
}
