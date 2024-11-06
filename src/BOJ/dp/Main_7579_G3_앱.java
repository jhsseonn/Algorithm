package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7579_G3_앱 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] apps, dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        apps = new int[N][2];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][0] = Integer.parseInt(st.nextToken());
            sum += apps[i][0];
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][sum+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            int m = apps[i-1][0];
            int c = apps[i-1][1];
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j>=m && dp[i-1][j-m]!=Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-m]+c);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = M; i <= sum; i++) {
            ans = Math.min(ans, dp[N][i]);
        }

        System.out.println(ans);
    }
}
