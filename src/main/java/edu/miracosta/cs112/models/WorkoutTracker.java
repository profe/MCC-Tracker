
package edu.miracosta.cs112.models;

/**
 * The WorkoutTracker class stores workout goal information
 * and recommended exercises for the user.
 *
 * @author Hasan Mert Konukcu
 * @version 1.3
 */
public class WorkoutTracker {

    // Constants
    public static final String DEFAULT_GOAL = "Unknown";
    public static final String DEFAULT_WORKOUT = "None";

    // Instance Variables
    private String workoutName;
    private String workoutImageName;

    /**
     * Default constructor.
     * Creates a workout tracker object with default values.
     */
    public WorkoutTracker() {
        this.workoutName = DEFAULT_WORKOUT;
        this.workoutImageName = "default.png";
    }

    /**
     * Full constructor
     */
    public WorkoutTracker(String workoutName, String workoutImageName) {
        setWorkoutName(workoutName);
        setWorkoutImageName(workoutImageName);
    }

    /**
     * Copy constructor.
     * Creates a copy of another WorkoutTracker object.
     */
    public WorkoutTracker(WorkoutTracker other) {
        if (other != null) {
            this.workoutName = other.workoutName;
            this.workoutImageName = other.workoutImageName;
        } else {
            this.workoutName = DEFAULT_WORKOUT;
            this.workoutImageName = "default.png";
        }
    }

    /**
     * Gets the workout name.
     */
    public String getWorkoutName() {
        return workoutName;
    }

    /**
     * Sets the workout name.
     */
    public void setWorkoutName(String workoutName) {
        if (isValidText(workoutName)) {
            this.workoutName = workoutName;
        } else {
            this.workoutName = DEFAULT_WORKOUT;
        }
    }

    /**
     * Gets the workout image name.
     */
    public String getWorkoutImageName() {
        return workoutImageName;
    }

    /**
     * Sets the workout image name.
     */
    public void setWorkoutImageName(String workoutImageName) {
        if (isValidText(workoutImageName)) {
            this.workoutImageName = workoutImageName;
        } else {
            this.workoutImageName = "default.png";
        }
    }

    /**
     * Checks if the text input is valid.
     */
    private boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Returns workout tracker information as a string.
     */
    @Override
    public String toString() {
        return "Workout Name: " + workoutName +
                "\nWorkout Image: " + workoutImageName;
    }

    /**
     * Returns a formatted workout summary.
     */
    public String getWorkoutSummary() {
        return workoutName + " (" + workoutImageName + ")";
    }

    /**
     * Compares two WorkoutTracker objects.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof WorkoutTracker other) {
            return workoutName.equalsIgnoreCase(other.workoutName)
                    && workoutImageName.equalsIgnoreCase(other.workoutImageName);
        }

        return false;
    }
}