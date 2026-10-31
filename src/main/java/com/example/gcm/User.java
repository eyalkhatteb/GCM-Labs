package com.example.gcm;

/**
 * A user of the system: a username, which must be an email address, and a password.
 * The constructor refuses invalid values, so every User object that exists is valid.
 */
public class User {

    /** Longest username allowed. */
    private static final int MAX_USERNAME_LENGTH = 50;
    /** Shortest password allowed. */
    private static final int MIN_PASSWORD_LENGTH = 8;
    /** Longest password allowed. */
    private static final int MAX_PASSWORD_LENGTH = 12;

    /**
     * Local part, then "@", then a domain that starts with a letter or digit,
     * then a dot and at least 2 letters at the end.
     */
    private static final String EMAIL_REGEX =
            "[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}";

    /** The symbols a password may use: Shift plus the number-row keys, from ! to +. */
    private static final String SYMBOLS = "!@#$%^&*()_+";

    /** The username, which is an email address. */
    private final String username;
    /** The password. There is no getter for it. */
    private final String password;

    /**
     * Creates a user, but only if both values follow the rules.
     *
     * @param username the email address that serves as the username
     * @param password the password
     * @throws InvalidUserException if a value breaks a rule; the message says which one
     */
    public User(String username, String password) throws InvalidUserException {
        checkUsername(username);
        checkPassword(password);
        this.username = username;
        this.password = password;
    }

    /**
     * Checks the username: first its length, then that it looks like an email address.
     *
     * @param username the username to check
     * @throws InvalidUserException at the first rule that is broken
     */
    private static void checkUsername(String username) throws InvalidUserException {
        if (username.length() > MAX_USERNAME_LENGTH) {
            throw new InvalidUserException("Username is too long, try something shorter");
        }
        if (!username.matches(EMAIL_REGEX)) {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
    }

    /**
     * Checks the password: first its length, then which characters it uses.
     *
     * @param password the password to check
     * @throws InvalidUserException at the first rule that is broken
     */
    private static void checkPassword(String password) throws InvalidUserException {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidUserException("Your password is too short, add more characters");
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new InvalidUserException("Your password is too long, try a shorter one");
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        for (char c : password.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                hasLetter = true;
            } else if (c >= '0' && c <= '9') {
                hasDigit = true;
            } else if (SYMBOLS.indexOf(c) >= 0) {
                hasSymbol = true;
            } else {
                throw new InvalidUserException("Please enter a valid password");
            }
        }
        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new InvalidUserException("Please enter a valid password");
        }
    }

    /**
     * Returns the username.
     *
     * @return the username (an email address)
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks whether a typed username and password belong to this user.
     * The login screen uses it, so the password never has to leave this class.
     *
     * @param username the username that was typed
     * @param password the password that was typed
     * @return true if both are equal to this user's username and password
     */
    public boolean matches(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns the line that UsersApp prints for this user: the username, one space,
     * and the password. The assignment's expected output includes the password,
     * so this is the only method that reveals it.
     *
     * @return the text "username password"
     */
    public String toOutputLine() {
        return username + " " + password;
    }

    /**
     * Describes the user without the password, so it is safe to print anywhere.
     *
     * @return the username only, in the form User{username='...'}
     */
    @Override
    public String toString() {
        return "User{username='" + username + "'}";
    }
}
