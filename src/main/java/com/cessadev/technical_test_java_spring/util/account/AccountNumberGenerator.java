package com.cessadev.technical_test_java_spring.util.account;

import java.util.UUID;

public class AccountNumberGenerator {

    public static String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" +
                uuid.substring(8, 12) + "-" + uuid.substring(12);
    }
}
