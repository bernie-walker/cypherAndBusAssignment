import com.sun.tools.corba.se.idl.InvalidArgument;

public class CustomCaesarCypher {

    public static String decrypt(String cipherText, int key) throws InvalidArgument {

        if (key <= 0) {
            throw new InvalidArgument("Value of key must be greater that 0");
        }

        StringBuilder decodedMessage = new StringBuilder();

        for (char character : cipherText.toCharArray()) {
            char decodedCharacter = character == ' ' ? character : decodeCharacter(character, key);
            decodedMessage.append(decodedCharacter);
        }

        return decodedMessage.toString();
    }

    private static char decodeCharacter(char character, int shift) {

        if (AlphaNumeric.isUpperCase(character)) {
            return getOriginalCharacter(character, shift, AlphaNumeric::transformUppercase, AlphaNumeric.TOTAL_ALPHABETS);
        }

        if (AlphaNumeric.isLowerCase(character)) {
            return getOriginalCharacter(character, shift, AlphaNumeric::transformLowercase, AlphaNumeric.TOTAL_ALPHABETS);
        }

        return getOriginalCharacter(character, shift, AlphaNumeric::transformNumeric, AlphaNumeric.TOTAL_NUMBERS);
    }

    private static char getOriginalCharacter(char character, int shift, AlphanumericTransformer transformer, int totalCharsInGroup) {
        int characterPosition = transformer.transform(character, false);
        int originalPosition = modulo((characterPosition - shift), totalCharsInGroup);
        return (char) transformer.transform(originalPosition, true);
    }

    private static int modulo(int num, int mod) {
        int result = num % mod;
        return result < 0 ? mod + result : result;
    }
}

class AlphaNumeric {

    public static final int A = 65, Z = 90, a = 97, z = 122, ZERO = 48, TOTAL_ALPHABETS = 26, TOTAL_NUMBERS = 10;

    public static boolean isUpperCase(char character) {
        return isInRange(character, A, Z);
    }

    public static boolean isLowerCase(char character) {
        return isInRange(character, a, z);
    }

    private static boolean isInRange(int num, int lowerLimit, int upperLimit) {
        return Math.min(Math.max(num, lowerLimit), upperLimit) == num;
    }

    public static int transformUppercase(int value, boolean isValuePosition) {
        return isValuePosition ? value + A : value - A;
    }

    public static int transformLowercase(int value, boolean isValuePosition) {
        return isValuePosition ? value + a : value - a;
    }

    public static int transformNumeric(int value, boolean isValuePosition) {
        return isValuePosition ? value + ZERO : value - ZERO;
    }
}

interface AlphanumericTransformer {
    int transform(int value, boolean isValuePosition);
}

