package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10844_S1_쉬운_계단_수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        long INF = (long)1e9;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1]%INF;
            dp[i][9] = dp[i - 1][8]%INF;
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = dp[i - 1][j - 1]%INF + dp[i - 1][j + 1]%INF;
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i]%INF;
        }
        System.out.println(sum%INF);
    }
}
