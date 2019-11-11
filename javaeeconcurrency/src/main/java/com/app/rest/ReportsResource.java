package com.app.rest;

import com.app.dao.BankAcountDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.Path;
import java.beans.PropertyVetoException;

@Path("/reports")
public class ReportsResource {

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService service;

    private BankAcountDAO dao;

    public ReportsResource() {

        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("password");
            dao = new BankAcountDAO(dataSource);
        } catch (PropertyVetoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
