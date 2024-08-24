package BOJ.setmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 메모리: 228,292kb / 시간: 724ms
 */
public class Main_11478_S3_서로_다른_부분_문자열의_개수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> countString = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String S = br.readLine();
        int len = S.length();

        for (int i = 0; i < len; i++) {
            for (int j = len; j > i; j--) {
                String str = S.substring(i, j);
                int value = countString.getOrDefault(str, 0);
                countString.put(str, value+1);
            }
        }

        System.out.println(countString.keySet().size());
    }
}
