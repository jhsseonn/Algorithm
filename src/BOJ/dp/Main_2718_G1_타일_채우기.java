package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 14,340kb / 108ms
 */
public class Main_2718_G1_타일_채우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N + 1];

            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= N; i++) {
                dp[i] = dp[i - 1] + 4 * dp[i - 2];
                for (int j = 3; j <= i; j++) {
                    if (j % 2 == 0) {
                        dp[i] += 3 * dp[i - j];
                    } else {
                        dp[i] += 2 * dp[i - j];
                    }
                }
            }

            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
