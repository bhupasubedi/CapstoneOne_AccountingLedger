package com.ps.data;

import com.ps.models.Search;
import com.ps.models.Transaction;

import java.util.List;

public interface TransactionDaoInt {
    List<Transaction> getAll();
    Transaction getById(int id);
    List<Transaction> getCreditsOrDebits(boolean positive);
    List<Transaction> getByMonth(int month, int year);
    List<Transaction> getByYear(int year);
    Transaction create (Transaction transaction);
    void delete(int id); // Deletes a task by id
    List<Transaction> customSearch (Search search);
}
