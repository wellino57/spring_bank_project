package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandling {
    DbRepository dr;
    public RequestHandling(DbRepository dr) {
        this.dr = dr;
    }

    @PostMapping("/transaction")
    public int transaction(@RequestBody Transaction t) {
        return dr.payment(t);
    }
}
