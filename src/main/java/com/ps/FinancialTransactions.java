package com.ps;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FinancialTransactions {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<FinancialTransactions> transactions = new ArrayList<>();


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
        }
    }
//    // switch statement to match the user command to the provided cases
//            switch (mainMenuCommand){
//        switch (mainMenuCommand) {
//            case 1:
//               adddeposit();
//
//                System.out.println("Command not found, please try again");
//            }
//
//
//
//
//
//    public static void loadTransactions() {
//      FinancialTransactions transactions1 = new FinancialTransactions(2023-04-15,  10:13:25, "ergonomic keyboard","Amazon", -89.50) ;
//      allTrans.add(transactions1);
//    }
//    public static void  loadTransactionsFromFile(){
//
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new BufferedReader("transactions.csv"));
//
//            String firstline = bufferedReader.readLine();
//            String input;
//
//
//
//            while(input = bufferedReader.readLine()) ! = null){
//            String [] trans = input.split("\\/"); // "2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50" >> ["vendor", "description",...]
//
//                LocalDate date = trans[0];
//                LocalTime time=  trans[1];
//                 String description
//                 String vendor;
//                double amount;
//
//
//              allTrans.add(new FinancialTransactions(date, time, description, vendor, amount));
//
//            }
//
//
//
//        } catch ( Exception e){
//
//        }
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
