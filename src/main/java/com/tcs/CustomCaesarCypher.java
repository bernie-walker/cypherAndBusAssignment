import com.sun.tools.corba.se.idl.InvalidArgument;

enum AlphaNumeric {
    A(65), Z(90), a(97), z(112), ZERO(48);

    public static final int TOTAL_ALPHABETS = 26, TOTAL_NUMBERS = 10;

    public final int numericValue;

    AlphaNumeric(int numericValue) {
        this.numericValue = numericValue;
    }
}

public class CustomCaesarCypher {

    public static String decrypt(String cipherText, int key) throws InvalidArgument {

        if (key < 0) {
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

        if (isInRange(character, AlphaNumeric.A.numericValue, AlphaNumeric.Z.numericValue)) {
            return unShiftCharacter(character, shift, AlphaNumeric.A.numericValue, AlphaNumeric.TOTAL_ALPHABETS);
        }

        if (isInRange(character, AlphaNumeric.a.numericValue, AlphaNumeric.z.numericValue)) {
            return unShiftCharacter(character, shift, AlphaNumeric.a.numericValue, AlphaNumeric.TOTAL_ALPHABETS);
        }

        return unShiftCharacter(character, shift, AlphaNumeric.ZERO.numericValue, AlphaNumeric.TOTAL_NUMBERS);
    }

    private static boolean isInRange(int num, int lowerLimit, int upperLimit) {
        return Math.min(Math.max(num, lowerLimit), upperLimit) == num;
    }

    private static char unShiftCharacter(char character, int shift, int lowerLimit, int totalChars) {
        int characterPosition = character - lowerLimit;
        int originalPosition = mod((characterPosition - shift), totalChars);
        return (char) (originalPosition + lowerLimit);
    }

    private static int mod(int num, int mod) {
        int result = num % mod;
        return result < 0 ? mod + result : result;
    }
}
