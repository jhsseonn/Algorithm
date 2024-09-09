package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11779_G3_최소비용_구하기_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<Node>[] adjList;
    static List<Integer> path = new ArrayList<>();
    static long[] minDistance;
    static long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        minDistance = new long[N+1];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
            minDistance[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        getMinDistance(start, end);

        sb.append(minDistance[end]).append("\n");
        sb.append(path.size()).append("\n");
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static void getMinDistance(int start, int end) {
        minDistance[start] = 0;
        boolean[] visited = new boolean[N+1];
        path.add(start);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;
            if (minDistance[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (visited[e.to]) continue;
                if (minDistance[e.to] < cur.w+e.w) continue;
                visited[e.to]=true;
                path.add(e.to);
                minDistance[e.to] = cur.w+e.w;
                pq.offer(new Node(e.to, minDistance[e.to]));
            }
        }
    }

    private static class Node {
        int to;
        long w;

        public Node(final int to, final long w) {
            this.to = to;
            this.w = w;
        }
    }
}
