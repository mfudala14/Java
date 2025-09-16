package assignment2;

//The Activity class represents an individual activity with an ID, start time, and finish time.
public class Activity {
    private int id; // Unique identifier for the activity.
    private int start;// Start time of the activity.
    private int finish;// Finish time of the activity.

    // create a new activity
    public Activity(int id, int start, int finish) {
        this.id = id; // Assign the provided ID to the activity's ID.
        this.start = start; // Assign the provided start time to the activity's start time.
        this.finish = finish; // Assign the provided finish time to the activity's finish time.
    }

    // test if two activities overlap
    public Boolean overlap(Activity activity) {
        // An activity overlaps if its start time is less than the finish time of the other activity
        // and its finish time is greater than the start time of the other activity.
        return !((start >= activity.finish) || (activity.start >= finish));
    }

    public void print() {
        System.out.printf("%3d", id);// Print the ID of the activity, formatted to take up 3 spaces.
        System.out.println(" ".repeat(start) + "#".repeat(finish-start));// Print the activity as a sequence of spaces and "#" characters to represent the time duration.
    }
}
