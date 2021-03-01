package com.tcs;

import java.util.Arrays;
import java.util.List;

public class BusFair {
    private static final List<Integer> fair = Arrays.asList(800, 600, 750, 900, 1400, 1200, 1100, 1500);
    private static final List<String> busStops = Arrays.asList("TH", "GA", "IC", "HA", "TE", "LU", "NI", "CA");
    private static final double fairPerMetre = 0.005;

    public static int getFair(String source, String destination) {

        if (source.equals(destination) || !areValidStops(source, destination)) {
            throw new IllegalArgumentException();
        }

        int sourcePosition = busStops.indexOf(source);
        int totalDistance = getTotalDistance(destination, sourcePosition, 0);

        return (int) Math.ceil(totalDistance * fairPerMetre);
    }

    private static boolean areValidStops(String source, String destination) {
        return busStops.contains(source) && busStops.contains(destination);
    }

    private static int getTotalDistance(String destination, int sourcePosition, int totalFair) {
        int nextStopPosition = (sourcePosition + 1) % 8;
        String nextStop = busStops.get(nextStopPosition);
        Integer fairToNextStop = fair.get(nextStopPosition);

        if (nextStop.equals(destination)) {
            return totalFair + fairToNextStop;
        }

        return getTotalDistance(destination, nextStopPosition, fairToNextStop);
    }
}
