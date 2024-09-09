package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_23801_두_단계_최단경로_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X, Z, P;
    static List<Node>[] adjList;
    static long[] minDistanceXY;
    static long[] minDistanceYZ;
    static long INF = Long.MAX_VALUE;
    static boolean[] isP;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isP = new boolean[N+1];
        adjList = new ArrayList[N+1];
        minDistanceXY = new long[N+1];
        minDistanceYZ = new long[N+1];
        visited = new boolean[N+1];
        long minDistance = INF;

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
            minDistanceXY[i] = INF;
            minDistanceYZ[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 무향 그래프
            adjList[from].add(new Node(to, w));
            adjList[to].add(new Node(from, w));
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        P = Integer.parseInt(br.readLine());

        getMinDistanceXY(X);
        Arrays.fill(visited, false);
        getMinDistanceYZ(Z);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (minDistanceXY[p]==INF || minDistanceYZ[p]==INF) continue;
            long d = minDistanceXY[p] + minDistanceYZ[p];
            if (minDistance>d) {
                minDistance = d;
            }
        }

        if (minDistance==INF) minDistance = -1;

        System.out.println(minDistance);
    }

    private static void getMinDistanceXY(int start) {
        minDistanceXY[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.w < b.w) return -1;
            else if (a.w > b.w) return 1;
            return 0;
        });
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;
            if (minDistanceXY[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (visited[e.to]) continue;
                if (minDistanceXY[e.to] < cur.w + e.w) continue;
                minDistanceXY[e.to] = cur.w+e.w;
                pq.offer(new Node(e.to, minDistanceXY[e.to]));
            }
        }
    }

    private static void getMinDistanceYZ(int start) {
        minDistanceYZ[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.w < b.w) return -1;
            else if (a.w > b.w) return 1;
            return 0;
        });
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;
            if (minDistanceYZ[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (visited[e.to]) continue;
                if (minDistanceYZ[e.to] < cur.w + e.w) continue;
                minDistanceYZ[e.to] = cur.w+e.w;
                pq.offer(new Node(e.to, minDistanceYZ[e.to]));
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
