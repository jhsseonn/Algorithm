package liveForm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class AdjListSearch {
    static int N;
    static Edge[] adjList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(input);
        N = sc.nextInt(); // 정점수
        int C = sc.nextInt(); // 간선수

        adjList = new Edge[N];

        // 정점 입력받기
        for (int i = 0; i < C; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList[from] = new Edge(to, adjList[from]);
            adjList[to] = new Edge(from, adjList[to]);
        }

        // 함수 호출
        bfs(0);
        System.out.println();
        dfs(0, new boolean[N]);
    }

    // bfs
    private static void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        visited[start] = true;
        dq.offer(start);

        while(!dq.isEmpty()) {
            int cur = dq.poll();
            System.out.print((char)(cur+65)+" ");

            for (Edge e = adjList[cur]; e!=null ; e=e.next) {
                if (visited[e.vertex]) continue;
                visited[e.vertex] = true;
                dq.offer(e.vertex);
            }
        }
    }

    // dfs
    private static void dfs(int cur, boolean[] visited) {
        visited[cur] = true;
        System.out.print((char)(cur+65)+" ");

        for (Edge e = adjList[cur]; e!=null ; e=e.next) {
            if (visited[e.vertex]) continue;
            visited[e.vertex]=true;
            dfs(e.vertex, visited);
        }
    }

    public static class Edge {
        int vertex;
        Edge next;

        public Edge(final int vertex, final Edge next) {
            this.vertex = vertex;
            this.next = next;
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
