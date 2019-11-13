package com.app.runnables;

import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;
import com.app.dao.BankAccountDAO;
import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class ReportsProcessor implements Callable<Boolean> {

    private BankAccount bankAccount;

    private BankAccountDAO dao;

    @Override
    public Boolean call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " generating report");
        boolean reportGenerated = false;
        List<BankAccountTransaction> transactions = dao.getTransactionAccountsForAccount(bankAccount);
        File file = new File("C:\\reports\\"
                +bankAccount.getAccNumber()+"tx_report.txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(BankAccountTransaction tx: transactions) {
                writer.write("Account Number: " + tx.getAccNumber());
                writer.write("Transaction type: " + tx.getTxType());
                writer.write("Transaction id: " + tx.getTxId());
                writer.write("Amount: " + tx.getAmount());
                writer.write("Transaction Date: " + tx.getTxDate());
                writer.newLine();
            }
            writer.flush();
            reportGenerated = true;
        }
        return reportGenerated;
    }
}
