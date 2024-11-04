package com.cessadev.technical_test_java_spring.service.implementation;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl {

    public String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" +
                uuid.substring(8, 12) + "-" + uuid.substring(12);
    }

}
