package BOJ.setmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 메모리: 31,128kb / 시간: 352ms
 */
public class Main_14425_S4_문자열_집합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Set<String> strings;

    public static void main(String[] args) throws IOException {
        strings = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int count = 0;

        for (int i = 0; i < N; i++) {
            strings.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            if (strings.contains(br.readLine())) {
                count += 1;
            }
        }

        System.out.println(count);
    }
}
