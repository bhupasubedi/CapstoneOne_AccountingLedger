package com.ps;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




public class FinanicalTransactions {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<FinanicalTransactions> transactionsArrayList = new ArrayList<>();


    public static void main(String[] args) {
        loadtransactionsArrayList();
        loadtransactionsArrayListFromFile();
    }

}








