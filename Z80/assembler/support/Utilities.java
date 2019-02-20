package assembler.support;

public class Utilities {
    private final static String hexChar = "0123456789ABCDEF";

    /*
      Constructor
     */
    private Utilities() {
    }

    /*
      turn a 4 bit value into its equivalent hex digit
     */
    private static char getHexCharacter(final int value) {
        return hexChar.charAt(value);
    }

    /*
      turn a byte into two hex digits
     */
    private static String getByte(final int value) {

        char[] byteText = new char[2];
        try {
            byteText[0] = Utilities.getHexCharacter((value >>> 4));
            byteText[1] = Utilities.getHexCharacter((value & 0x0F));
        } catch (Exception e) {
            byteText[0] = '*';
            byteText[1] = '*';
        }
        return new String(byteText);
    }

    /*
      turn a word into four hex digits
     */
    public static String getWord(final int value) {
        return getByte(value >>> 8) + getByte(value & 0x00FF);
    }

    /*
      convert a hex digit into an int
     */
    public static int getHexDigit(final char hex) {
        if (hex > '9') {
            return hex - 0x37;
        } else {
            return hex - 0x030;
        }
    }

    /*
      convert a hex string into an integer
     */
    public static int getHexValue(String hex) {
        return Integer.parseInt(hex, 16);
    }
}
