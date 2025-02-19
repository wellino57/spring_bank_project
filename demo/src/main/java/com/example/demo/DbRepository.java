package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.smartcardio.Card;

public class DbRepository {
    JdbcTemplate jt;
    public DbRepository(JdbcTemplate jt) {this.jt = jt;}

    private Integer getCardOwner(String card_num) {
        return jt.queryForObject(
                "SELECT account_number FROM cards WHERE card_number = "+card_num,
                BeanPropertyRowMapper.newInstance(Integer.class)
        );
    }

    public int payment(Transaction transaction) {
        if(getCardOwner(transaction.card_number) != null) {
            return jt.update("INSERT INTO transactions VALUES (NULL,)")
        }
    }
}
