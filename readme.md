# Accounting Ledger Application

## Description: 
The Financial Transactions Management System is a Java-based application designed to help users track their financial transactions, 
including deposits and payments. Users can easily input transaction details, view their transaction ledger, and generate reports 
based on specific criteria such as date and vendor. The application can load and save transactions using a CSV file, which keeps 
the data saved for future use.

## Features
Add Deposits and Payments: Users can add new financial transactions, which are stored in a CSV file.

View Ledger: Users can view all transactions, only deposits, or only negative transactions.

Reporting: Generate reports for specific time periods (month-to-date, previous month, etc.) and search by vendor.

## Home menu
The  method allows the user to input a deposit transaction by entering the date, time, description,
vendor, and amount. This information is then saved to a list and appended to a CSV file for future reference.
![addingDeposit.png](photos%2FaddingDeposit.png)![12list.png](photos%2F12list.png)![afterAddingtheDeposit.png](photos%2FafterAddingtheDeposit.png)


## submenu
The submenu lets users choose what they want to do with their transactions, like viewing all entries, seeing only deposits,
or checking negative amounts. It keeps asking for input until the user decides to go back to the main menu.

![Ledger1.png](photos%2FLedger1.png)![Ledger2.png](photos%2FLedger2.png)![Ledger3.png](photos%2FLedger3.png)
![report4.png](photos%2Freport4.png)

## Report
The reports section provides users with a dedicated screen to access various pre-defined reports or conduct 
custom searches on their transactions. Users can view transactions for the current month, the previous month, 
the current year, or the previous year, allowing for easy tracking and analysis of their financial activities over time.
![report4.png](photos%2Freport4.png) ![monthtodate.png](photos%2Fmonthtodate.png)![previousmonth.png](photos%2Fpreviousmonth.png)
![searchbyVendor.png](photos%2FsearchbyVendor.png)![backMenu.png](photos%2FbackMenu.png)

## Interesting code
An interesting part of the project is the code that saves transactions to a CSV file. It uses a FileWriter to write
each transaction's details, making it easy to keep track of finances.
![last.png](photos%2Flast.png)