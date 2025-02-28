package com.example.demo;

public class Account {
    int card_id;
    int account_number;
    int client_id;
    float credit;
    String validity;

    public Account() {}
    public Account(int account_number) {
        this.account_number = account_number;
    }
}
