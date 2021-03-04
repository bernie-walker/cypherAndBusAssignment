package com.tcs.busfair;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] busStops = new String[]{"TH", "GA", "IC", "HA", "TE", "LU", "NI", "CA"};
    static int[] fair = new int[]{800, 600, 750, 900, 1400, 1200, 1100, 1500};

    static double fairPerMetre = 0.005;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("List of bus stops ==> %s\n", Arrays.toString(busStops));

        System.out.println("Enter the source station : ");
        String source = scanner.nextLine();

        System.out.println("Enter the destination station : ");
        String destination = scanner.nextLine();

        if (!isValidInput(source, destination)) {
            System.out.println("INVALID INPUT");
        } else {
            System.out.printf("Total fair : %s\n", getFair(source, destination));
        }
    }

    private static boolean isValidInput(String source, String destination) {
        return !source.equals(destination) && areValidStops(source, destination);
    }

    private static boolean areValidStops(String source, String destination) {
        boolean isValidSource = false;
        boolean isValidDestination = false;

        for (int i = 0; i < busStops.length; i++) {
            if (busStops[i].equals(source)) {
                isValidSource = true;
            }

            if (busStops[i].equals(destination)) {
                isValidDestination = true;
            }
        }

        return isValidSource && isValidDestination;
    }

    public static int getFair(String source, String destination) {
        int sourceIndex = 0, destinationIndex = 0;

        for (int i = 0; i < busStops.length; i++) {
            if (busStops[i].equals(source)) {
                sourceIndex = i;
            }

            if (busStops[i].equals(destination)) {
                destinationIndex = i;
            }
        }

        int totalDistance = getTotalDistance(sourceIndex, destinationIndex);

        return (int) Math.ceil(totalDistance * fairPerMetre);
    }

    private static int getTotalDistance(int sourceIndex, int destinationIndex) {
        int totalDistance = 0;

        for (int i = sourceIndex + 1; i != destinationIndex + 1; i = ((i + 1) % busStops.length)) {
            totalDistance += fair[i];
        }

        return totalDistance;
    }
}
