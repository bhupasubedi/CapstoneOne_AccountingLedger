package com.ps.data;

import com.ps.models.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ArrayListToFile {

    private static final String RECEIPT_FOLDER = "history/";

    public static void createFile(List<Transaction> transactions) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String fileName = RECEIPT_FOLDER + dateTime + ".txt";
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            bufferedWriter.write("""
                 ID    Date          Time       Description                   Vendor             Amount
                ----- ------------- ---------- ----------------------------- ------------------ ----------
                """);

            for (Transaction transaction : transactions) {
                bufferedWriter.write(String.format("%-6d %-13s %-10s %-29s %-18s $%-9.2f%n",
                        transaction.getTransactionId(),
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//        String Sql = "SELECT * FROM transactions_db";
//
//        try (Connection connection = getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(Sql);
//            ResultSet resultSet = statement.executeQuery();
//            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/transactions.csv"));
//
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//
//            for (int i = 1; i <= columnCount; i++) {
//                writer.write(metaData.getColumnLabel(i));
//                if (i < columnCount) {
//                    writer.write(",");
//                }
//            }
//            writer.newLine(); // Move to the next line after headers
//            // Write the rows of data
//            while (resultSet.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    writer.write(resultSet.getString(i));
//                    if (i < columnCount) {
//                        writer.write(",");
//                    }
//                }
//                writer.newLine(); // Move to the next line after each row
//            }
//            System.out.println("Data successfully written to file.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}