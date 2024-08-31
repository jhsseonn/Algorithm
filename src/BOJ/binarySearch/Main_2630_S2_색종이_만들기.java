package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630_S2_색종이_만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            int result = 0;

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
