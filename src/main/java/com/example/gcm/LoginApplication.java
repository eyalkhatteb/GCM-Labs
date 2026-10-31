package com.example.gcm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab 2: the JavaFX application of the GCM login window.
 * It reads the valid users from the input file with the Lab 1 code,
 * then opens the login screen.
 */
public class LoginApplication extends Application {

    /**
     * The input file of Lab 1. A relative path is looked up in the working directory,
     * which is the project folder when the program runs from IntelliJ or Maven.
     */
    private static final String USERS_FILE = "users.txt";

    /**
     * Reads the users, shows the login screen, and makes the X button end the program.
     *
     * @param stage the main window, created by JavaFX
     * @throws IOException if users.txt or login-view.fxml cannot be read
     */
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<User> users;
        try (Scanner in = new Scanner(new File(USERS_FILE))) {
            users = UsersApp.readUsers(in);
        }

        FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load());
        LoginController controller = loader.getController();
        controller.setUsers(users);

        stage.setTitle("Users Login");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.show();
    }
}
