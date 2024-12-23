package BOJ.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 339,928kb / 748ms
 */
public class Main_15486_G5_퇴사2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Meeting[] meetings = new Meeting[N+1];
        int dp[] = new int[N+2];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp[N+1] = 0;
        for (int i = N; i >= 1 ; i--) {
            if (i+meetings[i].days<=N+1) {
                dp[i] = Math.max(dp[i+1], meetings[i].income+dp[i+meetings[i].days]);
            } else {
                dp[i] = dp[i+1];
            }
        }

        System.out.println(dp[1]);
    }

    static class Meeting {
        int days, income;

        public Meeting(int days, int income) {
            this.days = days;
            this.income = income;
        }
    }
}
