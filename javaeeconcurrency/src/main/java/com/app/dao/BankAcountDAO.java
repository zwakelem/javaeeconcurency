package com.app.dao;

import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class BankAcountDAO {

    private DataSource dataSource;

    public List<BankAccount> getAllBankAccounts() {

        List<BankAccount> accounts = new ArrayList<>();
        BankAccount bankAccount = null;

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bank_account");
            while(resultSet.next()) {
                bankAccount = new BankAccount();
                bankAccount.setAccNumber(resultSet.getInt("acc_number"));
                bankAccount.setName(resultSet.getString("name"));
                bankAccount.setEmail(resultSet.getString("email"));
                bankAccount.setAccType(resultSet.getString("acc_type"));
                accounts.add(bankAccount);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return accounts;
    }

    public List<BankAccountTransaction> getTransactionAccountsForAccount(BankAccount bankAccount) {

        BankAccountTransaction transaction = null;
        List<BankAccountTransaction> transactions = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from bank_account where acc_number=?");
            statement.setInt(1, bankAccount.getAccNumber());
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                transaction = new BankAccountTransaction();
                transaction.setAccNumber(set.getInt(""));
                transaction.setAmount(set.getDouble(""));
                transaction.setTxDate(new Date(set.getDate("transaction_date").getTime()));
                transaction.setTxId(set.getInt("tx_id"));
                transaction.setTxType(set.getString("transaction_type"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return transactions;
    }

}
