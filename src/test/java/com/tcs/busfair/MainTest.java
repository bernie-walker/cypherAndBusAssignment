package com.tcs.busfair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void shouldCalculateTheFair() {
        int fair = Main.getFair("GA", "TE");
        assertEquals(fair, 16);
    }

}