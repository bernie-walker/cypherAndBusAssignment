package com.tcs.customcaesercypher;

import com.tcs.customcasesarcypher.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void shouldDecryptUpperCaseMessage(){
        String cipherText = "Cqrb rb j CNBC vnbbjpn948";

        String expectedMessage = "This is a TEST message059";
        String actualMessage = Main.decrypt(cipherText, 9);

        assertEquals(expectedMessage, actualMessage);
    }

}