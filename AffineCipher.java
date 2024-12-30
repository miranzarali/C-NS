public class AffineCipher {
    private static final int a = 3;
    private static final int b = 12;
    private static final int m = 26; // Length of the alphabet

    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                character = (char) ((a * (character - base) + b) % m + base);
            }
            result.append(character);
        }
        return result.toString();
    }

    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        int aInverse = 9; // Multiplicative inverse of 3 modulo 26
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                character = (char) ((aInverse * ((character - base - b + m) % m)) % m + base);
            }
            result.append(character);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "HELLOWORLD";
        String encrypted = encrypt(text);
        String decrypted = decrypt(encrypted);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
