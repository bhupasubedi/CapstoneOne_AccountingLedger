package com.ps;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileReader;


public class FinancialTransactions {

    static Scanner scanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();


    public static void main(String[] args) {
        loadTransactions();
        loadTransactionsFromFile();

    }

    // ***** BEGIN Create menu Instructions *****

    // Initialize the command variable (int mainMenuCommand;)

    int mainMenuCommand;
    // Create a do-while loop with a conditional for the command= home screen

    do {
        // display the menu
        System.out.println("1) Would you like too add deposit?");
        System.out.println("2) Would you like to make payment (Debit)?");
        System.out.println("3) Display the screen");
        System.out.println("0) Exit ");
        System.out.println("command not found! Try again");

        try {
            mainMenuCommand = scanner.nextInt();
        } catch (InputMismatchException ime) {

            mainMenuCommand = 0;


            switch (mainMenuCommand) {
                case 1:
                    adddeposit();
                    break;
                case 2:
                    makepayment();
                    break;
                case 3:
                    displaymenu();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found, please try again");
            }
        }
        while (mainMenuCommand != 0) ;

    }


    public static void loadTransactions() {

        LocalDate date = LocalDate.parse("2023-04-15");
        LocalTime time = LocalTime.parse("10:13:25");

        TransactionRecord transcation1 = new TransactionRecord("date", "time", "ergonomic keyboard", "Amazon", -89.50);

        transactions.add(transcation1);
    }

    public static void loadTransactionsFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transaction.csv"));

            String firstline = bufferedReader.readLine();
            String input;


            while ((input = bufferedReader.readLine()) != null) {


                String[] trans = input.split("\\|"); // "2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50" >> ["2023-04-15", "10:13:25", "ergonomic keyboard", "Amazon", "-89.50"]

                LocalDate date = LocalDate.parse(trans[0]);

                LocalTime time = LocalTime.parse(trans[1]);


                String description = trans[2];


                String vendor = trans[3];


                double amount = Double.parseDouble(trans[4]);

                transactions.add(new TransactionRecord(date, time, description, vendor, amount));

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adddeposit() {
        System.out.print("command to add an deposit");

        System.out.print("Please enter today's date");
        String date = inputScanner.nextLine();

        System.out.println("Please enter the time");
        String time = inputScanner.nextLine();

        System.out.println("Please enter the description");
        String description = inputScanner.nextLine();

        System.out.println("Please enter the vendor");
        String vendor = inputScanner.nextLine();

        System.out.println("Please enter the amount");
        double amount = inputScanner.nextInt();

    }

}



