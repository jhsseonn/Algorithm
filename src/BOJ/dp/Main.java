package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[][] dp = new int[N][N];

        // 문자열의 길이가 1, 2인 경우는 고려x (input 3부터)
        for (int k = 2; k < N; k++) {
            for (int i = 0; i < N-k; i++) {
                char a = str.charAt(i);
                char b = str.charAt(N-1-i);
                int x = dp[i][N-2-i];
                int y = dp[i+1][N-1-i];
                int z = dp[i][N-1-i];

                if (a!=b) {  // 같지 않은 경우 문자열 추가

                } else {  // 같은 경우 문자열 추가 필요 없음
                    dp[i+1][N-2-i] = z;
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }

    private static void getMin(int i, int j) {
        if (i==j) {

        }
    }
}

