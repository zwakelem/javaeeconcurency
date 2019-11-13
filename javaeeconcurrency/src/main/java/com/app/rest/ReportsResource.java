package com.app.rest;

import com.app.beans.BankAccount;
import com.app.dao.BankAccountDAO;
import com.app.runnables.ReportsProcessor;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/reports")
public class ReportsResource {

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService service;

    private BankAccountDAO dao;

    public ReportsResource() {

        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("password");
            dao = new BankAccountDAO(dataSource);
        } catch (PropertyVetoException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GET
    public String generateReports() {
        List<BankAccount> accounts = dao.getAllBankAccounts();
        accounts.forEach( account -> {
            try {
                Future<Boolean> future = service.submit(new ReportsProcessor(account, dao));
                System.out.println("reports generated? " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return "Report generation tasks submitted";
    }
}
