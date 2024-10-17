package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileReader;


public class FinancialTransactions {

    static Scanner scanner;
    static Scanner inputScanner;
    static ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();


    public static void main(String[] args) {
        loadTransactionsFromFile();

        // ***** BEGIN Create menu Instructions *****

        displayHomeMenu();


    }

    private static void displayHomeMenu() {
        int mainMenuCommand;
        do {
            // display the menu
            System.out.println("1) Would you like to add deposit? ");
            System.out.println("2) Would you like to make payment (Debit)?");
            System.out.println("3) Ledger screen");
            System.out.println("0) Exit ");

            scanner = new Scanner(System.in);

            try {
                mainMenuCommand = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input, please enter the number.");
                mainMenuCommand = 0;
            }

            switch (mainMenuCommand) {
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
                    displayMenu();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found, please try again");

            }

        } while (mainMenuCommand != 0);
    }

    public static void loadTransactionsFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

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

    public static void addDeposit() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


        System.out.println("Please enter today's date ");
        inputScanner = new Scanner(System.in);
        String date = inputScanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, dateFormatter);


        System.out.println("Please enter the time");
        inputScanner = new Scanner(System.in);
        String time = inputScanner.nextLine();
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        System.out.println("Please enter the description");
        inputScanner = new Scanner(System.in);
        String description = inputScanner.nextLine();

        System.out.println("Please enter the vendor");
        inputScanner = new Scanner(System.in);
        String vendor = inputScanner.nextLine();

        System.out.println("Please enter the amount");
        inputScanner = new Scanner(System.in);
        double amount = inputScanner.nextDouble();

        TransactionRecord transaction = new TransactionRecord(localDate, localTime, description, vendor, amount);
        transactions.add(transaction);


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()

            ));
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makePayment() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


        System.out.print("Please enter today's date ");
        inputScanner = new Scanner(System.in);
        String date = inputScanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, dateFormatter);


        System.out.println("Please enter the time");
        inputScanner = new Scanner(System.in);
        String time = inputScanner.nextLine();
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        System.out.println("Please enter the description");
        inputScanner = new Scanner(System.in);
        String description = inputScanner.nextLine();

        System.out.println("Please enter the vendor");
        inputScanner = new Scanner(System.in);
        String vendor = inputScanner.nextLine();

        System.out.println("Please enter the amount");
        inputScanner = new Scanner(System.in);
        double amount = inputScanner.nextDouble();

        TransactionRecord transaction = new TransactionRecord(localDate, localTime, description, vendor, amount);
        transactions.add(transaction);


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()

            ));
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void displayMenu() {

        int subMenuCommand;

        do {
            //// display the sub menu options

            System.out.println("Welcome to the Ledger System! Please choose an option:");
            System.out.println("1) Display all entries ");
            System.out.println("2) Display only the entries that are deposits into the account ");
            System.out.println("3) Display only the negative entries ");
            System.out.println("4) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search");

            System.out.println("0) Back");
            System.out.print("Command: ");

            inputScanner = new Scanner(System.in);
            subMenuCommand = inputScanner.nextInt();

            switch (subMenuCommand) {
                case 1:
                    displayAllEntries();
                    break;
                case 2:
                    displayDepositsOnly();
                    break;
                case 3:
                    displayNegativeBalance();
                    break;
                case 4:
                    ShowReports();
                    break;
                case 0:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Command not found. Try again");
            }

        } while (subMenuCommand != 0);

    }

    public static void displayAllEntries() {

        System.out.println("Placeholder: Display All");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));

        }
    }

    public static void displayDepositsOnly() {

        System.out.println("Deposits Only:");
        for (int i = 0; i < transactions.size(); i++) {
            TransactionRecord transaction = transactions.get(i);
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    public static void displayNegativeBalance() {
        System.out.println("Negative Balance Transactions:");
        for (TransactionRecord transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);

            }
        }
    }

    public static void ShowReports() {
        int newMenuCommand;

        do {
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Go to the home page");

            System.out.println("0) Back");
            System.out.print("Command: ");

            inputScanner = new Scanner(System.in);
            newMenuCommand = inputScanner.nextInt();

            switch (newMenuCommand) {
                case 1:
                    monthDate();
                    break;
                case 2:
                    previousMonth();
                    break;
                case 3:
                    yearDate();
                    break;
                case 4:
                    previousYear();
                    break;
                case 5:
                    searchbyVendor();
                    break;
                case 0:
                    System.out.println("Going back..");
                    break;
                case 6:
                    System.out.println("Going back to the main menu...");
                    newMenuCommand = 0;
                    displayHomeMenu();
                    break;
                default:
                    System.out.println("Command not found. Try again");
            }

        } while (newMenuCommand != 0);

    }

    public static void monthDate() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();


        for (int i = 0; i < transactions.size(); i++) {
            int transactionsMonth = transactions.get(i).getDate().getMonthValue();
            int transactionsYear = transactions.get(i).getDate().getYear();

            if (currentMonth == transactionsMonth && currentYear == transactionsYear){
                System.out.println(transactions.get(i));
            }

        }

    }

    public static void previousMonth() {

        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue()-1;
        int currentYear = today.getYear();


        for (int i = 0; i < transactions.size(); i++) {
            int transactionsMonth = transactions.get(i).getDate().getMonthValue();
            int transactionsYear = transactions.get(i).getDate().getYear();

            if (currentMonth == transactionsMonth && currentYear == transactionsYear){
                System.out.println(transactions.get(i));
            }

        }


    }

    public static void yearDate() {

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();


        for (int i = 0; i < transactions.size(); i++) {
            int transactionsYear = transactions.get(i).getDate().getYear();

            if (currentYear == transactionsYear){
                System.out.println(transactions.get(i));
            }

        }



    }

    public static void previousYear() {

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear() - 1;


        for (int i = 0; i < transactions.size(); i++) {
            int transactionsYear = transactions.get(i).getDate().getYear();

            if (currentYear == transactionsYear){
                System.out.println(transactions.get(i));
            }

        }

    }

    public static void searchbyVendor () {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the vendor name to search: ");
            String vendor = scanner.nextLine();

            System.out.println("Transactions for Vendor: " + vendor);
            for (TransactionRecord transaction : transactions) {
                if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                    System.out.println(transaction);
            }
        }
    }
}