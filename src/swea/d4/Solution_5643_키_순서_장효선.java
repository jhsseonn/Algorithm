package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 94,516kb / 1,821ms
 */
public class Solution_5643_키_순서_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, cnt, cntReverse;
    static List<Integer>[] nodes;
    static List<Integer>[] nodesReverse;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            nodes = new ArrayList[N + 1];
            nodesReverse = new ArrayList[N + 1];
            int ans = 0;

            for (int i = 0; i < N + 1; i++) {
                nodes[i] = new ArrayList<>();
                nodesReverse[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                nodes[from].add(to);
                nodesReverse[to].add(from);
            }

            for (int i = 1; i < N + 1; i++) {
                cnt = 0;
                cntReverse = 0;
                dfs(i, new boolean[N + 1]);
                dfsReverse(i, new boolean[N + 1]);
                if (cnt + cntReverse - 1 == N) ans += 1;
            }

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cur, boolean[] visited) {
        visited[cur] = true;
        cnt++;

        for (int next : nodes[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next, visited);
        }
    }

    private static void dfsReverse(int cur, boolean[] visited) {
        visited[cur] = true;
        cntReverse++;

        for (int next : nodesReverse[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            dfsReverse(next, visited);
        }
    }
}
