package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 113,188kb / 304ms
 */
public class Main_5502_G3_팰린드롬 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String str2 = new StringBuffer(str).reverse().toString();
        int[][] dp = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (str.charAt(i)==str2.charAt(j)) {
                    dp[i+1][j+1]= dp[i][j]+1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        System.out.println(N-dp[N][N]);
    }
}
