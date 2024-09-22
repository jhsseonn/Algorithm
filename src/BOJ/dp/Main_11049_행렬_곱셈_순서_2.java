package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16,788kb / 188ms
 */
public class Main_11049_행렬_곱셈_순서_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i + 1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];
        for (int k = 2; k <= N; k++) {  // 행렬 개수
            for (int i = 0; i + k <= N; i++) {  // 인덱스
                dp[i][i + k - 1] = Integer.MAX_VALUE;
                for (int j = i; j < i + k - 1; j++) {
                    int value = dp[i][j] + dp[j + 1][i + k - 1] + (arr[i] * arr[j + 1] * arr[i + k]);
                    dp[i][i + k - 1] = Math.min(dp[i][i + k - 1], value);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
