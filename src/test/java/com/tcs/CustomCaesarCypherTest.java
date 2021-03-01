package com.tcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomCaesarCypherTest {

    @Test
    void shouldDecryptUpperCaseMessage(){
        String cipherText = "Cqrb rb j CNBC vnbbjpn948";

        String expectedMessage = "This is a TEST message059";
        String actualMessage = CustomCaesarCypher.decrypt(cipherText, 9);

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowAnErrorWhenKeyIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> CustomCaesarCypher.decrypt("", 0));
    }

}