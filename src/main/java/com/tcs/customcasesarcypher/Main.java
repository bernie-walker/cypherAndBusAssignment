package com.tcs.customcasesarcypher;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the message to de-cypher : ");
        String message = scanner.nextLine();

        System.out.println("Insert the key : ");
        int key = scanner.nextInt();

        if (key <= 0) {
            System.out.println("INVALID KEY");
        } else {
            String result = decrypt(message, key);
            System.out.printf("Decoded message is : %s\n", result);
        }
    }

    public static String decrypt(String message, int key) {
        StringBuilder decodedMessage = new StringBuilder();
        int messageLength = message.length();

        for (int i = 0; i < messageLength; i++) {
            char character = message.charAt(i);

            if (character == ' ') {
                decodedMessage.append(' ');
            } else {
                char decodedCharacter = decodeCharacter(character, key);
                decodedMessage.append(decodedCharacter);
            }
        }

        return decodedMessage.toString();
    }

    // checks if the character is number or upperCase or lowerCase, and decodes it accordingly
    // a char can  also be treated as int, because it's just a number value in memory
    // number value of each character groups :
    //      0 - 9 (48 - 57)
    //      A - Z (65 - 90)
    //      a - z (97 - 122)
    public static char decodeCharacter(char character, int shift) {

        if (character >= 48 && character <= 57) {
            return getOriginalCharacter(character, shift, 48, 10);
        }

        if (character >= 65 && character <= 90) {
            return getOriginalCharacter(character, shift, 65, 26);
        }

        return getOriginalCharacter(character, shift, 97, 26);
    }

    public static char getOriginalCharacter(char character, int shift, int lowestCharCodeInCharGroup, int totalCharsInGroup) {
        // decides the position of the character in it's character group.
        // eg: - C whose number value is 67, comes in the second position(0 is the first position) in the character group A - Z.
        //       67 - 65 = 2
        int characterPosition = character - lowestCharCodeInCharGroup;

        // shifts the position of the character by the value of key.
        // eg:- if character is C(67) whose position is 2 AND
        //      the key is 3
        //      then the original character's position was =>
        //      2 shiftLeft 3 = 25; i.e. character Z
        int originalPosition = modulo((characterPosition - shift), totalCharsInGroup);

        // transform the position to actual number value of the character, and coverts it to character.
        // eg:- 25 + 65 = 90;
        return (char) (originalPosition + lowestCharCodeInCharGroup);
    }

    public static int modulo(int num, int mod) {
        int result = num % mod;
        return result < 0 ? mod + result : result;
    }
}
