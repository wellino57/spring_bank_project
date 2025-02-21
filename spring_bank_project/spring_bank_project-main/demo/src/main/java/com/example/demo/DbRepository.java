package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;

@Repository
public class DbRepository {
    JdbcTemplate jt;
    public DbRepository(JdbcTemplate jt) {this.jt = jt;}

    private Account getCardOwner(Transaction t) {
        return jt.queryForObject(
                "SELECT account_number FROM cards WHERE card_number = "+t.card_number+" AND cvc = "+t.cvc+" AND pin = "+t.pin,
                BeanPropertyRowMapper.newInstance(Account.class)
        );
    }

    public int payment(Transaction transaction) {
        Account giver = getCardOwner(transaction);
        if(giver != null) {
            return jt.update("INSERT INTO transactions VALUES (NULL,?,?,transfer,?,NULL,?)",
                    giver.account_number,
                    transaction.getReceiver_id(),
                    transaction.getSum(),
                    transaction.getDescription());
        }
        return 0;
    }
}
