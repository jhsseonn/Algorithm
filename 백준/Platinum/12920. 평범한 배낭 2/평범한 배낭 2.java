import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Knap> knapsack = new ArrayList<>();

        knapsack.add(new Knap(0, 0));
        for (int i = 1; i <= N; i++) {
            st=new StringTokenizer(br.readLine());
            int v= Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());
            int k= Integer.parseInt(st.nextToken());

            while (k>0) {
                int nextK = k/2;
                int curK = k-nextK;
                knapsack.add(new Knap(v*curK, c*curK));
                k = nextK;
            }
        }

        N = knapsack.size();
        int[][] dp = new int[N+1][M+1];

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j<knapsack.get(i).v) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-knapsack.get(i).v]+knapsack.get(i).c);
                }
            }
        }

        System.out.println(dp[N-1][M]);
    }

    private static class Knap {
        int v, c;

        public Knap(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }
}
