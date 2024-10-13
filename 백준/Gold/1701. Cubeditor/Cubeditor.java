import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str;

    public static void main(String[] args) throws IOException {
        str = br.readLine();
        int maxLen = 0;

        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            int[] pi = getPI(suffix);

            for (int pattern:pi) {
                maxLen = Math.max(maxLen, pattern);
            }
        }

        System.out.println(maxLen);
    }

    private static int[] getPI(String pattern) {
        int n = pattern.length();
        int[] pi = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}
