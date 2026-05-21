package edu.miracosta.cs112.models;

/**
 * Represents a user profile for the MCC Tracker app
 * A UserProfile stores basic user information and connects the user to a DietTracker object
 */

public class UserProfile {
    // Instance variables
    private String name;
    private int age;
    private double weight;
    private double height;
    private String goal;
    private WorkoutTracker workout1, workout2, workout3;
    private DietTracker dietTracker;

    /**
     * Default constructor.
     * Creates a UserProfile with default values.
     */
    public UserProfile() {
        this("Unknown", 0, 0.0, 0.0, "No goal set", new DietTracker());
    }

    /**
     * Full constructor.
     * Creates a UserProfile using the given name, age, weight, height, goal and DietTracker.
     */
    public UserProfile(String name, int age, double weight, double height, String goal, DietTracker dietTracker) {
        this.setName(name);
        this.setAge(age);
        this.setWeight(weight);
        this.setHeight(height);
        this.setGoal(goal);
        this.setDietTracker(dietTracker);
    }

    /**
     * Constructor for creating a UserProfile from the workout main page.
     * Creates a UserProfile using the given name, weight, height, and age.
     */
    public UserProfile(String name, double weight, double height, int age) {
        this(name, age, weight, height, "Workout goal selected", new DietTracker());
    }

    /**
     * Copy constructor.
     * Creates a new UserProfile using another UserProfile object's data.
     */
    public UserProfile(UserProfile original) {
        this(original.name, original.age, original.weight, original.height, original.goal, original.dietTracker);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGoal() {
        return goal;
    }

    public DietTracker getDietTracker() {
        return dietTracker;
    }

    public WorkoutTracker getWorkout1() {
        return workout1;
    }

    public WorkoutTracker getWorkout2() {
        return workout2;
    }

    public WorkoutTracker getWorkout3() {
        return workout3;
    }

    public void setWorkout1(WorkoutTracker workout1) {
        this.workout1 = workout1;
    }

    public void setWorkout2(WorkoutTracker workout2) {
        this.workout2 = workout2;
    }

    public void setWorkout3(WorkoutTracker workout3) {
        this.workout3 = workout3;
    }

    // Setters with validation
    public boolean setName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public boolean setAge(int age) {
        if (age < 0) {
            return false;
        }
        this.age = age;
        return true;
    }

    public boolean setWeight(double weight) {
        if (weight < 0) {
            return false;
        }
        this.weight = weight;
        return true;
    }

    public boolean setHeight(double height) {
        if (height < 0) {
            return false;
        }
        this.height = height;
        return true;
    }

    public boolean setGoal(String goal) {
        if (goal == null || goal.isBlank()) {
            return false;
        }
        this.goal = goal;
        return true;
    }

    public boolean setDietTracker(DietTracker dietTracker) {
        if (dietTracker == null) {
            return false;
        }
        this.dietTracker = dietTracker;
        return true;
    }

    /**
     * Checks whether this UserProfile has the same values as another object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserProfile)) {
            return false;
        }

        UserProfile other = (UserProfile) obj;

        return this.age == other.age
                && Double.compare(this.weight, other.weight) == 0
                && Double.compare(this.height, other.height) == 0
                && this.name.equals(other.name)
                && this.goal.equals(other.goal)
                && this.dietTracker.equals(other.dietTracker);
    }

    /**
     * Returns a String representation of the UserProfile object
     */
    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", goal='" + goal + '\'' +
                ", workout1=" + workout1 +
                ", workout2=" + workout2 +
                ", workout3=" + workout3 +
                ", dietTracker=" + dietTracker +
                '}';
    }
}
