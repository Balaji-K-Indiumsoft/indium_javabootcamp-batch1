
CREATE TABLE IF NOT EXISTS BankAccounts (
    accountNumber VARCHAR(20) PRIMARY KEY,
    accountHolderName VARCHAR(100),
    balance BIGINT,
    accountType VARCHAR(20)
);
create database banking_app_db;
show databases;
use banking_app_db;
show tables;
select * from BankAccounts;

INSERT INTO BankAccounts (accountNumber, accountHolderName, balance, accountType) VALUES (?, ?, ?, ?);
SELECT * FROM BankAccounts WHERE accountNumber = ?;
UPDATE BankAccounts SET accountHolderName = ? WHERE accountNumber = ?;
DELETE FROM BankAccounts WHERE accountNumber = ?;
SELECT COUNT(*) FROM BankAccounts WHERE balance > ?;
SELECT accountType, COUNT(*) FROM BankAccounts GROUP BY accountType;
SELECT accountType, COUNT(*) FROM BankAccounts GROUP BY accountType ORDER BY accountType;
SELECT accountType, AVG(balance) FROM BankAccounts GROUP BY accountType;
SELECT accountNumber FROM BankAccounts WHERE accountHolderName LIKE ?;
