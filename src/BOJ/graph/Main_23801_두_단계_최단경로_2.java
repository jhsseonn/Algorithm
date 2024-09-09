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
    static int[] minDistanceXY;
    static int[] minDistanceYZ;
    static int INF = Integer.MAX_VALUE;
    static boolean[] isP;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isP = new boolean[N+1];
        adjList = new ArrayList[N+1];
        minDistanceXY = new int[N+1];
        minDistanceYZ = new int[N+1];
        int minDistance = INF;

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            isP[Integer.parseInt(st.nextToken())] = true;
        }
        getMinDistanceXY(X, Z);
        getMinDistanceYZ(Z, X);

        for (int i = 1; i < N+1; i++) {
            if (isP[i] && minDistanceXY[i]!=INF && minDistanceYZ[i]!=INF && minDistance>minDistanceXY[i] + minDistanceYZ[i]) {
                minDistance = minDistanceXY[i] + minDistanceYZ[i];
            }
        }

        if (minDistance==INF) minDistance = -1;

        System.out.println(minDistance);
    }

    private static void getMinDistanceXY(int start, int end) {
        Arrays.fill(minDistanceXY, INF);
        minDistanceXY[start] = 0;
        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistanceXY[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (minDistanceXY[e.to] < cur.w + e.w) continue;
                minDistanceXY[e.to] = cur.w+e.w;
                visited[e.to] = true;
                pq.offer(new Node(e.to, minDistanceXY[e.to]));
            }
        }
    }

    private static void getMinDistanceYZ(int start, int end) {
        Arrays.fill(minDistanceYZ, INF);
        minDistanceYZ[start] = 0;
        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistanceYZ[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (minDistanceYZ[e.to] < cur.w + e.w) continue;
                minDistanceYZ[e.to] = cur.w+e.w;
                visited[e.to] = true;
                pq.offer(new Node(e.to, minDistanceYZ[e.to]));
            }
        }
    }

    private static class Node {
        int to, w;

        public Node(final int to, final int w) {
            this.to = to;
            this.w = w;
        }
    }
}
