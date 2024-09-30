package BOJ.dp;

import java.io.*;
import java.util.*;

public class Main_9095_S3_123더하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dp = new int[11];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i < 11; i++) {
                dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
                if (i==N) {
                    break;
                }
            }


            System.out.println(dp[N]);
        }
    }
}
