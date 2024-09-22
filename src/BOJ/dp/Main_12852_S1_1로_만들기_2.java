package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 22,944kb / 124ms
 */
public class Main_12852_S1_1로_만들기_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N + 1];
        int[] before = new int[N + 1];

        int[] dp = new int[N + 1];
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                before[i] = i / 2;
            }
        }

        System.out.println(dp[N]);
        while (N > 0) {
            sb.append(N).append(" ");
            N = before[N];
        }
        System.out.println(sb);
    }
}
