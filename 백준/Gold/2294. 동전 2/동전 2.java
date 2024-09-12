import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] num = new int[n+1];
        int[] dp = new int[k+1];

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);
        Arrays.fill(dp, 10001);

        dp[0]=0;
        for (int i = 1; i <= n; i++) {
            for (int j = num[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j-num[i]]+1);
            }
        }

        if (dp[k]==10001) dp[k]=-1;

        sb.append(dp[k]);
        System.out.println(sb);
    }
}
