package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2718_타일_채우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int result, N;
    static int[] arr;
    static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        arr = new int[N+1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            System.out.println(dp(arr[i]));
        }
    }

    public static int dp(int x) {
        if (x==0) return 1;
        if (x==1) return 1;
        if (x==2) return 5;
        if (x==3) return 11;
        if (dp[x]!=0) return dp[x];

        result = dp(x-1)+4*dp(x-2);

        for (int i = 3; i <= x; ++i) {
            if (i%2==0) {
                result += 3*dp(x-i);
            } else {
                result += 2*dp(x-i);
            }
        }

        return dp[x] = result;
    }
}
