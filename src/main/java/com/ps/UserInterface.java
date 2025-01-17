package com.ps;

import com.ps.data.TransactionDaoImpl;
import com.ps.enums.LedgerMenuOption;
import com.ps.enums.MainMenuOption;
import com.ps.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static BasicDataSource dataSource = new BasicDataSource();
    private static TransactionDaoImpl dao = new TransactionDaoImpl(dataSource);

    static Scanner commandInput = new Scanner(System.in);
    static Scanner dataInput = new Scanner(System.in);

    public static void mainMenu(String username, String password) throws InputMismatchException {
        try {
            dataSource.setUrl("jdbc:mysql://localhost:3306/ledger_db");
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            System.exit(1);
        } catch (NullPointerException e) {
            System.err.println("Credentials incorrect or not provided: " + e.getMessage());
            System.exit(1);
        }

        int mainMenuCommand;
        do {
            System.out.println("Please select among the following menu options: ");
            for (MainMenuOption option : MainMenuOption.values()) {
                System.out.printf("(%d) %s%n", option.getCode(), option.getDescription());
            }
            System.out.print("Your selection: ");
            try {
                mainMenuCommand = commandInput.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException();
            }

            MainMenuOption selection = MainMenuOption.fromCode(mainMenuCommand);

            if (selection == null) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (selection) {
                case ADD_DEPOSIT:
                    handleAddDeposit();
                    break;
                case MAKE_PAYMENT:
                    handleMakePayment();
                    break;
                case LEDGER:
                    ledgerMenu();
                case EXIT:
                    System.out.println("Exiting...");
                    break;
            }
        } while (mainMenuCommand != 0);
    }

    private static void handleAddDeposit() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);
        System.out.printf("Current date and time will be recorded: %s | %s\n", date, time);
        System.out.println("Type \"cancel\" at any time to go back.");

        System.out.println("Enter a description: ");
        String description = dataInput.nextLine();
        if (cancelInput(description)) return;

        System.out.println("Enter a vendor: ");
        String vendor = dataInput.nextLine();
        if (cancelInput(vendor)) return;

        System.out.println("Enter an amount: ");
        String amount = dataInput.nextLine();
        if (cancelInput(amount)) return;
        double parsedAmount = Double.parseDouble(amount);

        dao.create(new Transaction(date,time,description,vendor,parsedAmount));
        System.out.println("Transaction has been recorded successfully!");
    }

    private static void handleMakePayment() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);
        System.out.printf("Current date and time will be recorded: %s | %s\n", date, time);

        System.out.println("Enter a description: ");
        String description = dataInput.nextLine();
        System.out.println("Enter a vendor: ");
        String vendor = dataInput.nextLine();
        System.out.println("Enter an amount: ");
        double amount = -Math.abs(dataInput.nextDouble());

        dao.create(new Transaction(date,time,description,vendor,amount));
        System.out.println("Transaction has been recorded successfully!");
    }

    private static void ledgerMenu() {
        int ledgerMenuCommand;
        commandInput.nextLine();
        do {
            System.out.println("Please select among the following menu options: ");
            for (LedgerMenuOption option : LedgerMenuOption.values()) {
                System.out.printf("(%d) %s%n", option.getCode(), option.getDescription());
            }
            System.out.print("Your selection: ");
            ledgerMenuCommand = commandInput.nextInt();

            LedgerMenuOption selection = LedgerMenuOption.fromCode(ledgerMenuCommand);

            if (selection == null) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (selection) {
                case LIST_ALL:
                    handleGetAll();
                    break;
                case LIST_CREDITS:
                    handleGetCredits();
                    break;
                case LIST_DEBITS:
                    handleGetDebits();
                    break;
                case SEARCH_BY_ID:
                    handleSearchById();
                    break;
                case MONTH_TO_DATE:
                    handleMonthToDate();
                    break;
                case PREVIOUS_MONTH:
                    handlePreviousMonth();
                    break;
                case YEAR_TO_DATE:
                    handleYearToDate();
                    break;
                case PREVIOUS_YEAR:
                    handlePreviousYear();
                    break;
                case CUSTOM_SEARCH:
                    handleCustomSearch();
                    break;
                case BACK:
                    return;
                default:
                    System.out.println("Invalid input, try again.");
            }
        } while (ledgerMenuCommand != 0);
    }

    private static void handleGetDebits() {
        List<Transaction> transactions = dao.getCreditsOrDebits(false);
        printFormattedTable(transactions);
    }

    private static void handleGetCredits() {
        List<Transaction> transactions = dao.getCreditsOrDebits(true);
        printFormattedTable(transactions);
    }

    private static void handleGetAll() {
        List<Transaction> transactions = dao.getAll();
        printFormattedTable(transactions);
    }

    private static void handleSearchById() {
        System.out.print("Enter a transaction ID: ");
        int id = dataInput.nextInt();
        Transaction transaction = dao.getById(id);
        printFormattedTable(transaction);
    }

    private static void handleMonthToDate() {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        List<Transaction> transactions = dao.getByMonth(month, year);
        printFormattedTable(transactions);
    }

    private static void handlePreviousMonth() {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        if (month == 1) {
            month = 12;
            year -= 1;
        } else {
            month -= 1;
        }
        List<Transaction> transactions = dao.getByMonth(month, year);
        printFormattedTable(transactions);
    }

    private static void handleYearToDate() {
        int year = LocalDate.now().getYear();
        List<Transaction> transactions = dao.getByYear(year);
        printFormattedTable(transactions);
    }

    private static void handlePreviousYear() {
        int year = LocalDate.now().getYear() -1;
        List<Transaction> transactions = dao.getByYear(year);
        printFormattedTable(transactions);
    }

    private static void handleCustomSearch() {
        System.out.println("");
    }

    private static void printFormattedTable(List<Transaction> transactions) {
        System.out.print("""
                 ID    Date          Time       Description                   Vendor             Amount
                ----- ------------- ---------- ----------------------------- ------------------ ----------
                """);

        for (Transaction transaction : transactions) {
            System.out.printf("%-6d %-13s %-10s %-29s %-18s $%-9.2f%n",
                    transaction.getTransactionId(),
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }
    }

    private static void printFormattedTable(Transaction transaction) {
        System.out.print("""
                 ID    Date          Time       Description                   Vendor             Amount
                ----- ------------- ---------- ----------------------------- ------------------ ----------
                """);

            System.out.printf("%-6d %-13s %-10s %-29s %-18s $%-9.2f%n",
                    transaction.getTransactionId(),
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
    }

    private static boolean cancelInput(String input) {
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Going back...");
            return true;
        } else {
            return false;
        }
    }
}