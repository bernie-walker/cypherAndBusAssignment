package com.tcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusFairTest {

    @Test
    void shouldCalculateTheFair() {
        int fair = BusFair.getFair("GA", "TE");
        assertEquals(fair, 12);
    }

    @Test
    void shouldThrowAnExceptionWhenSourceAndDestinationAreTheSame() {
        assertThrows(IllegalArgumentException.class, () -> BusFair.getFair("GA", "GA"));
    }

    @Test
    void shouldThrowAnExceptionWhenGivenUnknownBusStops() {
        assertThrows(IllegalArgumentException.class, () -> BusFair.getFair("WO", "YE"));
    }
}