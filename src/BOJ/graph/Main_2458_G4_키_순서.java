package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2458_G4_키_순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, cnt, cntReverse;
    static List<Integer>[] nodes;
    static List<Integer>[] nodesReverse;
//    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];
        nodesReverse = new ArrayList[N + 1];
//            visited = new boolean[N+1];
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
            dfs(i, new boolean[N+1]);
            dfsReverse(i, new boolean[N+1]);
//            if (cntReverse==0) cntReverse+=1;
            if (cnt + cntReverse-1 == N) ans += 1;
        }

        System.out.println(ans);
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
