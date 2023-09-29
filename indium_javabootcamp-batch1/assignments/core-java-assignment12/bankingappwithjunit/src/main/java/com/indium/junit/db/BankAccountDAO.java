package com.indium.junit.db;

import com.indium.junit.modal.BankAccount;
import com.indium.junit.services.BankingService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    public void saveToDatabase(String accountNumber, String accountHolderName, long balance, String accountType) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO BankAccounts VALUES (?, ?, ?, ?)")) {
            statement.setString(1, accountNumber);
            statement.setString(2, accountHolderName);
            statement.setLong(3, balance);
            statement.setString(4, accountType);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInDatabase(String accountNumber, String accountHolderName, long balance, String accountType) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE BankAccounts SET accountHolderName=?, balance=?, accountType=? WHERE accountNumber=?")) {
            statement.setString(1, accountHolderName);
            statement.setLong(2, balance);
            statement.setString(3, accountType);
            statement.setString(4, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(String accountNumber) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM BankAccounts WHERE accountNumber=?")) {
            statement.setString(1, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BankAccount getFromDatabase(String accountNumber) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM BankAccounts WHERE accountNumber = ?")) {
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String accountHolderName = resultSet.getString("accountHolderName");
                long balance = resultSet.getLong("balance");
                String accountType = resultSet.getString("accountType");
                return new BankAccount(accountNumber, accountHolderName, balance, accountType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BankAccount> getAllFromDatabase() {
        List<BankAccount> accounts = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM BankAccounts")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String accountNumber = resultSet.getString("accountNumber");
                String accountHolderName = resultSet.getString("accountHolderName");
                long balance = resultSet.getLong("balance");
                String accountType = resultSet.getString("accountType");
                accounts.add(new BankAccount(accountNumber, accountHolderName, balance, accountType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

}
