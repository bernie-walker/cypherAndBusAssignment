package com.tcs;

public class CustomCaesarCypher {

    public static String decrypt(String cypherText, int key) {

        if (key <= 0) {
            throw new IllegalArgumentException("Value of key must be greater that 0");
        }

        StringBuilder decodedMessage = new StringBuilder();

        for (char character : cypherText.toCharArray()) {
            char decodedCharacter = character == ' ' ? character : decodeCharacter(character, key);
            decodedMessage.append(decodedCharacter);
        }

        return decodedMessage.toString();
    }

    private static char decodeCharacter(char character, int shift) {

        if (character >= 65 && character <= 90) {
            return getOriginalCharacter(character, shift, 65, 26);
        }

        if (character >= 97 && character <= 122) {
            return getOriginalCharacter(character, shift, 97, 26);
        }

        return getOriginalCharacter(character, shift, 48, 10);
    }

    private static char getOriginalCharacter(char character, int shift, int lowestCharCodeInCharGroup, int totalCharsInGroup) {
        int characterPosition = character - lowestCharCodeInCharGroup;
        int originalPosition = modulo((characterPosition - shift), totalCharsInGroup);
        return (char) (originalPosition + lowestCharCodeInCharGroup);
    }

    private static int modulo(int num, int mod) {
        int result = num % mod;
        return result < 0 ? mod + result : result;
    }
}
