package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 14,392kb / 120ms
 */
public class Main_7579_G3_앱 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] apps;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        apps = new int[N][2];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][1] = Integer.parseInt(st.nextToken());
            sum += apps[i][1];
        }

        dp = new int[sum+1];
        Arrays.fill(dp, 0);

        for (int i = 0; i < N; i++) {
            int m = apps[i][0];
            int c = apps[i][1];
            for (int j = sum; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j-c]+m);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= sum; i++) {
            if (dp[i]>=M) {
                ans = Math.min(ans, i);
            }
        }

        System.out.println(ans);
    }
}
