package com.indium.bankingapp.service;

import com.indium.bankingapp.model.BankAccount;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DataHandler {
    private static final String EXPORT_FILE_PATH = "export.txt";
    private static final String IMPORT_FILE_PATH = "import.txt";
    private static final int NUM_THREADS = 2;

    private static ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
    private static AtomicInteger exportThreadCount = new AtomicInteger(0);
    private static AtomicInteger importThreadCount = new AtomicInteger(0);

    public static void exportAccounts(List<BankAccount> accounts) {
        System.out.println("Exporting accounts...");

        executorService.execute(() -> {
            System.out.println("Export thread started.");
            exportThreadCount.incrementAndGet();

            try (FileWriter writer = new FileWriter(EXPORT_FILE_PATH)) {
                for (BankAccount account : accounts) {
                    String accountDetails = account.toString();
                    writer.write(accountDetails + "\n");
                }
                System.out.println("Accounts exported successfully to " + EXPORT_FILE_PATH);
                System.out.println("Export threads used/executed: " + exportThreadCount.get());
            } catch (IOException e) {
                System.out.println("Error exporting accounts to file: " + e.getMessage());
            } finally {
                exportThreadCount.decrementAndGet();
                System.out.println("Export thread finished.");
            }
        });
    }

    public static void importAccounts(BankingService bankingService) {
        System.out.println("Importing accounts...");

        executorService.execute(() -> {
            System.out.println("Import thread started.");
            importThreadCount.incrementAndGet();

            try (FileReader reader = new FileReader(IMPORT_FILE_PATH);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] accountInfo = line.split(", ");
                    if (accountInfo.length == 4) {
                        String accountNumber = accountInfo[0];
                        String accountHolderName = accountInfo[1];
                        double balance = Double.parseDouble(accountInfo[2]);
                        String accountType = accountInfo[3];

                        BankAccount account = new BankAccount(accountNumber, accountHolderName, balance, accountType);
                        bankingService.addAccount(account, accountType);
                    }
                }
                System.out.println("Accounts imported successfully from " + IMPORT_FILE_PATH);
                System.out.println("Import threads used/executed: " + importThreadCount.get());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error importing accounts from file: " + e.getMessage());
            } finally {
                importThreadCount.decrementAndGet();
                System.out.println("Import thread finished.");
            }
        });
    }

    public static void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted during shutdown: " + e.getMessage());
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads have finished.");
    }
}
