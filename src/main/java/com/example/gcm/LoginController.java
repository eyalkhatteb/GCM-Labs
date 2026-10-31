package com.example.gcm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller of the login screen (login-view.fxml).
 * It checks the username and password that were typed against the list of valid users.
 */
public class LoginController {

    /** The field for the username (an email address). */
    @FXML
    private TextField usernameField;

    /** The field for the password. A PasswordField shows dots instead of the characters. */
    @FXML
    private PasswordField passwordField;

    /** The red error message. It stays empty until a login fails. */
    @FXML
    private Label errorLabel;

    /** The valid users from the input file. */
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Gives the controller the valid users that were read from the input file.
     *
     * @param users the valid users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Runs when the login button is clicked or Enter is pressed.
     * If a user with this username and password exists, the welcome screen replaces
     * the login screen. Otherwise an error message appears in the window (not a pop-up).
     *
     * @param event the click on the login button
     * @throws IOException if welcome-view.fxml cannot be loaded
     */
    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        for (User user : users) {
            if (user.matches(username, password)) {
                showWelcomeScreen(user.getUsername());
                return;
            }
        }
        errorLabel.setText("user or password do not match");
    }

    /**
     * Loads welcome-view.fxml and shows it in the same window, in place of the login screen.
     *
     * @param username the user who logged in
     * @throws IOException if welcome-view.fxml cannot be loaded
     */
    private void showWelcomeScreen(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("welcome-view.fxml"));
        Parent root = loader.load();
        WelcomeController controller = loader.getController();
        controller.setUsername(username);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root));
    }
}
