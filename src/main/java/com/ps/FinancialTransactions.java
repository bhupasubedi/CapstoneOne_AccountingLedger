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

    static Scanner scanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();


    public static void main(String[] args) {
        loadTransactionsFromFile();

        // ***** BEGIN Create menu Instructions *****

        int mainMenuCommand;

        do {
            // display the menu
            System.out.println("1) Would you like to add deposit? ");
            System.out.println("2) Would you like to make payment (Debit)?");
            System.out.println("3) Display the screen");
            System.out.println("0) Exit ");

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


        System.out.print("Please enter today's date ");
        String date = inputScanner.nextLine();

        System.out.println("Please enter the time");
        String time = inputScanner.nextLine();

        System.out.println("Please enter the description");
        String description = inputScanner.nextLine();

        System.out.println("Please enter the vendor");
        String vendor = inputScanner.nextLine();

        System.out.println("Please enter the amount");
        double amount = inputScanner.nextInt();

        TransactionRecord transaction = new TransactionRecord(date, time, description, vendor, amount);
        transactions.add(transaction);


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transaction.csv", true));
            bufferedWriter.write(String.format("%s|%s|%s|%s|%.2f",
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

        System.out.println("1) What is your debit card number?");
        String cardNumber = inputScanner.nextLine();

        System.out.println("2) What is the name on the debit card?");
        String nameonCard = inputScanner.nextLine();

        System.out.println("3) What is the expiration date of your debit card? (Please enter in MM/YY format)");
        String expirationDate = inputScanner.nextLine();

        System.out.println("4) What is the CVV number on the back of your debit card?");
        String cvvNumber = inputScanner.nextLine();

        System.out.println("5) How much would you like to pay?");
        double payment = inputScanner.nextInt();
        inputScanner.nextLine();

        System.out.println("6) What is the billing address associated with your debit card? (Street, City, State, Zip Code)");
        String address = inputScanner.nextLine();


        System.out.println("7)What is your contact phone number or email address for confirmation?");
        String emailorPhone = inputScanner.nextLine();

        //need to work on it

        System.out.println("0) Back");
        System.out.print("Command: ");

    }

    public static void displayMenu() {

        int subMenuCommand;

        do {
            //// display the sub menu options

            System.out.println("Welcome to the Ledger System! Please choose an option:");
            System.out.println("1) Display all entries ");
            System.out.println("2) Display only deposits");
            System.out.println("3) Display the negative balance");
            System.out.println("4) Show reports");

            System.out.println("0) Back");
            System.out.print("Command: ");

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
            System.out.println("5) Go back to the previous screen");
            System.out.println("6) Go to the home page");

            System.out.println("0) Back");
            System.out.print("Command: ");

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
                case 6:
                    previousScreen();
                    break;
                case 7:
                    homePage();
                    break;
                case 0:
                    System.out.println("Going back to the main menu...");
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
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String transactions1 = String.valueOf(transactions.get(i).getDate());

            LocalDate localDate = LocalDate.parse(transactions1, dateFormatter);

            int transactionsMonth = localDate.getMonthValue();
            int transactionsYear = localDate.getYear();

            if (currentMonth == transactionsMonth && currentYear == transactionsYear){
                System.out.println(transactions.get(i));
            }

        }

    }

    public static void previousMonth() {

    }

    public static void yearDate() {

    }

    public static void previousYear() {

    }

    public static void searchbyVendor () {

        System.out.println(" Search by Vendor");
        System.out.println("Please provide the vendor you're looking for...");
        System.out.print("Vendor: ");
        String typeToSearch = inputScanner.nextLine();

        for (int i = 0; i < transactions.size(); i++) {
            TransactionRecord transaction1 = transactions.get(i);
            if (transaction1.getVendor().equalsIgnoreCase(typeToSearch)) {
                System.out.println(transaction1);
            }
        }
    }
    public static void previousScreen() {

    }

    public static void homePage() {

    }
}