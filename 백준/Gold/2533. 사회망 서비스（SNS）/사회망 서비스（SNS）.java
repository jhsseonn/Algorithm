import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer>[] adjList;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        adjList = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int depth) {
        visited[depth] = true;
        dp[depth][0] = 0;
        dp[depth][1] = 1;  // 해당 지점도 얼리어답터이므로 1로 초기화

        for (Integer child:adjList[depth]) {
            if (visited[child]) continue;
            dfs(child);
            dp[depth][0] += dp[child][1];
            dp[depth][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}
