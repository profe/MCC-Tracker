package edu.miracosta.cs112;

import edu.miracosta.cs112.models.UserProfile;
import edu.miracosta.cs112.models.DietTracker;

public class UserProfileTester {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("      USER PROFILE TESTER");
        System.out.println("====================================\n");

        // Test 1: Default constructor
        UserProfile user1 = new UserProfile();
        System.out.println("[TEST 1] Default Constructor");
        System.out.println(user1);
        System.out.println();

        // Test 2: Full constructor
        UserProfile user2 = new UserProfile(
                "Hasan",
                22,
                180.0,
                72.0,
                "Build Muscle",
                new DietTracker()
        );

        System.out.println("[TEST 2] Full Constructor");
        System.out.println(user2);
        System.out.println();

        // Test 3: Copy constructor
        UserProfile user3 = new UserProfile(user2);
        System.out.println("[TEST 3] Copy Constructor");
        System.out.println(user3);
        System.out.println();

        // Test 4: Setters
        user1.setName("Alex");
        user1.setAge(30);
        user1.setGoal("Lose Weight");
        user1.setDietTracker(new DietTracker());

        System.out.println("[TEST 4] After Using Setters");
        System.out.println(user1);
        System.out.println();

        // Test 5: Getters
        System.out.println("[TEST 5] Getter Methods");
        System.out.println("Name: " + user2.getName());
        System.out.println("Age: " + user2.getAge());
        System.out.println("Goal: " + user2.getGoal());
        System.out.println();

        // Test 6: Equals method
        System.out.println("[TEST 6] Equals Method");
        System.out.println("user2 equals user3: " + user2.equals(user3));
        System.out.println("user1 equals user2: " + user1.equals(user2));
        System.out.println();

        System.out.println("====================================");
        System.out.println("        TESTING COMPLETE");
        System.out.println("====================================");
    }
}
