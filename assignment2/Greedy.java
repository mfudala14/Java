package assignment2;

import java.util.LinkedList;

public class Greedy {

    // TASK 1.B.a
    //The goal is to select the maximum number of non-overlapping activities from a list.
    public static LinkedList<Activity> activitySelection(LinkedList<Activity> activities) {
        // Create a LinkedList to store the selected activities
        LinkedList<Activity> selectedActivities = new LinkedList<>();
        // If the input list is empty, return an empty list of selected activities
        if (activities.isEmpty()){
            return selectedActivities;
        }
        // Start by selecting the first activity (this assumes the activities are sorted by their finish times)
        Activity lastSelected = activities.getFirst();
        // Add the first selected activity to the result list
        selectedActivities.add(lastSelected);
        // Repeat through all the activities
        for (Activity currentActivity : activities) {
            // If the current activity does not overlap with the last selected one, select it
            if (!lastSelected.overlap(currentActivity)) {
                selectedActivities.add(currentActivity);  // Add the current activity to the selected list
                lastSelected = currentActivity; // Update the last selected activity
            }
        }
        // Return the list of selected activities
        return selectedActivities;
    }

    // TASK 1.B.b
    //This method implements the Greedy algorithm for making change from a given amount using the fewest number of coins/denominations.
    public static LinkedList<Integer> makeChange(int amount, int[] denominations) {
        // Create a LinkedList to store the coins/denominations used to make the change.
        LinkedList<Integer> change = new LinkedList<>();
        // for(int i = 0; i < d.length; i++)
        // Repeat through each denomination provided in the denominations array.
        // The loop will check if the current denomination can be used to make change for the remaining amount.
        for (int denomination : denominations) { // enhanced for loop
            // Continue subtracting the current denomination from the amount until it is less than the denomination.
            while (amount >= denomination) {
                amount -= denomination;  // Reduce the amount by the denomination.
                change.add(denomination); // Add the denomination to the list of coins used.
            }
        }
        // Return the list of denominations used to make up the original amount.
        return change;
    }

    public static void main(String[] args) {
        // Initialize a LinkedList to store a series of activities, each with a start time, end time, and an ID.
        // These activities represent tasks or events that will be selected for execution using a greedy algorithm.
        LinkedList<Activity> activities = new LinkedList<Activity>();
        // Add activities to the list. Each activity is represented by an ID, start time, and end time.
        activities.add(new Activity(1,1, 4));//Activity 1: starts at 1, ends at 4
        activities.add(new Activity(2, 3, 5));//Activity 2: starts at 3 end at 5
        activities.add(new Activity(3, 0, 6));//Activity 3: starts at 0 end at 6
        activities.add(new Activity(4, 5, 7));//Activity 4: starts at 5 end at 7
        activities.add(new Activity(5, 3, 8));//Activity 5: starts at 3 end at 8
        activities.add(new Activity(6, 5, 9));//Activity 6: starts at 5 end at 9
        activities.add(new Activity(7, 6, 10));//Activity 7: starts at 6 end at 10
        activities.add(new Activity(8, 8, 11));//Activity 8: starts at 8 end at 11
        activities.add(new Activity(9, 8, 12));//Activity 9: starts at 8 end at 12
        activities.add(new Activity(10, 2, 13));//Activity 10: starts at 2 end at 13
        activities.add(new Activity(11, 12, 14));//Activity 11: starts at 12 end at 14
        // Call the activitySelection function to determine the maximum number of non-overlapping activities.
        activitySelection(activities).forEach(a -> a.print());

        System.out.println();//print new line
        // Call the makeChange function to compute the coin denominations for making change for the amount 1234.
        // The array of denominations is provided, and the result is printed to the console.
        makeChange(1234, new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }).forEach(i -> System.out.println(i));// For each coin denomination in the change list, print the coin value.
    }
}
