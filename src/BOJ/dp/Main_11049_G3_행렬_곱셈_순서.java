package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11049_G3_행렬_곱셈_순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int INF = Integer.MAX_VALUE;
        int[] matrix = new int[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = r;
            matrix[i+1] = c;
        }

        int[][] dp = new int[N][N];
        for (int i = 2; i <=N ; i++) {
            for (int j = 0; j <= N-i; j++) {
                dp[j][j+i-1] = INF;
                for (int k = j; k < j+i-1; k++) {
                    int value = dp[j][k] + dp[k+1][j+i-1] + (matrix[j] * matrix[k+1] * matrix[j+i]);
                    dp[j][j+i-1] = Math.min(dp[j][j+i-1], value);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
