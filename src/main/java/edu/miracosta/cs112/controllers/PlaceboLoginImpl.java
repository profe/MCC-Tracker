package edu.miracosta.cs112.controllers;

import java.util.Objects;

public class PlaceboLoginImpl {
    private final String[] usernames = new String[10];
    private final String[] passwords = new String[10];

    public PlaceboLoginImpl() {
        //Random names and passwords that were only generated for testing.

        usernames[0] = "alice";
        usernames[1] = "bob";
        usernames[2] = "carol";
        usernames[3] = "dave";
        usernames[4] = "eve";
        usernames[5] = "frank";
        usernames[6] = "grace";
        usernames[7] = "heidi";
        usernames[8] = "ivan";
        usernames[9] = "judy";


        passwords[0] = "alicePass";
        passwords[1] = "bobPass";
        passwords[2] = "carolPass";
        passwords[3] = "davePass";
        passwords[4] = "evePass";
        passwords[5] = "frankPass";
        passwords[6] = "gracePass";
        passwords[7] = "heidiPass";
        passwords[8] = "ivanPass";
        passwords[9] = "judyPass";
    }

    /**
     * Check that the username exists and that the password matches.
     * Throws InvalidCredentialsException with a specific message on failure.
     */
    public void checkCredentials(String username, String password) throws InvalidCredentialsException {
        if (username == null || username.isBlank()) {
            throw new InvalidCredentialsException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new InvalidCredentialsException("Password cannot be empty.");
        }

        //checks if the username exist
        int idx = -1;
        for (int i = 0; i < usernames.length; i++) {
            if (Objects.equals(usernames[i], username)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            throw new InvalidCredentialsException("Username not found.");
        }

        // Check password at same index
        if (!Objects.equals(passwords[idx], password)) {
            throw new InvalidCredentialsException("Password incorrect for the given username.");
        }

    }
}
