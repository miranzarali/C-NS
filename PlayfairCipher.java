import java.util.HashMap;
import java.util.Map;

public class PlayfairCipher {
    private static char[][] keyTable;
    private static Map<Character, int[]> charPosMap;

    private static void createKeyTable(String key) {
        keyTable = new char[5][5];
        charPosMap = new HashMap<>();
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // 'J' is excluded
        String keyString = key.toUpperCase().replace("J", "I");
        keyString += alphabet;
        StringBuilder keyUnique = new StringBuilder();
        for (char c : keyString.toCharArray()) {
            if (keyUnique.indexOf(String.valueOf(c)) == -1) {
                keyUnique.append(c);
            }
        }
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyTable[i][j] = keyUnique.charAt(k);
                charPosMap.put(keyUnique.charAt(k), new int[]{i, j});
                k++;
            }
        }
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I");
        StringBuilder prepared = new StringBuilder(text);
        for (int i = 0; i < prepared.length(); i += 2) {
            if (i + 1 == prepared.length()) {
                prepared.append('X');
            } else if (prepared.charAt(i) == prepared.charAt(i + 1)) {
                prepared.insert(i + 1, 'X');
            }
        }
        return prepared.toString();
    }

    private static String[] digraphs(String text) {
        String preparedText = prepareText(text);
        String[] digraphs = new String[preparedText.length() / 2];
        for (int i = 0; i < digraphs.length; i++) {
            digraphs[i] = preparedText.substring(2 * i, 2 * i + 2);
        }
        return digraphs;
    }

    private static String encryptDigraph(String digraph) {
        int[] pos1 = charPosMap.get(digraph.charAt(0));
        int[] pos2 = charPosMap.get(digraph.charAt(1));
        char ch1, ch2;

        if (pos1[0] == pos2[0]) {
            ch1 = keyTable[pos1[0]][(pos1[1] + 1) % 5];
            ch2 = keyTable[pos2[0]][(pos2[1] + 1) % 5];
        } else if (pos1[1] == pos2[1]) {
            ch1 = keyTable[(pos1[0] + 1) % 5][pos1[1]];
            ch2 = keyTable[(pos2[0] + 1) % 5][pos2[1]];
        } else {
            ch1 = keyTable[pos1[0]][pos2[1]];
            ch2 = keyTable[pos2[0]][pos1[1]];
        }

        return "" + ch1 + ch2;
    }

    public static String encrypt(String text, String key) {
        createKeyTable(key);
        String[] digraphs = digraphs(text);
        StringBuilder encryptedText = new StringBuilder();

        for (String digraph : digraphs) {
            encryptedText.append(encryptDigraph(digraph));
        }

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "playfair example";
        String text = "hidethegoldinthetreestump";
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}
