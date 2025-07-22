package com.example.revive_app.model;

public class RootUser extends User {
    private static RootUser instance;

    // Private constructor to prevent instantiation
    private RootUser() {}

    // Public method to provide access to the single instance
    public static synchronized RootUser getInstance() {
        if (instance == null) {
            instance = new RootUser();
        }
        return instance;
    }
}
