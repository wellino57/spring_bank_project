package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Repository
public class DbRepository {
    JdbcTemplate jt;
    public DbRepository(JdbcTemplate jt) {this.jt = jt;}

    public Account getCardOwner(Transaction t) {
        String sql = "SELECT * FROM cards WHERE card_number = ? AND pin = ? AND cvc = ?";

        return jt.queryForObject(
                sql,
                BeanPropertyRowMapper.newInstance(Account.class),
                t.card_number, t.pin, t.cvc
        );
    }

    public int payment(Transaction transaction) {
        Account giver = getCardOwner(transaction);
        if(giver != null) {
            System.out.println("Numer konta "+giver.account_number);
            return jt.update("INSERT INTO transactions(giver_account_number,receiver_account_number,transaction_type,amount,description) VALUES (?,?,'transfer',?,?)",
                    giver.account_number,
                    transaction.getReceiver_id(),
                    transaction.getSum(),
                    transaction.getDescription());
        }
        return 0;
    }

    public Blik generateBlik(int cardId) {
        Date expiresAt = Date.from(Instant.now().plusSeconds(120));
        int blikCode = new Random().nextInt(900000) + 100000;
        jt.update("INSERT INTO blik(card_id,expiration,blik_code) VALUES (?,?,?)",
                cardId,
                expiresAt,
                blikCode);
        return new Blik(cardId,expiresAt,blikCode);
    }

    public Blik updateBlik(int cardId) {
        Date expiresAt = Date.from(Instant.now().plusSeconds(120));
        int blikCode = new Random().nextInt(900000) + 100000;
        jt.update("UPDATE blik SET expiration = ?, blik_code = ? WHERE card_id = ?",
                expiresAt,
                blikCode,
                cardId);
        return new Blik(cardId,expiresAt,blikCode);
    }

    public Blik checkBlik(int cardId) {
        Blik blikData = jt.queryForObject("SELECT * FROM blik WHERE card_id = ?", BeanPropertyRowMapper.newInstance(Blik.class),cardId);
        return blikData;
    }
}
