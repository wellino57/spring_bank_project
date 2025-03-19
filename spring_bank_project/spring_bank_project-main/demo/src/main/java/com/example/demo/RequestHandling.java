package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class RequestHandling {
    DbRepository dr;
    private final ConcurrentHashMap<String, Integer> blikCodes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> blikTimestamps = new ConcurrentHashMap<>();
    private static final long BLIK_VALIDITY_DURATION = 120000;
    public RequestHandling(DbRepository dr) {
        this.dr = dr;
    }

    @PostMapping("/transaction")
    public int transaction(@RequestBody Transaction t) {
        return dr.payment(t);
    }
    @PostMapping("/transaction1")
    public int transaction() {
        return 222;
    }

    @PostMapping("/generate-blik/{cardNumber}")
    public int generateBlik(@PathVariable String cardNumber) {
        int blikCode = new Random().nextInt(900000) + 100000;
        blikCodes.put(cardNumber, blikCode);
        blikTimestamps.put(cardNumber, System.currentTimeMillis());
        return blikCode;
    }

    @PostMapping("/confirm-blik")
    public String confirmBlik(@RequestParam String cardNumber, @RequestParam int blikCode, @RequestParam int receiverId, @RequestParam float sum) {
        Long timestamp = blikTimestamps.get(cardNumber);
        if (timestamp == null || System.currentTimeMillis() - timestamp > BLIK_VALIDITY_DURATION) {
            blikCodes.remove(cardNumber);
            blikTimestamps.remove(cardNumber);
            return "BLIK Code Expired";
        }

        if (blikCodes.containsKey(cardNumber) && blikCodes.get(cardNumber) == blikCode) {
            blikCodes.remove(cardNumber);
            blikTimestamps.remove(cardNumber);
            Transaction transaction = new Transaction();
            transaction.setCard_number(cardNumber);
            transaction.setReceiver_id(receiverId);
            transaction.setSum(sum);
            transaction.setDescription("BLIK Payment");
            int result = dr.payment(transaction);
            return result > 0 ? "Transaction Successful" : "Transaction Failed";
        }
        return "Invalid BLIK Code";
    }
}
