package com.indium.junit.db;

import com.indium.junit.modal.BankAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BankAccountDAOTest {

    private BankAccountDAO bankAccountDAO;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        bankAccountDAO = new BankAccountDAO();
        createTable();
    }

    @After
    public void tearDown() throws SQLException {
        dropTable();
        connection.close();
    }

    private void createTable() throws SQLException {
        try (PreparedStatement checkStatement = connection.prepareStatement("SHOW TABLES LIKE 'BankAccounts'");
             ResultSet resultSet = checkStatement.executeQuery()) {

            if (resultSet.next()) {
                dropTable();
            }

            try (PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE BankAccounts " +
                    "(accountNumber VARCHAR(20), accountHolderName VARCHAR(255), balance BIGINT, accountType VARCHAR(50))")) {
                createStatement.executeUpdate();
            }
        }
    }


    private void dropTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DROP TABLE BankAccounts")) {
            statement.executeUpdate();
        }
    }

    @Test
    public void testSaveToDatabase() throws SQLException {
        String accountNumber = "123456";
        String accountHolderName = "John Doe";
        long balance = 1000L;
        String accountType = "Savings";

        bankAccountDAO.saveToDatabase(accountNumber, accountHolderName, balance, accountType);

        BankAccount account = bankAccountDAO.getFromDatabase(accountNumber);
        assertNotNull(account);
        assertEquals(accountNumber, account.getAccountNumber());
        assertEquals(accountHolderName, account.getAccountHolderName());
        assertEquals(balance, account.getBalance());
        assertEquals(accountType, account.getAccountType());
    }

    @Test
    public void testUpdateInDatabase() throws SQLException {
        String accountNumber = "123456";
        String accountHolderName = "John Doe";
        long balance = 2000L;
        String accountType = "Current";


        bankAccountDAO.saveToDatabase(accountNumber, accountHolderName, 1000L, "Savings");
        bankAccountDAO.updateInDatabase(accountNumber, accountHolderName, balance, accountType);
        
        BankAccount account = bankAccountDAO.getFromDatabase(accountNumber);
        assertNotNull(account);
        assertEquals(accountHolderName, account.getAccountHolderName());
        assertEquals(balance, account.getBalance());
        assertEquals(accountType, account.getAccountType());
    }

    @Test
    public void testDeleteFromDatabase() throws SQLException {

        String accountNumber = "123456";
        bankAccountDAO.saveToDatabase(accountNumber, "John Doe", 1000L, "Savings");
        bankAccountDAO.deleteFromDatabase(accountNumber);

        BankAccount account = bankAccountDAO.getFromDatabase(accountNumber);
        assertNull(account);
    }

    @Test
    public void testGetAllFromDatabase() throws SQLException {
        bankAccountDAO.saveToDatabase("123", "John Doe", 1000L, "Savings");
        bankAccountDAO.saveToDatabase("456", "Jane Smith", 1500L, "Checking");

        List<BankAccount> accounts = bankAccountDAO.getAllFromDatabase();

        assertEquals(2, accounts.size());
    }
}
