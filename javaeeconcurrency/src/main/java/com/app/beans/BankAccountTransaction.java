package com.app.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BankAccountTransaction {

    private int accNumber;
    private double amount;
    private Date txDate;
    private String tsType;
    private int txId;

}
