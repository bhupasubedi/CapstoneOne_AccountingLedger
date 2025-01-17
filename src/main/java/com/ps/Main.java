package com.ps;

public class Main {

    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        UserInterface.mainMenu(username, password);
    }
}