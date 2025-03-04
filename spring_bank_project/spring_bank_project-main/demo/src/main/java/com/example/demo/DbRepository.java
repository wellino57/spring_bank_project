package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;

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
}
