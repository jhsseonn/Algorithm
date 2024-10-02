package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 19,568kb / 160ms
 */
public class Main_2098_G1_외판원_순회 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr, dp;
    static int INF = 987654321;
    static int N, fullBit;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1 << N];
        fullBit = (1 << N) - 1;

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);  // dp 초기화
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = dfs(0, 1);
        System.out.println(ans);
    }

    private static int dfs(int cur, int visited) {
        if (visited == fullBit) {  // 모든 노드를 방문한 상태라면
            if (arr[cur][0] != 0) {  // 처음 노드로 돌아가는 길이 있는 경우
                return arr[cur][0];
            }
            else return INF;  // 처음 노드로 돌아가는 길이 없는 경우
        }

        if (dp[cur][visited] != -1) return dp[cur][visited];  // 이미 방문한 노드라면 dp에 저장되어 있는 최단경로 리턴

        dp[cur][visited] = INF; // 최단 경로 저장을 위해 INF로 초기화
        for (int i = 0; i < N; i++) {  // 현재 노드로부터 다른 노드까지의 경로를 모두 탐색하면서 최단경로 구하기
            if (arr[cur][i] == 0 || (visited & (1 << i)) != 0) continue;  // 다음 노드까지의 경로가 없는 경우
            dp[cur][visited] = Math.min(dp[cur][visited], arr[cur][i] + dfs(i, visited | (1 << i)));
        }

        return dp[cur][visited];
    }
}
