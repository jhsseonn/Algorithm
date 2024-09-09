package liveForm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class AdjMatrixSearch {
    static int V;
    static int[][] adjMatrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(input);
        V = sc.nextInt();
        int E = sc.nextInt();
        adjMatrix = new int[V][V];

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjMatrix[from][to] = adjMatrix[to][from] = 1;
        }

        // 함수 호출
        bfs(0);
        System.out.println();
        dfs(0, new boolean[V]);
    }

    // bfs
    private static void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[V];
        visited[start] = true;
        dq.offer(start);

        while(!dq.isEmpty()) {
            int cur = dq.poll();
            System.out.print((char)(cur+65)+" ");

            for (int i = 0; i < V; i++) {
                if (adjMatrix[cur][i]==0 || visited[i]) continue;
                visited[i] = true;
                dq.offer(i);
            }
        }
    }

    // dfs
    private static void dfs(int cur, boolean[] visited) {
        visited[cur] = true;
        System.out.print((char)(cur+65)+" ");

        for (int i = 0; i < V; i++) {
            if (adjMatrix[cur][i]==0 || visited[i]) continue;
            dfs(i, visited);
        }
    }

    static String input="7\r\n" +
            "8\r\n" +
            "0 1\r\n" +
            "0 2	\r\n" +
            "1 3\r\n" +
            "1 4\r\n" +
            "2 4\r\n" +
            "3 5\r\n" +
            "4 5\r\n" +
            "5 6	";
}
