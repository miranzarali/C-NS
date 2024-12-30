import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SubstitutionCipher {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.print("Enter the substitution string for 'a': ");
        String a = br.readLine();

        System.out.print("Enter the substitution string for 'b': ");
        String b = br.readLine();

        if (a.length() != b.length()) {
            System.out.println("Error: Both substitution strings must have the same length.");
            return;
        }

        System.out.print("Enter any string: ");
        String str = br.readLine();

        String encrypted = encrypt(str, a, b);
        System.out.println("The encrypted data is: " + encrypted);
    }

    public static String encrypt(String str, String a, String b) {
        String encrypted = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int j = a.indexOf(c);
            if (j != -1) {
                encrypted += b.charAt(j);
            } else {
                encrypted += c; // If the character is not found in 'a', keep it unchanged
            }
        }
        return encrypted;
    }
}
