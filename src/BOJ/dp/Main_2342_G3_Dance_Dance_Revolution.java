package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 46,764kb / 340ms
 */
public class Main_2342_G3_Dance_Dance_Revolution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = st.countTokens();
        int[] arr = new int[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[N+1][5][5];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        dp[0][0][0] = 0;
        for (int i = 1; i<=N; i++) {
            if (arr[i]==0) break;
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    if (dp[i-1][left][right]==Integer.MAX_VALUE) continue;
                    dp[i][arr[i]][right] = Math.min(dp[i][arr[i]][right], (dp[i-1][left][right]+calculate(left, arr[i])));
                    dp[i][left][arr[i]] = Math.min(dp[i][left][arr[i]], (dp[i-1][left][right]+calculate(right, arr[i])));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result = Math.min(result, dp[N-1][i][j]);
            }
        }

        System.out.println(result);
    }

    private static int calculate(int before, int next) {
        if (before==0) return 2;
        if (before==next) return 1;
        if (Math.abs(before-next)==2) return 4;
        return 3;
    }
}
