package com.ps.data;

import com.ps.models.Search;
import com.ps.models.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDaoInt {

    private DataSource dataSource;

    public TransactionDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {
                Transaction transaction = mapTransaction(resultSet);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction getById(int id) {
        String query = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapTransaction(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> getCreditsOrDebits(boolean positive) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions " +
                       "WHERE amount ";
        if(positive) {
            query += "> 0";
        } else {
            query += "< 0";
        }

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while(resultSet.next()) {
                Transaction transaction = mapTransaction(resultSet);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getByMonth(int month, int year) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions " +
                       "WHERE MONTH(date) = ? AND YEAR(date) = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, month);
            statement.setInt(2, year);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = mapTransaction(resultSet);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getByYear(int year) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions " +
                       "WHERE YEAR(date) = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Transaction transaction = mapTransaction(resultSet);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction create(Transaction transaction) {
        String query = "INSERT INTO transactions (date,time,description,vendor,amount) " +
                       "VALUES (?,?,?,?,?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setDate(1, Date.valueOf(transaction.getDate()));
            statement.setTime(2, Time.valueOf(transaction.getTime()));
            statement.setString(3, transaction.getDescription());
            statement.setString(4, transaction.getVendor());
            statement.setDouble(5, transaction.getAmount());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int transactionId = generatedKeys.getInt(1);
                    return getById(transactionId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Transaction> customSearch(Search search) {
        return List.of();
    }

    protected Transaction mapTransaction(ResultSet resultSet) throws SQLException
    {
        int transactionId = resultSet.getInt("transaction_id");
        Date date = resultSet.getDate("date");
        Time time = resultSet.getTime("time");
        String description = resultSet.getString("description");
        String vendor = resultSet.getString("vendor");
        double amount = resultSet.getDouble("amount");

        Transaction transaction = new Transaction()
        {{
            setTransactionId(transactionId);
            setDate(date.toLocalDate());
            setTime(time.toLocalTime());
            setDescription(description);
            setVendor(vendor);
            setAmount(amount);
        }};

        return transaction;
    }
}
