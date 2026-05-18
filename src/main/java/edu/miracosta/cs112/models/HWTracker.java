package edu.miracosta.cs112.models;

public class HWTracker {

    public static final String DEFAULT_COURSE   = "Unknown Course";
    public static final String DEFAULT_TITLE    = "Untitled Assignment";
    public static final String DEFAULT_DUE_DATE = "TBD";
    public static final String DEFAULT_DATE     = "TBD";
    public static final String DEFAULT_GRADE    = "";

    private String date;
    private String course;
    private String title;
    private String dueDate;
    private String grade;
    private boolean submitted;
    private java.util.List<Note> notes;

    public HWTracker() {
        this.date      = DEFAULT_DATE;
        this.course    = DEFAULT_COURSE;
        this.title     = DEFAULT_TITLE;
        this.dueDate   = DEFAULT_DUE_DATE;
        this.grade     = DEFAULT_GRADE;
        this.submitted = false;
        this.notes     = new java.util.ArrayList<>();
    }

    public HWTracker(String course, String title, String dueDate, boolean submitted) {
        this.date      = DEFAULT_DATE;
        this.course    = (course  != null && !course.isBlank())  ? course  : DEFAULT_COURSE;
        this.title     = (title   != null && !title.isBlank())   ? title   : DEFAULT_TITLE;
        this.dueDate   = (dueDate != null && !dueDate.isBlank()) ? dueDate : DEFAULT_DUE_DATE;
        this.grade     = DEFAULT_GRADE;
        this.submitted = submitted;
        this.notes     = new java.util.ArrayList<>();
    }

    public String getDate() {
        return (date != null) ? date : DEFAULT_DATE;
    }

    public String getCourse() {
        return (course != null) ? course : DEFAULT_COURSE;
    }

    public String getTitle() {
        return (title != null) ? title : DEFAULT_TITLE;
    }

    public String getDueDate() {
        return (dueDate != null) ? dueDate : DEFAULT_DUE_DATE;
    }

    public String getGrade() {
        return (grade != null) ? grade : DEFAULT_GRADE;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setDate(String date) {
        if (date != null && !date.isBlank()) {
            this.date = date;
        }
    }

    public void setCourse(String course) {
        if (course != null && !course.isBlank()) {
            this.course = course;
        }
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    public void setDueDate(String dueDate) {
        if (dueDate != null && !dueDate.isBlank()) {
            this.dueDate = dueDate;
        }
    }

    public void setGrade(String grade) {
        this.grade = (grade != null) ? grade : DEFAULT_GRADE;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public void addNote(String content) {
        if (content != null && !content.isBlank()) {
            notes.add(new Note(content));
        }
    }

    public java.util.List<Note> getNotes() {
        return java.util.Collections.unmodifiableList(notes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HWTracker)) return false;
        HWTracker other = (HWTracker) obj;
        return this.getCourse().equals(other.getCourse())
                && this.getTitle().equals(other.getTitle())
                && this.getDueDate().equals(other.getDueDate());
    }

    @Override
    public String toString() {
        return "HWTracker {"
                + "\n  Date      : " + getDate()
                + "\n  Course    : " + getCourse()
                + "\n  Title     : " + getTitle()
                + "\n  Due Date  : " + getDueDate()
                + "\n  Grade     : " + getGrade()
                + "\n  Submitted : " + (submitted ? "Yes" : "No")
                + "\n  Notes     : " + notes.size() + " note(s)"
                + "\n}";
    }

    public class Note {

        private String content;
        private final String timestamp;

        public Note(String content) {
            this.content   = content;
            this.timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .format(new java.util.Date());
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] " + title + " (" + course + "): " + content;
        }
    }
}