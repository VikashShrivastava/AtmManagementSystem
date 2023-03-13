package com.pack;

public class User {
    int custId;
    String custName;
    int balance;
    int pin;

    public User(int custId, String custName, int balance, int pin) {
        this.custId = custId;
        this.custName = custName;
        this.balance = balance;
        this.pin = pin;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "User{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", balance=" + balance +
                ", pin=" + pin +
                '}';
    }
}
