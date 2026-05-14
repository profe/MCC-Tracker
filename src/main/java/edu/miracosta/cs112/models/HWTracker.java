package edu.miracosta.cs112.models;

/**
 * HWTracker.java
 * Model class for tracking homework assignments with priority levels.
 *
 * @author Samuel Carter
 * @version 3.0
 * @since 2026-03-25
 */
public class HWTracker {

    // -------------------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------------------

    /** Default course name used when none is provided. */
    public static final String DEFAULT_COURSE = "Unknown Course";

    /** Default assignment title used when none is provided. */
    public static final String DEFAULT_TITLE = "Untitled Assignment";

    /** Default due date string used when none is provided. */
    public static final String DEFAULT_DUE_DATE = "TBD";

    /** Default point value for an assignment. */
    public static final int DEFAULT_POINTS = 0;

    /** Default priority level for an assignment. */
    public static final String DEFAULT_PRIORITY = "Medium";

    // -------------------------------------------------------------------------
    // Instance Variables
    // -------------------------------------------------------------------------

    /** The name of the course this assignment belongs to. */
    private String course;

    /** The title or name of the assignment. */
    private String title;

    /** The due date of the assignment as a formatted string. */
    private String dueDate;

    /** The total points the assignment is worth. */
    private int points;

    /** Whether the assignment has been submitted. */
    private boolean submitted;

    /** Priority level: "High", "Medium", or "Low". */
    private String priority;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Default constructor. Initializes all fields to their default values.
     */
    public HWTracker() {
        this.course    = DEFAULT_COURSE;
        this.title     = DEFAULT_TITLE;
        this.dueDate   = DEFAULT_DUE_DATE;
        this.points    = DEFAULT_POINTS;
        this.submitted = false;
        this.priority  = DEFAULT_PRIORITY;
    }

    /**
     * Partial constructor. Sets course and title; other fields use defaults.
     *
     * @param course the name of the course
     * @param title  the title of the assignment
     */
    public HWTracker(String course, String title) {
        this.course    = (course != null && !course.isBlank()) ? course : DEFAULT_COURSE;
        this.title     = (title  != null && !title.isBlank())  ? title  : DEFAULT_TITLE;
        this.dueDate   = DEFAULT_DUE_DATE;
        this.points    = DEFAULT_POINTS;
        this.submitted = false;
        this.priority  = DEFAULT_PRIORITY;
    }

    /**
     * Full constructor. Initializes all fields with the provided values.
     *
     * @param course    the name of the course
     * @param title     the title of the assignment
     * @param dueDate   the due date as a string
     * @param points    the total point value (must be >= 0)
     * @param submitted whether the assignment has already been submitted
     */
    public HWTracker(String course, String title, String dueDate,
                     int points, boolean submitted) {
        this.course    = (course  != null && !course.isBlank())  ? course  : DEFAULT_COURSE;
        this.title     = (title   != null && !title.isBlank())   ? title   : DEFAULT_TITLE;
        this.dueDate   = (dueDate != null && !dueDate.isBlank()) ? dueDate : DEFAULT_DUE_DATE;
        this.points    = (points  >= 0) ? points : DEFAULT_POINTS;
        this.submitted = submitted;
        this.priority  = DEFAULT_PRIORITY;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /**
     * Returns the course name, or the default if null.
     *
     * @return the course this assignment belongs to
     */
    public String getCourse() {
        if (course == null) {
            return DEFAULT_COURSE;
        }
        return course;
    }

    /**
     * Returns the assignment title, or the default if null.
     *
     * @return the title of this assignment
     */
    public String getTitle() {
        if (title == null) {
            return DEFAULT_TITLE;
        }
        return title;
    }

    /**
     * Returns the due date, or the default if null.
     *
     * @return the due date of this assignment
     */
    public String getDueDate() {
        if (dueDate == null) {
            return DEFAULT_DUE_DATE;
        }
        return dueDate;
    }

    /**
     * Returns the point value of the assignment.
     *
     * @return the number of points this assignment is worth
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns whether the assignment has been submitted.
     *
     * @return true if submitted, false otherwise
     */
    public boolean isSubmitted() {
        return submitted;
    }

    /**
     * Returns the priority level of the assignment.
     *
     * @return "High", "Medium", or "Low"
     */
    public String getPriority() {
        if (priority == null) {
            return DEFAULT_PRIORITY;
        }
        return priority;
    }

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /**
     * Sets the course name. Ignores null or blank values.
     *
     * @param course the new course name
     */
    public void setCourse(String course) {
        if (course != null && !course.isBlank()) {
            this.course = course;
        }
    }

    /**
     * Sets the assignment title. Ignores null or blank values.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    /**
     * Sets the due date. Ignores null or blank values.
     *
     * @param dueDate the new due date string
     */
    public void setDueDate(String dueDate) {
        if (dueDate != null && !dueDate.isBlank()) {
            this.dueDate = dueDate;
        }
    }

    /**
     * Sets the point value. Ignores negative values.
     *
     * @param points the new point value (must be >= 0)
     */
    public void setPoints(int points) {
        if (points >= 0) {
            this.points = points;
        }
    }

    /**
     * Sets the submitted status of the assignment.
     *
     * @param submitted true if the assignment has been submitted
     */
    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * Sets the priority level. Accepts "High", "Medium", or "Low" only.
     *
     * @param priority the priority level
     */
    public void setPriority(String priority) {
        if (priority != null && (priority.equals("High") || priority.equals("Medium") || priority.equals("Low"))) {
            this.priority = priority;
        }
    }

    // -------------------------------------------------------------------------
    // equals
    // -------------------------------------------------------------------------

    /**
     * Compares this HWTracker to another object for equality.
     *
     * @param obj the object to compare against
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HWTracker)) {
            return false;
        }
        HWTracker other = (HWTracker) obj;
        return this.getCourse().equals(other.getCourse())
                && this.getTitle().equals(other.getTitle())
                && this.getDueDate().equals(other.getDueDate());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    /**
     * Returns a formatted string representation of this HWTracker.
     *
     * @return a readable summary of the assignment's fields
     */
    @Override
    public String toString() {
        return "HWTracker {"
                + "\n  Course    : " + getCourse()
                + "\n  Title     : " + getTitle()
                + "\n  Due Date  : " + getDueDate()
                + "\n  Points    : " + points
                + "\n  Priority  : " + getPriority()
                + "\n  Submitted : " + (submitted ? "Yes" : "No")
                + "\n}";
    }
}