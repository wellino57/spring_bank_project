package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandling {
    DbRepository dr
    public RequestHandling(DbRepository dr) {
        this.dr = dr;
    }

    @PostMapping("/transaction")
    public String transaction() {
        dr.payment
    }
}
