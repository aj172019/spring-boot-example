package com.megazone.springbootboilerplate.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@TestPropertySource(properties = {"jasypt.encryptor.password=1234"})
@SpringBootTest
public class JasyptTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @DisplayName("Jasypt 암호화 출력")
    @Test
    void encryptTest() {
        String encrypted = stringEncryptor.encrypt("sa");
        System.out.println("encrypted: " + encrypted);
    }
}
