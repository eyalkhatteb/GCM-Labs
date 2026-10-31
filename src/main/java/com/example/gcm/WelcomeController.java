package com.example.gcm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller of the welcome screen (welcome-view.fxml), which is shown after a successful login.
 */
public class WelcomeController {

    /** The line under the big "Welcome!" title. */
    @FXML
    private Label welcomeLabel;

    /**
     * Shows which user logged in.
     *
     * @param username the username of the user who logged in
     */
    public void setUsername(String username) {
        welcomeLabel.setText("You are logged in as " + username);
    }
}
