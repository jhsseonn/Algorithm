import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String str1 = br.readLine();
        String str2 = br.readLine();
        int N = str1.length();
        int M = str2.length();
        int[][] dp = new int[N+1][M+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (str1.charAt(i)==str2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j]+1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        int i = N, j=M;
        while (i>0 && j>0) {
            if (str1.charAt(i-1)==str2.charAt(j-1)) {
                ans.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j]>dp[i][j-1]) {
                i--;
            } else j--;
        }

        ans.reverse();
        System.out.println(dp[N][M]);
        System.out.println(ans);
    }
}
