import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;  // 길이가 1인 경우 무조건 팰린드롬
        }

        // 길이가 2인 경우 팰린드롬 여부 dp에 저장
        for (int i = 1; i <= N-1; i++) {
            if (arr[i]==arr[i+1]) dp[i][i+1] = 1;
        }

        // 길이가 N-k인 경우 팰린드롬 여부 dp에 저장
        for (int k = 2; k < N; k++) {
            for (int i = 1; i<=N-k; i++) {
                if (dp[i+1][i+k-1]==1 && arr[i]==arr[i+k]) {
                    dp[i][i+k]=1;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dp[a][b]).append("\n");
        }
        System.out.println(sb);
    }
}
