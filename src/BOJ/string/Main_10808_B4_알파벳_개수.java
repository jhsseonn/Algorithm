package BOJ.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10808_B4_알파벳_개수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        int[] alphabet = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alphabet[str.charAt(i) - 'a'] += 1;
        }

        for (int res : alphabet) {
            System.out.print(res + " ");
        }
    }
}
