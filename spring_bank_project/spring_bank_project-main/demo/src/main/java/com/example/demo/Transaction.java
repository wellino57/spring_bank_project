package com.example.demo;

public class Transaction {
    public String getCard_number() {
        return card_number;
    }

    public short getPin() {
        return pin;
    }

    public short getCvc() {
        return cvc;
    }

    public float getSum() {
        return sum;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public String getDescription() {
        return description;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setPin(short pin) {
        this.pin = pin;
    }

    public void setCvc(short cvc) {
        this.cvc = cvc;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String card_number;
    short pin;
    short cvc;
    float sum;
    int receiver_id;
    String description;
}
