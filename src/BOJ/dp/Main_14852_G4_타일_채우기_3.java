package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 29,724kb / 136ms
 */
public class Main_14852_G4_타일_채우기_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int MOD = (int)1e9+7;
        long[] dp = new long[N+3];
        long[] sum = new long[N+3];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = 22;
        sum[3] = 1;
        for (int i = 4; i <= N; i++) {
            sum[i] = (sum[i-1]+dp[i-3])%MOD;
            dp[i] = (2*dp[i-1] + 3*dp[i-2] + 2*sum[i])%MOD;
        }

        System.out.println(dp[N]%MOD);
    }
}
