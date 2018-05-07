package ru.vsu.entity;

public class CashDeskEntity {
    private static CashDeskEntity instance;

    private double balance = 100000;

    private CashDeskEntity() {
    }

    public static CashDeskEntity getInstance() {
        if (instance == null) {
            instance = new CashDeskEntity();
        }
        return instance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
