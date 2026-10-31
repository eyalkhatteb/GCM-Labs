package com.example.gcm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab 1 console program. It reads lines of the form "username password" from standard input,
 * keeps the valid users, and prints them sorted by username.
 * Invalid lines go to standard error together with the reason, so standard output
 * holds only the sorted list that the grader compares with out.txt.
 */
public class UsersApp {

    /**
     * Reads all lines and builds the list of valid users.
     * Each invalid line is printed to System.err with the reason it was rejected.
     *
     * @param in the source of the lines: the keyboard, a redirected file, or an opened file
     * @return the valid users, in the order they were read
     */
    public static ArrayList<User> readUsers(Scanner in) {
        ArrayList<User> users = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.err.println(line + "  -->  expected a username and a password");
                continue;
            }
            try {
                users.add(new User(parts[0], parts[1]));
            } catch (InvalidUserException e) {
                System.err.println(line + "  -->  " + e.getMessage());
            }
        }
        return users;
    }

    /**
     * Reads the users from standard input, sorts them by username and prints them.
     * Run it with input redirection, for example {@code java ... UsersApp < users.txt}.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            ArrayList<User> users = readUsers(in);
            users.sort((u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));
            for (User user : users) {
                // "\n" and not println: on Windows println ends lines with "\r\n",
                // but out.txt uses "\n" and is compared byte by byte.
                System.out.print(user.toOutputLine() + "\n");
            }
        }
    }
}
