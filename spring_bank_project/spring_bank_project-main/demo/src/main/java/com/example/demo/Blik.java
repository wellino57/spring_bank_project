package com.example.demo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Blik {
    public int getBlik_id() {
        return blik_id;
    }

    public void setBlik_id(int blik_id) {
        this.blik_id = blik_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getBlik_code() {
        return blik_code;
    }

    public void setBlik_code(int blik_code) {
        this.blik_code = blik_code;
    }

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public int blik_id;
    public int card_id;
    public Date expiration;
    public int blik_code;
    public String requested;

    public Blik() {}

    public Blik(int card_id, int blik_code) {
        this.blik_id = 0;
        this.card_id = card_id;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);
        this.expiration = calendar.getTime();

        this.blik_code = blik_code;
        this.requested = "";
    }
}
