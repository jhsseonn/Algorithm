import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int INF = Integer.MAX_VALUE;
            int[][] dist = new int[N + 2][2];
            int[][] dp = new int[N + 2][N + 2];

            // 입력받기
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                dist[i] = new int[]{x, y};
            }

            // dp 정해진 거리 초기화
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (i == j) dp[i][j] = 0;
                    else if (Math.abs(dist[i][0] - dist[j][0]) + Math.abs(dist[i][1] - dist[j][1]) <= 1000) {
                        dp[i][j] = 1;
                        dp[j][i] = 1;
                    } else {
                        dp[i][j] = INF;
                        dp[j][i] = INF;
                    }
                }
            }

            // 플로이드 워셜
            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        if (dp[i][k] != INF && dp[k][j] != INF) {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                        }
                    }
                }
            }

            if (dp[0][N + 1] == INF) System.out.println("sad");
            else System.out.println("happy");
        }
    }
}
