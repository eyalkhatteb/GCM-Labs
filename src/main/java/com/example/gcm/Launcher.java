package com.example.gcm;

import javafx.application.Application;

/**
 * Starts the GCM login program. Keeping main() in a class that does not extend
 * Application is the usual way to run a JavaFX program from a JAR file.
 */
public class Launcher {

    /**
     * Program entry point: launches the JavaFX application.
     *
     * @param args command-line arguments, passed on to JavaFX
     */
    public static void main(String[] args) {
        Application.launch(LoginApplication.class, args);
    }
}
