package com.welitoncardozo.caesarcipher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cipher")
public class CipherResource {
    private final CipherService cipherService;

    public CipherResource(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    @GetMapping("/encrypt")
    public String encrypt(@RequestParam final String message) {
        return cipherService.getEncrypt(message);
    }

    @GetMapping("/decrypt")
    public String decrypt(@RequestParam final String message) {
        return cipherService.getDecrypt(message);
    }
}
