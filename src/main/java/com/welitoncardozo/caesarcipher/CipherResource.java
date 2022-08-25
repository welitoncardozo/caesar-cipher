package com.welitoncardozo.caesarcipher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cipher")
public class CipherResource {
    @GetMapping("/encrypt")
    public String encrypt() {
        System.out.println("encrypt");
        return "encrypt";
    }

    @GetMapping("/decrypt")
    public String decrypt() {
        System.out.println("decrypt");
        return "decrypt";
    }
}
